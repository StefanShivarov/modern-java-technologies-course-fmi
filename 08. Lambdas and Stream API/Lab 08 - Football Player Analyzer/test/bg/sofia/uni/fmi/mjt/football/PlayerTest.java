package bg.sofia.uni.fmi.mjt.football;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    private static Player player1;
    private static Player player2;
    private static Player player3;

    @BeforeAll
    static void setUp() {
        player1 = new Player(
                "testPlayer",
                "Player For Test",
                LocalDate.of(2003, 7, 24),
                20,
                173,
                78,
                List.of(Position.CAM, Position.LW, Position.RW),
                "Bulgaria",
                79,
                88,
                2_000_000L,
                20_000L,
                Foot.RIGHT
        );

        player2 = new Player(
                "secondPlayer",
                "Second Player For Test",
                LocalDate.of(2003, 3, 1),
                20,
                176,
                76,
                List.of(Position.CAM, Position.CF),
                "Bulgaria",
                82,
                90,
                6_000_000L,
                50_000L,
                Foot.RIGHT
        );

        player3 = new Player(
                "thirdPlayer",
                "Third Player For Test",
                LocalDate.of(2005, 2, 25),
                20,
                184,
                82,
                List.of(Position.CB, Position.CDM),
                "Bulgaria",
                73,
                82,
                1_000_000L,
                10_000L,
                Foot.RIGHT
        );
    }

    @Test
    void testCalculateProspect() {
        assertEquals(8.35, player1.calculateProspect());
        assertEquals(8.6, player2.calculateProspect());
        assertEquals(7.75, player3.calculateProspect());
    }

    @Test
    void testIsSimilarTo() {
        assertTrue(player1.isSimilarTo(player2));
        assertFalse(player2.isSimilarTo(player3));
        assertFalse(player1.isSimilarTo(player3));
    }

}
