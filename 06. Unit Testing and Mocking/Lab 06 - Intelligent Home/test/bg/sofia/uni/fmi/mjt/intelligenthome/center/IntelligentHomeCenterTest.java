package bg.sofia.uni.fmi.mjt.intelligenthome.center;

import bg.sofia.uni.fmi.mjt.intelligenthome.center.exceptions.DeviceAlreadyRegisteredException;
import bg.sofia.uni.fmi.mjt.intelligenthome.center.exceptions.DeviceNotFoundException;
import bg.sofia.uni.fmi.mjt.intelligenthome.device.DeviceType;
import bg.sofia.uni.fmi.mjt.intelligenthome.device.IoTDevice;
import bg.sofia.uni.fmi.mjt.intelligenthome.storage.DeviceStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IntelligentHomeCenterTest {

    @Mock
    private DeviceStorage storageMock;

    @InjectMocks
    private IntelligentHomeCenter intelligentHomeCenter;

    @Test
    void testRegisterThrowsExceptionIfDeviceIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> intelligentHomeCenter.register(null),
                "register() should receive a device that is not null!");
    }

    @Test
    void testRegisterThrowsExceptionIfDeviceExists() {
        IoTDevice deviceMock = mock();
        when(storageMock.exists(deviceMock.getId())).thenReturn(true);

        assertThrows(DeviceAlreadyRegisteredException.class,
                () -> intelligentHomeCenter.register(deviceMock),
                "register() should receive a device that isn't already registered!");
    }

    @Test
    void testRegisterDeviceRegistersSuccessfully() throws DeviceNotFoundException {
        IoTDevice deviceMock = mock();
        when(deviceMock.getId()).thenReturn("SPKR-device-0");

        when(storageMock.exists(deviceMock.getId())).thenReturn(false);

        assertDoesNotThrow(() -> intelligentHomeCenter.register(deviceMock),
                "register() should not throw exceptions if a valid unregistered device is passed!");

        when(storageMock.exists(deviceMock.getId())).thenReturn(true);
        when(storageMock.get(deviceMock.getId())).thenReturn(deviceMock);
        assertEquals(deviceMock, intelligentHomeCenter.getDeviceById(deviceMock.getId()),
                "If register() is successful, then getDeviceById() should return the device!");
    }

    @Test
    void testUnregisterThrowsExceptionIfDeviceIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> intelligentHomeCenter.unregister(null),
                "unregister() should receive a device that is not null!");
    }

    @Test
    void testUnregisterThrowsExceptionIfDeviceDoesntExist() {
        IoTDevice deviceMock = mock();
        when(storageMock.exists(deviceMock.getId())).thenReturn(false);

        assertThrows(DeviceNotFoundException.class,
                () -> intelligentHomeCenter.unregister(deviceMock),
                "unregister() should receive a device that is already registered!");
    }

    @Test
    void testUnregisterDeviceUnregistersSuccessfully() {
        IoTDevice deviceMock = mock();
        when(deviceMock.getId()).thenReturn("SPKR-device-0");

        when(storageMock.exists(deviceMock.getId())).thenReturn(true);

        assertDoesNotThrow(() -> intelligentHomeCenter.unregister(deviceMock));
    }

    @Test
    void testGetDeviceByIdThrowsExceptionIfIdIsNullOrBlank() {
        String outputMessage = "getDeviceById() should receive an id that is not null or blank!";

        assertThrows(IllegalArgumentException.class, () -> intelligentHomeCenter.getDeviceById(null),
                outputMessage);

        assertThrows(IllegalArgumentException.class, () -> intelligentHomeCenter.getDeviceById("   "),
                outputMessage);
    }

    @Test
    void testGetDeviceByIdThrowsExceptionIfDeviceWithIdDoesntExist() {
        IoTDevice deviceMock = mock();
        when(deviceMock.getId()).thenReturn("BLB-device-0");
        when(storageMock.exists(deviceMock.getId())).thenReturn(false);

        assertThrows(DeviceNotFoundException.class,
                () -> intelligentHomeCenter.getDeviceById(deviceMock.getId()),
                "getDeviceById() should receive an id of a device that is already registered!");
    }

    @Test
    void testGetDeviceByIdReturnsCorrectDevice() throws DeviceNotFoundException {
        IoTDevice deviceMock = mock();
        when(deviceMock.getId()).thenReturn("TMST-device-0");

        when(storageMock.exists(deviceMock.getId())).thenReturn(true);
        when(storageMock.get(deviceMock.getId())).thenReturn(deviceMock);

        assertEquals(deviceMock, intelligentHomeCenter.getDeviceById(deviceMock.getId()));
    }

    @Test
    void testGetDeviceQuantityPerTypeThrowsExceptionIfTypeIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> intelligentHomeCenter.getDeviceQuantityPerType(null),
                "getDeviceQuantityType() should receive deviceType that is not null!");
    }

    @Test
    void testGetDeviceQuantityPerTypeReturnsCorrectQuantity() {
        IoTDevice d1 = mock();
        when(d1.getType()).thenReturn(DeviceType.SMART_SPEAKER);

        IoTDevice d2 = mock();
        when(d2.getType()).thenReturn(DeviceType.BULB);

        IoTDevice d3 = mock();
        when(d3.getType()).thenReturn(DeviceType.BULB);

        when(storageMock.listAll()).thenReturn(List.of(d1, d2, d3));

        assertEquals(0, intelligentHomeCenter.getDeviceQuantityPerType(DeviceType.THERMOSTAT));
        assertEquals(1, intelligentHomeCenter.getDeviceQuantityPerType(DeviceType.SMART_SPEAKER));
        assertEquals(2, intelligentHomeCenter.getDeviceQuantityPerType(DeviceType.BULB));
    }

    @Test
    void testGetTopNDevicesByPowerConsumptionThrowsExceptionIfNIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> intelligentHomeCenter.getTopNDevicesByPowerConsumption(-1),
                "getTopNDevicesByPowerConsumption() should receive a number >= 0!");
    }

    @Test
    void testGetTopNDevicesByPowerConsumptionReturnsCorrectCollection() {
        IoTDevice d1 = mock();
        when(d1.getId()).thenReturn("BLB-bulb-0");
        when(d1.getPowerConsumptionKWh()).thenReturn(60L);

        IoTDevice d2 = mock();
        when(d2.getId()).thenReturn("SPKR-alexa-1");
        when(d2.getPowerConsumptionKWh()).thenReturn(120L);

        IoTDevice d3 = mock();
        when(d3.getId()).thenReturn("TMST-thermostat-2");
        when(d3.getPowerConsumptionKWh()).thenReturn(90L);

        when(storageMock.listAll()).thenReturn(List.of(d1, d2, d3));

        assertEquals(List.of(d2.getId(), d3.getId()),
                intelligentHomeCenter.getTopNDevicesByPowerConsumption(2));

        assertEquals(List.of(d2.getId(), d3.getId(), d1.getId()),
                intelligentHomeCenter.getTopNDevicesByPowerConsumption(5));
    }

    @Test
    void testGetFirstNDevicesByRegistrationThrowsExceptionIfNIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> intelligentHomeCenter.getFirstNDevicesByRegistration(-1),
                "getFirstNDevicesByRegistration() should receive a number >= 0!");
    }

    @Test
    void testGetFirstNDevicesByRegistrationReturnsCorrectCollection() {
        IoTDevice d1 = mock();
        when(d1.getRegistration()).thenReturn(60L);

        IoTDevice d2 = mock();
        when(d2.getRegistration()).thenReturn(120L);

        IoTDevice d3 = mock();
        when(d3.getRegistration()).thenReturn(90L);

        when(storageMock.listAll()).thenReturn(List.of(d1, d2, d3));

        assertEquals(List.of(d2, d3),
                intelligentHomeCenter.getFirstNDevicesByRegistration(2));

        assertEquals(List.of(d2, d3, d1),
                intelligentHomeCenter.getFirstNDevicesByRegistration(5));
    }

}
