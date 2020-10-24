package hu.miskolc.uni.web_blackjack.service.exceptions;

public class PlayerAlreadyStoppedException extends Exception {

    private static final String ERROR_MESSAGE = "This player has already stopped in this game, cannot get more cards.";

    public PlayerAlreadyStoppedException() {super(ERROR_MESSAGE);}

    public PlayerAlreadyStoppedException(String message) {
        super(message);
    }

    public PlayerAlreadyStoppedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayerAlreadyStoppedException(Throwable cause) {
        super(cause);
    }

    public PlayerAlreadyStoppedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
