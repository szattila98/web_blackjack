package hu.miskolc.uni.web_blackjack.service.exceptions;

public class InvalidBidException extends Exception {

    private static final String ERROR_MESSAGE = "Amount of the bid is invalid";

    public InvalidBidException() {
        super(ERROR_MESSAGE);
    }

    public InvalidBidException(String message) {
        super(message);
    }

    public InvalidBidException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidBidException(Throwable cause) {
        super(cause);
    }

    public InvalidBidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
