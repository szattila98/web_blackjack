package hu.miskolc.uni.web_blackjack.service.exceptions;

/**
 * Custom exception class, is thrown if a player is already joined a game before and cannot join again.
 *
 * @author Attila Szőke
 */
public class PlayerAlreadyInGameException extends Exception {

    private static final String ERROR_MESSAGE = "This player already joined this game, cannot join again!";

    public PlayerAlreadyInGameException() {
        super(ERROR_MESSAGE);
    }

    public PlayerAlreadyInGameException(String message) {
        super(message);
    }

    public PlayerAlreadyInGameException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayerAlreadyInGameException(Throwable cause) {
        super(cause);
    }

    public PlayerAlreadyInGameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
