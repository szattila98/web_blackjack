package hu.miskolc.uni.web_blackjack.service.exceptions;

/**
 * Custom exception class, is thrown if a game has already closed and an attempt is made to join.
 *
 * @author Attila Szőke
 */
public class GameAlreadyClosedException extends Exception {

    private static final String ERROR_MESSAGE = "This game can not be joined, it is already closed!";

    public GameAlreadyClosedException() {
        super(ERROR_MESSAGE);
    }

    public GameAlreadyClosedException(String message) {
        super(message);
    }

    public GameAlreadyClosedException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameAlreadyClosedException(Throwable cause) {
        super(cause);
    }

    public GameAlreadyClosedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
