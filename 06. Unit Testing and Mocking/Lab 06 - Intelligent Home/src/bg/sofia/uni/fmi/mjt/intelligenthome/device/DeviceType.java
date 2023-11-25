package bg.sofia.uni.fmi.mjt.intelligenthome.device;

import bg.sofia.uni.fmi.mjt.intelligenthome.center.exceptions.DeviceNotFoundException;

public enum DeviceType {

    SMART_SPEAKER("SPKR"),
    BULB("BLB"),
    THERMOSTAT("TMST");

    private final String shortName;

    private DeviceType(String name) {
        shortName = name;
    }

    public String getShortName() {
        return shortName;
    }

}
