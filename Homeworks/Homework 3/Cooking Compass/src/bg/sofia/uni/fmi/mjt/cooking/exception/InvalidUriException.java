package bg.sofia.uni.fmi.mjt.cooking.exception;

public class InvalidUriException extends Exception {

    public InvalidUriException(String message) {
        super(message);
    }

    public InvalidUriException(String message, Throwable cause) {
        super(message, cause);
    }

}
