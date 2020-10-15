package hu.miskolc.uni.web_blackjack.controller;

import hu.miskolc.uni.web_blackjack.model.Card;
import hu.miskolc.uni.web_blackjack.model.Game;
import hu.miskolc.uni.web_blackjack.model.User;
import hu.miskolc.uni.web_blackjack.service.BlackjackService;
import hu.miskolc.uni.web_blackjack.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Carries out the blackjack game operations.
 *
 * @author Attila Szőke
 */
@RestController
@RequestMapping("/api")
public class BlackjackController {

    private final BlackjackService blackjackService;

    @Autowired
    public BlackjackController(BlackjackService blackjackService) {
        this.blackjackService = blackjackService;
    }

    /**
     * Records a new user.
     *
     * @param name - name of the new user
     * @return Created user object
     */
    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestParam String name) {
        return ResponseEntity.ok(blackjackService.createUser(name));
    }

    /**
     * List all games, except the one in which the current user is already a player.
     *
     * @param userId - id of the current user
     * @return list of games
     */
    @GetMapping("/game")
    public ResponseEntity<List<Game>> listGames(@RequestParam String userId) {
        return ResponseEntity.ok(blackjackService.getGames(userId));
    }

    /**
     * Fetches the details of a particular game.
     *
     * @param gameId - id of the game
     * @return game details
     */
    @GetMapping("/game/{gameId}")
    public ResponseEntity<Game> gameDetails(@PathVariable String gameId) throws GameNotFoundException {
        return ResponseEntity.ok(blackjackService.getGame(gameId));
    }

    /**
     * Records a new game object.
     *
     * @param userId - creator id
     * @return new game details
     */
    @PostMapping("/game")
    public ResponseEntity<Game> addGame(@RequestParam String userId) throws UserNotFoundException {
        return ResponseEntity.ok(blackjackService.createGame(userId));
    }

    /**
     * Joins a player into a game.
     *
     * @param gameId - id of the game
     * @param userId - id of the user
     * @return game details
     */
    @PostMapping("/game/{gameId}/user/{userId}/join")
    public ResponseEntity<Game> joinGame(@PathVariable String gameId, @PathVariable String userId) throws UserNotFoundException, GameAlreadyClosedException, GameFullException, PlayerAlreadyInGameException, GameNotFoundException {
        return ResponseEntity.ok(blackjackService.joinGame(gameId, userId));
    }

    /**
     * Gives a player another card.
     *
     * @param gameId - id of the game
     * @param userId - id of the user
     * @return game details
     */
    @PostMapping("/api/game/{gameId}/user/{userId}/hit")
    public ResponseEntity<Card> hit(@PathVariable String gameId, @PathVariable String userId) {
        return null;
        // TODO Bad request ha nem az adott játékos következik
    }

    /**
     * Stops a players turn.
     *
     * @param gameId - id of the game
     * @param userId - id of the user
     */
    @PostMapping("/api/game/{gameId}/user/{userId}/stand")
    public ResponseEntity<Void> stand(@PathVariable String gameId, @PathVariable String userId) {
        return null;
        // TODO Bad request ha nem az adott játékos következik
    }
}
