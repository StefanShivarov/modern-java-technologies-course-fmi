package bg.sofia.uni.fmi.mjt.cooking.exception;

public class RequiredQueryMissingException extends Exception {

    public RequiredQueryMissingException(String message) {
        super(message);
    }

    public RequiredQueryMissingException(String message, Throwable cause) {
        super(message, cause);
    }

}
