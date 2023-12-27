package bg.sofia.uni.fmi.mjt.space;

import bg.sofia.uni.fmi.mjt.space.algorithm.Rijndael;
import bg.sofia.uni.fmi.mjt.space.exception.CipherException;
import bg.sofia.uni.fmi.mjt.space.exception.TimeFrameMismatchException;
import bg.sofia.uni.fmi.mjt.space.io.CSVReader;
import bg.sofia.uni.fmi.mjt.space.io.parser.MissionParser;
import bg.sofia.uni.fmi.mjt.space.io.parser.RocketParser;
import bg.sofia.uni.fmi.mjt.space.mission.Mission;
import bg.sofia.uni.fmi.mjt.space.mission.MissionStatus;
import bg.sofia.uni.fmi.mjt.space.rocket.Rocket;
import bg.sofia.uni.fmi.mjt.space.rocket.RocketStatus;

import javax.crypto.SecretKey;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class MJTSpaceScanner implements SpaceScannerAPI {

    private final List<Mission> missions;
    private final List<Rocket> rockets;
    private final Rijndael rijndael;

    public MJTSpaceScanner(Reader missionsReader, Reader rocketsReader, SecretKey secretKey) {
        this.missions = loadMissionsFromCSVFile(missionsReader);
        this.rockets = loadRocketsFromCSVFile(rocketsReader);
        this.rijndael = new Rijndael(secretKey);
    }

    @Override
    public Collection<Mission> getAllMissions() {
        return missions;
    }

    @Override
    public Collection<Mission> getAllMissions(MissionStatus missionStatus) {
        return missions.stream()
                .filter(mission -> mission.missionStatus() == missionStatus)
                .toList();
    }

    @Override
    public String getCompanyWithMostSuccessfulMissions(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) {
            throw new TimeFrameMismatchException("Invalid time frame!");
        }

        return missions.stream()
                .filter(mission -> mission.date().isAfter(from) && mission.date().isBefore(to))
                .filter(mission -> mission.missionStatus() == MissionStatus.SUCCESS)
                .collect(Collectors.groupingBy(Mission::company, Collectors.counting()))
                .entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("");
    }

    @Override
    public Map<String, Collection<Mission>> getMissionsPerCountry() {
        return missions.stream()
                .collect(Collectors.groupingBy(
                        mission -> {
                            String[] locationTokens = mission.location().split(",\\s+");
                            return locationTokens[locationTokens.length - 1];
                        },
                        Collectors.toCollection(ArrayList::new)
                ));
    }

    @Override
    public List<Mission> getTopNLeastExpensiveMissions(int n, MissionStatus missionStatus, RocketStatus rocketStatus) {
        return missions.stream()
                .filter(mission -> mission.missionStatus() == missionStatus)
                .filter(mission -> mission.rocketStatus() == rocketStatus)
                .sorted(Comparator.comparingDouble(m -> m.cost().orElse(Double.MAX_VALUE)))
                .limit(n)
                .toList();
    }

    @Override
    public Map<String, String> getMostDesiredLocationForMissionsPerCompany() {
        return missions.stream()
                .collect(Collectors.groupingBy(
                        Mission::company,
                        Collectors.groupingBy(
                                mission -> {
                                    String[] locationTokens = mission.location().split(",\\s+");
                                    return locationTokens[locationTokens.length - 1];
                                },
                                Collectors.counting()
                        )
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().entrySet().stream()
                                .max(Map.Entry.comparingByValue())
                                .map(Map.Entry::getKey)
                                .orElse("Unknown")
                ));
    }

    @Override
    public Map<String, String> getLocationWithMostSuccessfulMissionsPerCompany(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) {
            throw new TimeFrameMismatchException("Invalid time frame!");
        }

        return missions.stream()
                .filter(mission -> mission.date().isAfter(from) && mission.date().isBefore(to))
                .filter(mission -> mission.missionStatus() == MissionStatus.SUCCESS)
                .collect(Collectors.groupingBy(
                        Mission::company,
                        Collectors.groupingBy(
                                mission -> {
                                    String[] locationTokens = mission.location().split(",\\s+");
                                    return locationTokens[locationTokens.length - 1];
                                },
                                Collectors.counting()
                        )
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().entrySet().stream()
                                .max(Map.Entry.comparingByValue())
                                .map(Map.Entry::getKey)
                                .orElse("Unknown")
                ));
    }

    @Override
    public Collection<Rocket> getAllRockets() {
        return rockets;
    }

    @Override
    public List<Rocket> getTopNTallestRockets(int n) {
        return rockets.stream()
                .filter(rocket -> rocket.height().isPresent())
                .sorted((r1, r2) -> Double.compare(r2.height().get(), r1.height().get()))
                .limit(n)
                .toList();
    }

    @Override
    public Map<String, Optional<String>> getWikiPageForRocket() {
        return rockets.stream()
                .collect(Collectors.groupingBy(
                        Rocket::name,
                        Collectors.mapping(
                                rocket -> rocket.wiki().orElse(null),
                                Collectors.reducing((s1, s2) -> s1)
                        )
                ));
    }

    @Override
    public List<String> getWikiPagesForRocketsUsedInMostExpensiveMissions(int n, MissionStatus missionStatus, RocketStatus rocketStatus) {
        List<String> mostExpensiveMissionRocketNames = missions.stream()
                .filter(mission -> mission.missionStatus() == missionStatus)
                .filter(mission -> mission.rocketStatus() == rocketStatus)
                .filter(mission -> mission.cost().isPresent())
                .sorted(Comparator.comparingDouble(mission -> mission.cost().get()))
                .limit(n)
                .map(mission -> mission.detail().rocketName())
                .toList();

        return mostExpensiveMissionRocketNames.stream()
                .map(rocketName -> rockets.stream()
                        .filter(rocket -> rocket.name().equals(rocketName))
                        .findFirst()
                        .flatMap(Rocket::wiki)
                        .orElse(null)
                )
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public void saveMostReliableRocket(OutputStream outputStream, LocalDate from, LocalDate to) throws CipherException {
        if (from.isAfter(to)) {
            throw new TimeFrameMismatchException("Invalid time frame!");
        }

        String mostReliableRocketName = missions.stream()
                .filter(mission -> mission.date().isAfter(from) && mission.date().isBefore(to))
                .map(mission -> mission.detail().rocketName())
                .max(Comparator.comparingDouble(this::calculateRocketReliability))
                .orElse(null);

        Rocket mostReliableRocket = rockets.stream()
                .filter(rocket -> rocket.name().equals(mostReliableRocketName))
                .findFirst()
                .orElse(null);

        if (mostReliableRocket != null) {
            rijndael.serializeRocket(mostReliableRocket, outputStream);
        }
    }

    private long getSuccessfulMissionsAmountForRocket(String rocketName) {
        return missions.stream()
                .filter(mission -> mission.detail().rocketName().equals(rocketName))
                .filter(mission -> mission.missionStatus() == MissionStatus.SUCCESS)
                .count();
    }

    private long getUnsuccessfulMissionsAmountForRocket(String rocketName) {
        return missions.stream()
                .filter(mission -> mission.detail().rocketName().equals(rocketName))
                .filter(mission -> mission.missionStatus() == MissionStatus.FAILURE
                        || mission.missionStatus() == MissionStatus.PARTIAL_FAILURE
                        || mission.missionStatus() == MissionStatus.PRELAUNCH_FAILURE
                )
                .count();
    }

    private double calculateRocketReliability(String rocketName) {
        long successfulMissions = getSuccessfulMissionsAmountForRocket(rocketName);
        long unsuccessfulMissions = getUnsuccessfulMissionsAmountForRocket(rocketName);

        return (2 * successfulMissions + unsuccessfulMissions) / (2.0 * (successfulMissions + unsuccessfulMissions));
    }

    private List<Mission> loadMissionsFromCSVFile(Reader missionsReader) {
        try (CSVReader csvReader = new CSVReader(missionsReader)) {
            return csvReader.readAllLines().stream()
                    .skip(1)
                    .map(MissionParser::parseFromInput)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Error while parsing the missions data from the CSV file!", e);
        }
    }

    private List<Rocket> loadRocketsFromCSVFile(Reader rocketsReader) {
        try (CSVReader csvReader = new CSVReader(rocketsReader)) {
            return csvReader.readAllLines().stream()
                    .skip(1)
                    .map(RocketParser::parseFromInput)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Error while parsing the missions data from the CSV file!", e);
        }
    }

}
