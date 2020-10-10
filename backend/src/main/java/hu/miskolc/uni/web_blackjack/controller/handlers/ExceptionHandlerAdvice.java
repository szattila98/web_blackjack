package hu.miskolc.uni.web_blackjack.controller.handlers;

import hu.miskolc.uni.web_blackjack.service.exceptions.GameNotFoundException;
import hu.miskolc.uni.web_blackjack.service.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    // TODO log exceptions

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity handleException(GameNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Game with this ID could not be found!");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleException(UserNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("User with this ID could not be found!");
    }
}
