package hu.miskolc.uni.web_blackjack.controller.handlers;

import hu.miskolc.uni.web_blackjack.service.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Map;

/**
 * Handles all exceptions with REST return values, thrown when a controller method encounters one.
 *
 * @author Attila Szőke
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    private static final String messageKey = "msg";

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleException(GameNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(messageKey, e.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleException(UserNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(messageKey, e.getMessage()));
    }

    @ExceptionHandler(GameFullException.class)
    public ResponseEntity<Map<String, Object>> handleException(GameFullException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(messageKey, e.getMessage()));
    }

    @ExceptionHandler(PlayerAlreadyInGameException.class)
    public ResponseEntity<Map<String, Object>> handleException(PlayerAlreadyInGameException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(messageKey, e.getMessage()));
    }

    @ExceptionHandler(PlayerAlreadyStoppedException.class)
    public ResponseEntity<Map<String, Object>> handleException(PlayerAlreadyStoppedException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(messageKey, e.getMessage()));
    }

    @ExceptionHandler(GameAlreadyClosedException.class)
    public ResponseEntity<Map<String, Object>> handleException(GameAlreadyClosedException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(messageKey, e.getMessage()));
    }

    @ExceptionHandler(GameInProgressException.class)
    public ResponseEntity<Map<String, Object>> handleException(GameInProgressException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(messageKey, e.getMessage()));
    }

    @ExceptionHandler(NotThisPlayersTurnException.class)
    public ResponseEntity<Map<String, Object>> handleException(NotThisPlayersTurnException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(messageKey, e.getMessage()));
    }
}
