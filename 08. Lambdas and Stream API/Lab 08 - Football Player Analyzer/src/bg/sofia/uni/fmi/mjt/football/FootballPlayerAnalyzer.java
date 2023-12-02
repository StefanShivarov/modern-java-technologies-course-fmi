package bg.sofia.uni.fmi.mjt.football;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FootballPlayerAnalyzer implements FootballPlayerAnalyzerAPI {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    private List<Player> playersDatabase;

    /**
     * Loads the dataset from the given {@code reader}.
     * The reader argument will not be null and a correct dataset of
     * the specified type can be read from it.
     *
     * @param reader Reader from which the dataset can be read.
     */
    public FootballPlayerAnalyzer(Reader reader) {
        this.playersDatabase = loadPlayers(reader);
    }

    private List<Player> loadPlayers(Reader reader) {
        List<Player> loadedPlayers = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            bufferedReader.readLine();
            String nextLine;
            while ((nextLine = bufferedReader.readLine()) != null) {
                String[] inputTokens = nextLine.split(";");
                loadedPlayers.add(
                        new Player(
                                inputTokens[0],
                                inputTokens[1],
                                parseDate(inputTokens[2]),
                                Integer.parseInt(inputTokens[3]),
                                Double.parseDouble(inputTokens[4]),
                                Double.parseDouble(inputTokens[5]),
                                parsePositions(inputTokens[6]),
                                inputTokens[7],
                                Integer.parseInt(inputTokens[8]),
                                Integer.parseInt(inputTokens[9]),
                                Long.parseLong(inputTokens[10]),
                                Long.parseLong(inputTokens[11]),
                                Foot.valueOf(inputTokens[12].toUpperCase())
                        ));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return loadedPlayers;
    }

    private List<Position> parsePositions(String positionsInput) {
        return Arrays.stream(positionsInput.split(","))
                .map(Position::valueOf)
                .collect(Collectors.toList());
    }

    private LocalDate parseDate(String dateInput) {
        return LocalDate.parse(dateInput, dateFormatter);
    }

    @Override
    public List<Player> getAllPlayers() {
        return Collections.unmodifiableList(playersDatabase);
    }

    @Override
    public Set<String> getAllNationalities() {
        return playersDatabase.stream()
                .map(Player::nationality)
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Player getHighestPaidPlayerByNationality(String nationality) {
        if (nationality == null) {
            throw new IllegalArgumentException("Nationality is null!");
        }

        return playersDatabase.stream()
                .filter(player -> player.nationality().equals(nationality))
                .max(Comparator.comparingLong(Player::wageEuro))
                .orElseThrow(() -> new NoSuchElementException(
                        "No player from " + nationality + " was found!"
                ));
    }

    @Override
    public Map<Position, Set<Player>> groupByPosition() {
        return playersDatabase.stream()
                .flatMap(player -> player.positions().stream()
                        .map(position -> Map.entry(position, player)))
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toSet())));
    }

    @Override
    public Optional<Player> getTopProspectPlayerForPositionInBudget(
            Position position,
            long budget
    ) {
        if (position == null) {
            throw new IllegalArgumentException("Position is null!");
        }

        if (budget < 0) {
            throw new IllegalArgumentException("Budget is negative!");
        }

        return playersDatabase.stream()
                .filter(player -> player.positions().contains(position))
                .filter(player -> player.valueEuro() <= budget)
                .max(Comparator.comparingDouble(Player::calculateProspect));
    }

    @Override
    public Set<Player> getSimilarPlayers(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player is null!");
        }

        return playersDatabase.stream()
                .filter(p -> p.isSimilarTo(player))
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Set<Player> getPlayersByFullNameKeyword(String keyword) {
        if (keyword == null) {
            throw new IllegalArgumentException("Keyword is null!");
        }

        return playersDatabase.stream()
                .filter(player -> player.fullName().contains(keyword))
                .collect(Collectors.toUnmodifiableSet());
    }

}
