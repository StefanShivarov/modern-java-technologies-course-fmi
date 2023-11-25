package bg.sofia.uni.fmi.mjt.intelligenthome.device;

import java.time.LocalDateTime;

public class AmazonAlexa extends IoTDeviceBase {

    public AmazonAlexa(String name, double powerConsumption, LocalDateTime installationDateTime) {
        super(name, powerConsumption, installationDateTime);
        type = DeviceType.SMART_SPEAKER;
        id = String.format("%s-%s-%d", getType().getShortName(), getName(), uniqueNumberDevice++);
    }

}
