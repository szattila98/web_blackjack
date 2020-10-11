package hu.miskolc.uni.web_blackjack.service;

import hu.miskolc.uni.web_blackjack.model.Game;
import hu.miskolc.uni.web_blackjack.model.User;
import hu.miskolc.uni.web_blackjack.service.exceptions.GameNotFoundException;
import hu.miskolc.uni.web_blackjack.service.exceptions.UserNotFoundException;

import java.util.List;

/**
 * Interface of the Blackjack Service
 * Defines all the possible operations for the service
 *
 * @author Attila Szőke
 */
public interface BlackjackService {

    /**
     * Creates a new user.
     *
     * @param name - new users name
     * @return user object
     */
    User createUser(String name);

    /**
     * Returns every game, except the one in which the userId is already in.
     *
     * @param userId - id of the user to filter out
     * @return list of games
     */
    List<Game> getGames(String userId);

    /**
     * Returns every stored info about a game.
     *
     * @param gameId id of the searched game
     * @return game object
     */
    Game getGame(String gameId) throws GameNotFoundException;

    /**
     * Starts a new game of blackjack. Sends an object which stores the game state.
     *
     * @param userId - the creators user ID
     * @return user object
     */
    Game createGame(String userId) throws UserNotFoundException;

    /**
     * Joins an ongoing game of blackjack. Sends an object which stores the game state.
     *
     * @return id - id of the game
     */
    Game joinGame(Long id);

    /**
     * Gives the current player another card.
     *
     * @param game - stores the game state
     * @return GameState - stores the game state
     */
    Game hit(Game game);

    /**
     * Ends the current player's turn.
     *
     * @param game - stores the game state
     * @return GameState - stores the game state
     */
    Game stand(Game game);

}
