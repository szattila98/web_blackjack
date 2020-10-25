package hu.miskolc.uni.web_blackjack.service.exceptions;

public class GameInProgressException extends Exception {

    private static final String ERROR_MESSAGE = "The game is in progress, the player can't leave it now.";

    public GameInProgressException() {super(ERROR_MESSAGE);}

    public GameInProgressException(String message) {
        super(message);
    }

    public GameInProgressException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameInProgressException(Throwable cause) {
        super(cause);
    }

    public GameInProgressException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
