package hu.miskolc.uni.web_blackjack.service.exceptions;

public class InvalidCurrencyException extends Exception {

    private static final String ERROR_MESSAGE = "Amount of the money is invalid";

    public InvalidCurrencyException() {
        super(ERROR_MESSAGE);
    }

    public InvalidCurrencyException(String message) {
        super(message);
    }

    public InvalidCurrencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCurrencyException(Throwable cause) {
        super(cause);
    }

    public InvalidCurrencyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
