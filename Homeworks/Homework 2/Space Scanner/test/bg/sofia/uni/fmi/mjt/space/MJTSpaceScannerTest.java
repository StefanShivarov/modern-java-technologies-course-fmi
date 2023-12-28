package bg.sofia.uni.fmi.mjt.space;

import bg.sofia.uni.fmi.mjt.space.algorithm.Rijndael;
import bg.sofia.uni.fmi.mjt.space.exception.CipherException;
import bg.sofia.uni.fmi.mjt.space.exception.TimeFrameMismatchException;
import bg.sofia.uni.fmi.mjt.space.mission.Mission;
import bg.sofia.uni.fmi.mjt.space.mission.MissionStatus;
import bg.sofia.uni.fmi.mjt.space.rocket.Rocket;
import bg.sofia.uni.fmi.mjt.space.rocket.RocketStatus;
import bg.sofia.uni.fmi.mjt.space.util.TestMockData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MJTSpaceScannerTest {

    private static MJTSpaceScanner spaceScanner;
    private static MJTSpaceScanner encryptionSpaceScanner;
    private static Rijndael rijndael;

    @BeforeAll
    static void setUp() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            SecretKey secretKey = keyGenerator.generateKey();

            rijndael = new Rijndael(secretKey);

            spaceScanner = new MJTSpaceScanner(
                    new StringReader(TestMockData.MOCK_MISSIONS_DATA),
                    new StringReader(TestMockData.MOCK_ROCKETS_DATA),
                    secretKey
            );

            encryptionSpaceScanner = new MJTSpaceScanner(
                    new StringReader(TestMockData.ENCRYPTION_MOCK_MISSION),
                    new StringReader(TestMockData.ENCRYPTION_MOCK_ROCKET),
                    secretKey
            );
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetAllMissions() {
        assertEquals(151, spaceScanner.getAllMissions().size(),
                "getAllMissions() should return all missions from the csv file!");
    }

    @Test
    void testGetAllMissionsWithGivenMissionStatus() {
        assertEquals(140, spaceScanner.getAllMissions(MissionStatus.SUCCESS).size(),
                "getAllMissions(MissionStatus) should return correct missions!");
        assertEquals(1, spaceScanner.getAllMissions(MissionStatus.PRELAUNCH_FAILURE).size(),
                "getAllMissions(MissionStatus) should return correct missions!");
    }

    @Test
    void testGetCompanyWithMostSuccessfulMissionsThrowsTimeFrameMismatchException() {
        assertThrows(TimeFrameMismatchException.class,
                () -> spaceScanner.getCompanyWithMostSuccessfulMissions(
                        LocalDate.of(2010, 1, 1),
                        LocalDate.of(1990, 1, 1)
                ), "getCompanyWithMostSuccessfulMissions() should throw" +
                        " TimeFrameMismatchException if from is after to!");
    }

    @Test
    void testGetCompanyWithMostSuccessfulMissions() {
        assertEquals("CASC", spaceScanner.getCompanyWithMostSuccessfulMissions(
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2023, 1, 1)
        ), "getCompanyWithMostSuccessfulMissions() should return correct company!");
    }

    @Test
    void testGetMissionsPerCountry() {
        Map<String, Collection<Mission>> missionsPerCountry = spaceScanner.getMissionsPerCountry();

        assertTrue(missionsPerCountry.containsKey("USA"));
        assertTrue(missionsPerCountry.containsKey("Russia"));
        assertTrue(missionsPerCountry.containsKey("China"));
        assertTrue(missionsPerCountry.containsKey("Kazakhstan"));
        assertEquals(39, missionsPerCountry.get("USA").size());
    }

    @Test
    void testGetTopNLeastExpensiveMissions() {
        List<Mission> top7leastExpensiveMissions = spaceScanner.getTopNLeastExpensiveMissions(7,
                MissionStatus.SUCCESS, RocketStatus.STATUS_ACTIVE);

        assertTrue(top7leastExpensiveMissions.size() <= 7);
        assertTrue(top7leastExpensiveMissions.stream()
                .noneMatch(mission -> mission.missionStatus() != MissionStatus.SUCCESS));
        assertTrue(top7leastExpensiveMissions.get(0).cost().get()
                <= top7leastExpensiveMissions.get(1).cost().get());
    }

    @Test
    void testGetLocationWithMostSuccessfulMissionsPerCompanyThrowsTimeFrameMismatchException() {
        assertThrows(TimeFrameMismatchException.class,
                () -> spaceScanner.getLocationWithMostSuccessfulMissionsPerCompany(
                        LocalDate.of(2020, 1, 1),
                        LocalDate.of(1980, 1, 1)
                ), "getLocationWithMostSuccessfulMissions() should throw" +
                        "TimeFrameMismatchException if from is after to!");
    }

    @Test
    void testGetMostDesiredLocationForMissionsPerCompany() {
        Map<String, String> mostDesiredLocationsPerCompany =
                spaceScanner.getMostDesiredLocationForMissionsPerCompany();
        assertTrue(mostDesiredLocationsPerCompany.containsKey("SpaceX"));
        assertTrue(mostDesiredLocationsPerCompany.containsKey("Arianespace"));
        assertTrue(mostDesiredLocationsPerCompany.containsKey("CASC"));
        assertEquals("ELA-3, Guiana Space Centre, French Guiana, France",
                mostDesiredLocationsPerCompany.get("Arianespace"));
    }

    @Test
    void testGetLocationWithMostSuccessfulMissionsPerCompany() {
        Map<String, String> locationsWithMostSuccessfulMissionsPerCompany =
                spaceScanner.getLocationWithMostSuccessfulMissionsPerCompany(
                        LocalDate.of(1960, 1, 1),
                        LocalDate.of(2023, 1, 1)
                );

        assertTrue(locationsWithMostSuccessfulMissionsPerCompany.containsKey("SpaceX"));
        assertTrue(locationsWithMostSuccessfulMissionsPerCompany.containsKey("CASC"));
        assertTrue(locationsWithMostSuccessfulMissionsPerCompany
                .containsValue("SLC-40, Cape Canaveral AFS, Florida, USA"));
    }

    @Test
    void testGetAllRockets() {
        assertEquals(151, spaceScanner.getAllRockets().size());
    }

    @Test
    void testGetTopNTallestRockets() {
        List<Rocket> top9TallestRockets = spaceScanner.getTopNTallestRockets(9);

        assertEquals(9, top9TallestRockets.size());
        assertTrue(top9TallestRockets.get(0).height().get()
                >= top9TallestRockets.get(1).height().get());
    }

    @Test
    void testGetWikiPageForRocket() {
        Map<String, Optional<String>> wikiPagesPerRocket = spaceScanner.getWikiPageForRocket();

        assertEquals(151, wikiPagesPerRocket.size());
        assertEquals(Optional.of("https://en.wikipedia.org/wiki/Tsyklon-3"),
                wikiPagesPerRocket.get("Tsyklon-3"));
        assertEquals(Optional.empty(), wikiPagesPerRocket.get("Ceres-1"));
    }

    @Test
    void testGetWikiPagesForRocketsUsedInMostExpensiveMissions() {
        List<String> wikiPagesPerRocketInMostExpensiveMissions =
                spaceScanner.getWikiPagesForRocketsUsedInMostExpensiveMissions(
                        6,
                        MissionStatus.SUCCESS,
                        RocketStatus.STATUS_RETIRED
                );

        List<String> wikisForActiveRockets =
                spaceScanner.getWikiPagesForRocketsUsedInMostExpensiveMissions(
                        6,
                        MissionStatus.SUCCESS,
                        RocketStatus.STATUS_ACTIVE
                );

        assertEquals(2, wikiPagesPerRocketInMostExpensiveMissions.size());
        assertEquals(6, wikisForActiveRockets.size());
        assertTrue(wikiPagesPerRocketInMostExpensiveMissions
                .contains("https://en.wikipedia.org/wiki/Delta_IV"));

    }

    @Test
    void testSaveMostReliableRocketThrowsTimeFrameMismatchException() {
        assertThrows(TimeFrameMismatchException.class,
                () -> spaceScanner.saveMostReliableRocket(
                        new ByteArrayOutputStream(),
                        LocalDate.of(2020, 1, 1),
                        LocalDate.of(1970, 1, 1)
                ));
    }

    @Test
    void testSaveMostReliableRocket() throws CipherException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        assertDoesNotThrow(() -> encryptionSpaceScanner.saveMostReliableRocket(
                outputStream,
                LocalDate.of(1960, 1, 1),
                LocalDate.of(2023, 1, 1)
        ));

        byte[] encryptedData = outputStream.toByteArray();
        assertNotNull(encryptedData);

        Rocket mostReliableRocket = rijndael.deserializeRocket(new ByteArrayInputStream(encryptedData));
        assertNotNull(mostReliableRocket);
        assertEquals("Falcon 9 Block 5", mostReliableRocket.name());
    }

    @Test
    void testMethodsThrowIllegalArgumentExceptionForInvalidParameters() {
        assertThrows(IllegalArgumentException.class,
                () -> spaceScanner.getAllMissions(null));
        assertThrows(IllegalArgumentException.class,
                () -> spaceScanner.getCompanyWithMostSuccessfulMissions(null, null));
        assertThrows(IllegalArgumentException.class,
                () -> spaceScanner.getTopNLeastExpensiveMissions(
                        -1,
                        MissionStatus.SUCCESS,
                        RocketStatus.STATUS_RETIRED
                ));
        assertThrows(IllegalArgumentException.class,
                () -> spaceScanner.getLocationWithMostSuccessfulMissionsPerCompany(null, null));
        assertThrows(IllegalArgumentException.class,
                () -> spaceScanner.getTopNTallestRockets(0));
        assertThrows(IllegalArgumentException.class,
                () -> spaceScanner.getWikiPagesForRocketsUsedInMostExpensiveMissions(
                        6,
                        null,
                        null
                ));
        assertThrows(IllegalArgumentException.class,
                () -> spaceScanner.saveMostReliableRocket(
                        null,
                        null,
                        LocalDate.of(1999, 1, 1)
                ));
    }

}
