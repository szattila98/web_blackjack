package hu.miskolc.uni.web_blackjack.service.exceptions;

/**
 * Custom exception class, is thrown if a user can not be found in the database.
 *
 * @author Attila Szőke
 */
public class UserNotFoundException extends Exception {

    private static final String ERROR_MESSAGE = "User can not be found!";

    public UserNotFoundException() {
        super(ERROR_MESSAGE);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
