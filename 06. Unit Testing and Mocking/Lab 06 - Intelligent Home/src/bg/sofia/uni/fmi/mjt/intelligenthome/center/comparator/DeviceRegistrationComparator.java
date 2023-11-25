package bg.sofia.uni.fmi.mjt.intelligenthome.center.comparator;

import bg.sofia.uni.fmi.mjt.intelligenthome.device.IoTDevice;

import java.util.Comparator;

public class DeviceRegistrationComparator implements Comparator<IoTDevice> {

    @Override
    public int compare(IoTDevice d1, IoTDevice d2) {
        return (int) (d1.getRegistration() - d2.getRegistration());
    }

}
