package hu.miskolc.uni.web_blackjack.service;

import hu.miskolc.uni.web_blackjack.model.Game;
import hu.miskolc.uni.web_blackjack.model.User;
import hu.miskolc.uni.web_blackjack.service.exceptions.*;

import java.util.List;

/**
 * Interface of the Blackjack Service
 * Defines all the possible operations for the service
 *
 * @author Attila Szőke
 * @author Tamás Sólyom
 */
public interface BlackjackService {

    /**
     * Creates a new user.
     *
     * @param name new users name
     * @return user object
     */
    User createUser(String name);

    /**
     * Returns the user with the given id.
     *
     * @param id user's id
     * @throws UserNotFoundException when a user could not be found
     * @return user object
     */
    User getUser(String id) throws UserNotFoundException;

    /**
     * Raise the amount of money on user's currency
     *
     * @param userId id of the user
     * @param money amount of money to raise currency
     * @return user object
     */
    User refillCurrency(String userId, int money) throws UserNotFoundException, InvalidCurrencyException;

    /**
     * Returns every game, except the one in which the userId is already in.
     *
     * @param userId id of the user to filter out
     * @return list of games
     */
    List<Game> getGames(String userId);

    /**
     * Returns every stored info about a game.
     *
     * @param gameId id of the searched game
     * @return game object
     * @throws GameNotFoundException when a game could not be found
     */
    Game getGame(String gameId) throws GameNotFoundException;

    /**
     * Starts a new game of blackjack. Sends an object which stores the game state.
     *
     * @param userId the creators user ID
     * @return user object
     * @throws UserNotFoundException when a user could not be found
     */
    Game createGame(String userId) throws UserNotFoundException;

    /**
     * Joins an ongoing game of blackjack. Sends an object which stores the game state.
     *
     * @return id of the game
     * @exception GameNotFoundException when a game could not be found
     * @exception UserNotFoundException when a user could not be found
     * @exception GameFullException when the game reached it's maximum player count
     * @exception PlayerAlreadyInGameException when the player is already present in the player list
     */
    Game joinGame(String gameId, String userId) throws GameNotFoundException, UserNotFoundException, GameFullException, PlayerAlreadyInGameException, GameAlreadyClosedException;

    /**
     * Gives the current player another card.
     *
     * @param gameId stores the game ID
     * @param userId stores the user ID
     * @return game object
     * @throws PlayerAlreadyStoppedException when the player is stopped in the game and can't get cards
     */
    Game hit(String gameId, String userId) throws PlayerAlreadyStoppedException, GameNotFoundException, NotThisPlayersTurnException;

    /**
     * Ends the current player's turn.
     *
     * @param gameId stores the game ID
     * @param userId stores the user ID
     * @return game object
     * @exception PlayerAlreadyStoppedException when the player is already stopped in the game
     */
    Game stand(String gameId, String userId) throws PlayerAlreadyStoppedException, GameNotFoundException, NotThisPlayersTurnException;

    /**
     * Raise the player's bid in this turn
     *
     * @param gameId stores the game ID
     * @param userId stores the user ID
     * @return game object
     */
    Game raiseBid(String gameId, String userId, int bid) throws PlayerAlreadyStoppedException, GameNotFoundException, NotThisPlayersTurnException, InvalidBidException;

    /**
     * Leaving a game
     *
     * @param gameId stores the game ID
     * @param userId stores the user ID
     * @return game object
     * @exception GameInProgressException when there are still active players in the game
     */
    Game exitGame(String gameId, String userId) throws GameInProgressException, GameNotFoundException;

}
