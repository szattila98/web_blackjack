package hu.miskolc.uni.web_blackjack.controller;

import hu.miskolc.uni.web_blackjack.controller.dtos.UserInput;
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
     * @param userNameDTO dto containing the name of the new user
     * @return Created user object
     */
    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody UserInput userNameDTO) {
        return ResponseEntity.ok(blackjackService.createUser(userNameDTO.getInputValue()));
    }

    /**
     * Returns the user with the given id.
     *
     * @param id user id
     * @return user object
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) throws UserNotFoundException {
        return ResponseEntity.ok(blackjackService.getUser(id));
    }

    /**
     * Increase the amount of money on a user's currency
     *
     * @param userId id of the user
     * @param money amount of money to increase currency
     * @return user object
     */
    @PostMapping("/user/{userId}/currency/{money}")
    public ResponseEntity<User> increaseCurrency(@PathVariable String userId, @PathVariable int money) throws UserNotFoundException, InvalidCurrencyException {
        return ResponseEntity.ok(blackjackService.refillCurrency(userId, money));
    }

    /**
     * List all games, except the one in which the current user is already a player.
     *
     * @param userId id of the current user
     * @return list of games
     */
    @GetMapping("/game")
    public ResponseEntity<List<Game>> listGames(@RequestParam String userId) {
        return ResponseEntity.ok(blackjackService.getGames(userId));
    }

    /**
     * Fetches the details of a particular game.
     *
     * @param gameId id of the game
     * @return game details
     */
    @GetMapping("/game/{gameId}")
    public ResponseEntity<Game> gameDetails(@PathVariable String gameId) throws GameNotFoundException {
        return ResponseEntity.ok(blackjackService.getGame(gameId));
    }

    /**
     * Records a new game object.
     *
     * @param creatorIdDTO dto containing the creator id
     * @return new game details
     */
    @PostMapping("/game")
    public ResponseEntity<Game> addGame(@RequestBody UserInput creatorIdDTO) throws UserNotFoundException {
        return ResponseEntity.ok(blackjackService.createGame(creatorIdDTO.getInputValue()));
    }

    /**
     * Joins a player into a game.
     *
     * @param gameId id of the game
     * @param userId id of the user
     * @return game details
     */
    @PostMapping("/game/{gameId}/user/{userId}/join")
    public ResponseEntity<Game> joinGame(@PathVariable String gameId, @PathVariable String userId) throws UserNotFoundException, GameAlreadyClosedException, GameFullException, PlayerAlreadyInGameException, GameNotFoundException {
        return ResponseEntity.ok(blackjackService.joinGame(gameId, userId));
    }

    /**
     * Gives a player another card.
     *
     * @param gameId id of the game
     * @param userId id of the user
     * @return game details
     */
    @PostMapping("/game/{gameId}/user/{userId}/hit")
    public ResponseEntity<Game> hit(@PathVariable String gameId, @PathVariable String userId) throws PlayerAlreadyStoppedException, GameNotFoundException, NotThisPlayersTurnException {
        return ResponseEntity.ok(blackjackService.hit(gameId, userId));
    }

    /**
     * Stops a players turn.
     *
     * @param gameId id of the game
     * @param userId id of the user
     */
    @PostMapping("/game/{gameId}/user/{userId}/stand")
    public ResponseEntity<Game> stand(@PathVariable String gameId, @PathVariable String userId) throws PlayerAlreadyStoppedException, GameNotFoundException, NotThisPlayersTurnException {
        return ResponseEntity.ok(blackjackService.stand(gameId, userId));
    }

    /**
     * Raise the player's bid
     *
     * @param gameId id of the game
     * @param userId id of the user
     * @param bid amount of bid
     */
    @PostMapping("/game/{gameId}/user/{userId}/bid/{bid}")
    public ResponseEntity<Game> raiseBid(@PathVariable String gameId, @PathVariable String userId, @PathVariable int bid) throws InvalidBidException, PlayerAlreadyStoppedException, GameNotFoundException, NotThisPlayersTurnException {
        return ResponseEntity.ok(blackjackService.raiseBid(gameId, userId, bid));
    }

    /**
     * Leaving a game
     *
     * @param gameId id of the game
     * @param userId id of the user
     */
    @PostMapping("/game/{gameId}/user/{userId}/exit")
    public ResponseEntity<Game> exitGame(@PathVariable String gameId, @PathVariable String userId) throws GameInProgressException, GameNotFoundException {
        return ResponseEntity.ok(blackjackService.exitGame(gameId, userId));
    }

}
