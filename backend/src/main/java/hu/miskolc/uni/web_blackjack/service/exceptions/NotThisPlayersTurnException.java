package hu.miskolc.uni.web_blackjack.service.exceptions;

public class NotThisPlayersTurnException extends Exception {

    private static final String ERROR_MESSAGE = "A player wanted to hit or stand, but it is not their turn to do so.";

    public NotThisPlayersTurnException() {
        super(ERROR_MESSAGE);
    }

    public NotThisPlayersTurnException(String message) {
        super(message);
    }

    public NotThisPlayersTurnException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotThisPlayersTurnException(Throwable cause) {
        super(cause);
    }

    public NotThisPlayersTurnException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
