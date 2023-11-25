package bg.sofia.uni.fmi.mjt.intelligenthome.center.exceptions;

public class DeviceNotFoundException extends Exception {

    public DeviceNotFoundException(String message) {
        super(message);
    }

    public DeviceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
