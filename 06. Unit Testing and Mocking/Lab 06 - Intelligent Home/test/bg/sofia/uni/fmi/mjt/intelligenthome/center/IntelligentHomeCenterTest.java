package bg.sofia.uni.fmi.mjt.intelligenthome.center;

import bg.sofia.uni.fmi.mjt.intelligenthome.center.exceptions.DeviceAlreadyRegisteredException;
import bg.sofia.uni.fmi.mjt.intelligenthome.center.exceptions.DeviceNotFoundException;
import bg.sofia.uni.fmi.mjt.intelligenthome.device.IoTDevice;
import bg.sofia.uni.fmi.mjt.intelligenthome.storage.DeviceStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        assertThrows(IllegalArgumentException.class, () -> intelligentHomeCenter.register(null),
                "register() should receive a device that is not null!");
    }

    @Test
    void testRegisterThrowsExceptionIfDeviceExists() {
        IoTDevice deviceMock = mock();
        when(storageMock.exists(deviceMock.getId())).thenReturn(true);

        assertThrows(DeviceAlreadyRegisteredException.class, () -> intelligentHomeCenter.register(deviceMock),
                "register() should receive a device that isn't already registered!");
    }

    @Test
    void testUnregisterThrowsExceptionIfDeviceIsNull() {
        assertThrows(IllegalArgumentException.class, () -> intelligentHomeCenter.unregister(null),
                "unregister() should receive a device that is not null!");
    }

    @Test
    void testUnregisterThrowsExceptionIfDeviceDoesntExist() {
        IoTDevice deviceMock = mock();
        when(storageMock.exists(deviceMock.getId())).thenReturn(false);

        assertThrows(DeviceNotFoundException.class, () -> intelligentHomeCenter.unregister(deviceMock),
                "unregister() should receive a device that is already registered!");
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

        assertThrows(DeviceNotFoundException.class, () -> intelligentHomeCenter.getDeviceById(deviceMock.getId()),
                "getDeviceById() should receive an id of a device that is already registered!");
    }

    @Test
    void testGetDeviceQuantityPerTypeThrowsExceptionIfTypeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> intelligentHomeCenter.getDeviceQuantityPerType(null),
                "getDeviceQuantityType() should receive deviceType that is not null!");
    }

    @Test
    void testGetTopNDevicesByPowerConsumptionThrowsExceptionIfNIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> intelligentHomeCenter.getTopNDevicesByPowerConsumption(-1),
                "getTopNDevicesByPowerConsumption() should receive a number >= 0!");
    }

    @Test
    void testGetFirstNDevicesByRegistrationThrowsExceptionIfNIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> intelligentHomeCenter.getFirstNDevicesByRegistration(-1),
                "getFirstNDevicesByRegistration() should receive a number >= 0!");
    }

}
