package bg.sofia.uni.fmi.mjt.cooking.exception;

public class InvalidAppCredentialsException extends Exception {

    public InvalidAppCredentialsException(String message) {
        super(message);
    }

    public InvalidAppCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

}
