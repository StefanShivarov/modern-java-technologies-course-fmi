package bg.sofia.uni.fmi.mjt.space;

import bg.sofia.uni.fmi.mjt.space.exception.CipherException;
import bg.sofia.uni.fmi.mjt.space.mission.Mission;
import bg.sofia.uni.fmi.mjt.space.mission.MissionStatus;
import bg.sofia.uni.fmi.mjt.space.rocket.Rocket;
import bg.sofia.uni.fmi.mjt.space.rocket.RocketStatus;

import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MJTSpaceScanner implements SpaceScannerAPI {

    //TODO: implement
    @Override
    public Collection<Mission> getAllMissions() {
        return null;
    }

    @Override
    public Collection<Mission> getAllMissions(MissionStatus missionStatus) {
        return null;
    }

    @Override
    public String getCompanyWithMostSuccessfulMissions(LocalDate from, LocalDate to) {
        return null;
    }

    @Override
    public Map<String, Collection<Mission>> getMissionsPerCountry() {
        return null;
    }

    @Override
    public List<Mission> getTopNLeastExpensiveMissions(int n, MissionStatus missionStatus, RocketStatus rocketStatus) {
        return null;
    }

    @Override
    public Map<String, String> getMostDesiredLocationForMissionsPerCompany() {
        return null;
    }

    @Override
    public Map<String, String> getLocationWithMostSuccessfulMissionsPerCompany(LocalDate from, LocalDate to) {
        return null;
    }

    @Override
    public Collection<Rocket> getAllRockets() {
        return null;
    }

    @Override
    public List<Rocket> getTopNTallestRockets(int n) {
        return null;
    }

    @Override
    public Map<String, Optional<String>> getWikiPageForRocket() {
        return null;
    }

    @Override
    public List<String> getWikiPagesForRocketsUsedInMostExpensiveMissions(int n, MissionStatus missionStatus, RocketStatus rocketStatus) {
        return null;
    }

    @Override
    public void saveMostReliableRocket(OutputStream outputStream, LocalDate from, LocalDate to) throws CipherException {

    }

}
