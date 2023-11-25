package bg.sofia.uni.fmi.mjt.intelligenthome.device;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class IoTDeviceTest {

    private static IoTDevice device;

    @BeforeAll
    static void setUpTestCase() {
        device = new WiFiThermostat(
                "testDevice",
                150,
                LocalDateTime.of(2023, 11, 22, 12, 30)
        );
    }

    @BeforeEach
    void setUp() {
        IoTDeviceBase.uniqueNumberDevice = 0;
    }

    @AfterAll
    static void tearDownTestCase() {
        IoTDeviceBase.uniqueNumberDevice = 0;
    }

    @Test
    void testGetIdForEveryDeviceType() {
        IoTDevice alexa = new AmazonAlexa("alexa", 100,
                LocalDateTime.now());
        IoTDevice bulb = new RgbBulb("bulb", 100,
                LocalDateTime.now());
        IoTDevice thermostat = new WiFiThermostat("thermostat", 100,
                LocalDateTime.now());

        String outputMessage = "getId() should return a string in format type-name-uniqueNumber!";
        assertEquals("SPKR-alexa-0", alexa.getId(), outputMessage);
        assertEquals("BLB-bulb-1", bulb.getId(), outputMessage);
        assertEquals("TMST-thermostat-2", thermostat.getId(), outputMessage);
    }

    @Test
    void testGetPowerConsumptionKWh() {
        assertEquals(Duration.between(LocalDateTime.of(2023, 11, 22, 12, 30),
                        LocalDateTime.now()).toHours() * 150,
                device.getPowerConsumptionKWh(),
                "getPowerConsumptionKWh() should return product of powerConsumption and hours since installation!");
    }

    @Test
    void testRegistrationReturnsCorrectTime() {
        device.setRegistration(LocalDateTime.now().minusHours(10));
        assertEquals(10, device.getRegistration(),
                "getRegistration() should return amount of hours since registration!");
    }

    @Test
    void testIoTDeviceEqualsChecksIdOnly() {
        IoTDeviceBase.uniqueNumberDevice = 0;
        IoTDevice d1 = new RgbBulb("bulb1", 70, LocalDateTime.now().minusHours(2));
        IoTDevice d2 = new RgbBulb("bulb1", 70, LocalDateTime.now().minusHours(2));
        IoTDeviceBase.uniqueNumberDevice = 0;
        IoTDevice d3 = new RgbBulb("bulb1", 77, LocalDateTime.now().minusHours(2));

        assertNotEquals(d1, d2);
        assertNotEquals(d2, d3);
        assertEquals(d1, d3);
    }

    @Test
    void testIoTDeviceHashCode() {
        IoTDeviceBase.uniqueNumberDevice = 0;
        IoTDevice d1 = new AmazonAlexa("alexa1", 70, LocalDateTime.now().minusHours(2));
        IoTDevice d2 = new AmazonAlexa("alexa2", 70, LocalDateTime.now().minusHours(2));
        IoTDeviceBase.uniqueNumberDevice = 0;
        IoTDevice d3 = new AmazonAlexa("alexa1", 77, LocalDateTime.now().minusHours(2));

        assertEquals(d1.hashCode(), d3.hashCode());
        assertNotEquals(d1.hashCode(), d2.hashCode());
        assertNotEquals(d2.hashCode(), d3.hashCode());
    }

}
