package bg.sofia.uni.fmi.mjt.intelligenthome.center.exceptions;

public class DeviceAlreadyRegisteredException extends Exception {

    public DeviceAlreadyRegisteredException(String message) {
        super(message);
    }

    public DeviceAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

}
