package bg.sofia.uni.fmi.mjt.football;

import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class FootballPlayerAnalyzer implements FootballPlayerAnalyzerAPI {

    /**
     * Loads the dataset from the given {@code reader}. The reader argument will not be null and a correct dataset of
     * the specified type can be read from it.
     *
     * @param reader Reader from which the dataset can be read.
     */
    public FootballPlayerAnalyzer(Reader reader) {

    }

    @Override
    public List<Player> getAllPlayers() {
        return null;
    }

    @Override
    public Set<String> getAllNationalities() {
        return null;
    }

    @Override
    public Player getHighestPaidPlayerByNationality(String nationality) {
        return null;
    }

    @Override
    public Map<Position, Set<Player>> groupByPosition() {
        return null;
    }

    @Override
    public Optional<Player> getTopProspectPlayerForPositionInBudget(Position position, long budget) {
        return Optional.empty();
    }

    @Override
    public Set<Player> getSimilarPlayers(Player player) {
        return null;
    }

    @Override
    public Set<Player> getPlayersByFullNameKeyword(String keyword) {
        return null;
    }
}
