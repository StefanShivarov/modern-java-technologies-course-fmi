package bg.sofia.uni.fmi.mjt.football;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FootballPlayerAnalyzerTest {

    private static FootballPlayerAnalyzer footballPlayerAnalyzer;

    @BeforeAll
    static void setUp() {
        String csvData =
                "name;full_name;birth_date;age;height_cm;weight_kgs;positions;nationality;overall_rating;potential;value_euro;wage_euro;preferred_foot\n" +
                        "L. Messi;Lionel Andrés Messi Cuccittini;6/24/1987;31;170.18;72.1;CF,RW,ST;Argentina;94;94;110500000;565000;Left\n" +
                        "C. Eriksen;Christian  Dannemann Eriksen;2/14/1992;27;154.94;76.2;CAM,RM,CM;Denmark;88;89;69500000;205000;Right\n" +
                        "P. Pogba;Paul Pogba;3/15/1993;25;190.5;83.9;CM,CAM;France;88;91;73000000;255000;Right\n" +
                        "L. Insigne;Lorenzo Insigne;6/4/1991;27;162.56;59;LW,ST;Italy;88;88;62000000;165000;Right\n" +
                        "K. Koulibaly;Kalidou Koulibaly;6/20/1991;27;187.96;88.9;CB;Senegal;88;91;60000000;135000;Right\n" +
                        "V. van Dijk;Virgil van Dijk;7/8/1991;27;193.04;92.1;CB;Netherlands;88;90;59500000;215000;Right\n" +
                        "K. Mbappé;Kylian Mbappé;12/20/1998;20;152.4;73;RW,ST,RM;France;88;95;81000000;100000;Right\n" +
                        "S. Agüero;Sergio Leonel Agüero del Castillo;6/2/1988;30;172.72;69.9;ST;Argentina;89;89;64500000;300000;Right\n" +
                        "M. Neuer;Manuel Neuer;3/27/1986;32;193.04;92.1;GK;Germany;89;89;38000000;130000;Right\n" +
                        "E. Cavani;Edinson Roberto Cavani Gómez;2/14/1987;32;185.42;77.1;ST;Uruguay;89;89;60000000;200000;Right\n" +
                        "Sergio Busquets;Sergio Busquets i Burgos;7/16/1988;30;187.96;76.2;CDM,CM;Spain;89;89;51500000;315000;Right\n" +
                        "T. Courtois;Thibaut Courtois;5/11/1992;26;198.12;96.2;GK;Belgium;89;90;53500000;240000;Left\n" +
                        "M. ter Stegen;Marc-André ter Stegen;4/30/1992;26;187.96;84.8;GK;Germany;89;92;58000000;240000;Right\n" +
                        "A. Griezmann;Antoine Griezmann;3/21/1991;27;175.26;73;CF,ST;France;89;90;78000000;145000;Left\n" +
                        "M. Salah;Mohamed  Salah Ghaly;6/15/1992;26;175.26;71.2;RW,ST;Egypt;89;90;78500000;265000;Left\n" +
                        "P. Dybala;Paulo Bruno Exequiel Dybala;11/15/1993;25;152.4;74.8;CAM,RW;Argentina;89;94;89000000;205000;Left\n" +
                        "M. Škriniar;Milan Škriniar;2/11/1995;24;187.96;79.8;CB;Slovakia;86;93;53500000;89000;Right\n" +
                        "Fernandinho;Fernando Luiz Rosa;5/4/1985;33;152.4;67.1;CDM;Brazil;87;87;20500000;200000;Right\n" +
                        "G. Higuaín;Gonzalo Gerardo Higuaín;12/10/1987;31;185.42;88.9;ST;Argentina;87;87;48500000;205000;Right\n" +
                        "I. Rakitić;Ivan Rakitić;3/10/1988;30;182.88;78;CM,CDM;Croatia;87;87;46500000;260000;Right";

        footballPlayerAnalyzer = new FootballPlayerAnalyzer(new StringReader(csvData));
    }

    @Test
    void testGetAllPlayers() {
        List<Player> allPlayers = footballPlayerAnalyzer.getAllPlayers();

        assertEquals(20, allPlayers.size(),
                "getAllPlayers() should return list with correct size!");
        assertEquals("L. Messi", allPlayers.get(0).name(),
                "getAllPlayers() should return correct players data!");
        assertEquals(LocalDate.of(1998, 12, 20), allPlayers.get(6).birthDate(),
                "getAllPlayers() should return correct players data!");
        assertThrows(UnsupportedOperationException.class, () -> allPlayers.add(allPlayers.get(2)),
                "getAllPlayers() should return an unmodifiable list!");
    }

    @Test
    void testGetAllNationalities() {
        Set<String> nationalities = footballPlayerAnalyzer.getAllNationalities();
        assertEquals(14, nationalities.size());
        assertTrue(nationalities.contains("Slovakia"));
        assertFalse(nationalities.contains("Bulgaria"));
    }

    @Test
    void testGetHighestPaidPlayerByNationalityThrowsExceptionIfNationalityIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> footballPlayerAnalyzer.getHighestPaidPlayerByNationality(null),
                "getHighestPaidPlayerByNationality() should throw exception if nationality is null!");
    }

    @Test
    void testGetHighestPaidPlayerByNationalityThrowsExceptionIfNoPlayerExistsWithNationality() {
        assertThrows(NoSuchElementException.class,
                () -> footballPlayerAnalyzer.getHighestPaidPlayerByNationality("invalid country"),
                "getHighestPaidPlayerByNationality() should throw exception if no player with nationality exists!");
    }

    @Test
    void testGetHighestPaidPlayerByNationality() {
        assertEquals("P. Pogba",
                footballPlayerAnalyzer.getHighestPaidPlayerByNationality("France").name(),
                "getHighestPaidPlayerByNationality() should return correct player!");

        assertEquals("C. Eriksen",
                footballPlayerAnalyzer.getHighestPaidPlayerByNationality("Denmark").name(),
                "getHighestPaidPlayerByNationality() should return correct player!");
    }

    @Test
    void testGroupByPosition() {
        Map<Position, Set<Player>> groupedByPosition = footballPlayerAnalyzer.groupByPosition();
        assertEquals(3, groupedByPosition.get(Position.GK).size(),
                "groupByPosition() should group players correctly!");
        assertEquals(3, groupedByPosition.get(Position.CB).size(),
                "groupByPosition() should group players correctly!");
        assertEquals(8, groupedByPosition.get(Position.ST).size(),
                "groupByPosition() should group players correctly!");
    }

    @Test
    void testGetTopProspectPlayerForPositionInBudgetThrowsExceptionForInvalidArguments() {
        assertThrows(IllegalArgumentException.class,
                () -> footballPlayerAnalyzer.getTopProspectPlayerForPositionInBudget(null, 10000L),
                "getTopProspectPlayerForPositionInBudget() should throw exception if position is null!");

        assertThrows(IllegalArgumentException.class,
                () -> footballPlayerAnalyzer.getTopProspectPlayerForPositionInBudget(Position.LW, -12L),
                "getTopProspectPlayerForPositionInBudget() should throw exception if budget is negative!");
    }

    @Test
    void testGetTopProspectPlayerForPositionInBudget() {
        assertEquals("K. Mbappé",
                footballPlayerAnalyzer.getTopProspectPlayerForPositionInBudget(Position.RW, 100_000_000L)
                        .get().name(),
                "getTopProspectPlayerForPositionInBudget() should return correct player!");
    }

    @Test
    void testGetSimilarPlayersThrowsExceptionIfPlayerIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> footballPlayerAnalyzer.getSimilarPlayers(null),
                "getSimilarPlayers() should throw exception if player is null!");
    }

    @Test
    void testGetSimilarPlayers() {
        Player pogba = footballPlayerAnalyzer.getAllPlayers().get(2);
        Set<Player> similarToPogba = footballPlayerAnalyzer.getSimilarPlayers(pogba);
        assertEquals(4, similarToPogba.size(),
                "getSimilarPlayers() should return correct data!");
        assertTrue(similarToPogba.stream()
                        .anyMatch(player -> player.name().equals("C. Eriksen")),
                "getSimilarPlayers() should return correct data!");
    }

    @Test
    void testGetPlayersByFullNameKeywordThrowsExceptionForNullKeyword() {
        assertThrows(IllegalArgumentException.class,
                () -> footballPlayerAnalyzer.getPlayersByFullNameKeyword(null),
                "getPlayersByFullNameKeyword() should throw exception if keyword is null!");
    }

    @Test
    void testGetPlayersByFullNameKeyword() {
        assertEquals(1, footballPlayerAnalyzer.getPlayersByFullNameKeyword("Salah").size(),
                "getPlayersByFullNameKeyword() should return correct data!");
        assertEquals(2, footballPlayerAnalyzer.getPlayersByFullNameKeyword("Sergio").size(),
                "getPlayersByFullNameKeyword() should return correct data!");
    }

}
