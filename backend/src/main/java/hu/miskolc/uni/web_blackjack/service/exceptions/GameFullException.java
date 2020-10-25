package hu.miskolc.uni.web_blackjack.service.exceptions;

/**
 * Custom exception class, is thrown if a game has the maximum of 2 players and an attempt is made to join.
 *
 * @author Attila Szőke
 */
public class GameFullException extends Exception {

    private static final String ERROR_MESSAGE = "This game can not be joined, it is already full!";

    public GameFullException() {
        super(ERROR_MESSAGE);
    }

    public GameFullException(String message) {
        super(message);
    }

    public GameFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameFullException(Throwable cause) {
        super(cause);
    }

    public GameFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
