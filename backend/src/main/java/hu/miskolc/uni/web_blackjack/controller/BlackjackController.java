package hu.miskolc.uni.web_blackjack.controller;

import hu.miskolc.uni.web_blackjack.model.Card;
import hu.miskolc.uni.web_blackjack.model.Game;
import hu.miskolc.uni.web_blackjack.model.User;
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

    /**
     * Adds a new user object to the database.
     *
     * @param name - name of the new user
     * @return Created user object
     */
    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestParam String name) {
        return null;
    }

    /**
     * List all games, except the one in which the current user is already a player.
     *
     * @param userId - id of the current user
     * @return list of games
     */
    @GetMapping("/game")
    public ResponseEntity<List<Game>> listGames(@RequestParam Long userId) {
        return null;
    }

    /**
     * Fetches the details of a particular game.
     *
     * @param gameId - id of the game
     * @return game details
     */
    @GetMapping("/game/{gameId}")
    public ResponseEntity<Game> gameDetails(@PathVariable Long gameId) {
        return null;
    }

    /**
     * Adds a game object to the database.
     *
     * @param userId - creator id
     * @return new game details
     */
    @PostMapping("/game")
    public ResponseEntity<Game> addGame(@RequestParam Long userId) {
        return null;
    }

    /**
     * Joins a player into a game.
     *
     * @param gameId - id of the game
     * @param userId - id of the user
     * @return game details
     */
    @PostMapping("/game/{gameId}/user/{userId}/join")
    public ResponseEntity<Game> joinGame(@PathVariable Long gameId, @PathVariable Long userId) {
        return null;
    }

    /**
     * Gives a player another card.
     *
     * @param gameId - id of the game
     * @param userId - id of the user
     * @return game details
     */
    @PostMapping("/api/game/{gameId}/user/{userId}/hit")
    public ResponseEntity<Card> hit(@PathVariable Long gameId, @PathVariable Long userId) {
        return null;
        //Bad request ha nem az adott játékos következik
    }

    /**
     * Stops a players turn.
     *
     * @param gameId - id of the game
     * @param userId - id of the user
     */
    @PostMapping("/api/game/{gameId}/user/{userId}/stand")
    public ResponseEntity<Void> stand(@PathVariable Long gameId, @PathVariable Long userId) {
        return null;
        //Bad request ha nem az adott játékos következik
    }
}
