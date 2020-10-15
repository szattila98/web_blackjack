package hu.miskolc.uni.web_blackjack.service.exceptions;

/**
 * Custom exception class, is thrown if a game can not be found in the database.
 *
 * @author Attila Szőke
 */
public class GameNotFoundException extends Exception {

    private static final String ERROR_MESSAGE = "Game with this ID could not be found!";

    public GameNotFoundException() {
        super(ERROR_MESSAGE);
    }

    public GameNotFoundException(String message) {
        super(message);
    }

    public GameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameNotFoundException(Throwable cause) {
        super(cause);
    }

    public GameNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
