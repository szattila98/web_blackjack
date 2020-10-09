package hu.miskolc.uni.web_blackjack.service;

import hu.miskolc.uni.web_blackjack.model.Game;

/**
 * Interface of the Blackjack Service
 * Defines all the possible operations for the service
 *
 * @author Attila Szőke
 */
public interface BlackjackService {

    /**
     * Starts a new game of blackjack. Sends an object which stores the game state.
     *
     * @return GameState - stores the game state
     */
    Game start();

    /**
     * Joins an ongoing game of blackjack. Sends an object which stores the game state.
     *
     * @return id - id of the game
     */
    Game start(Long id);

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

    /**
     * Surrenders the round of the current player with them only losing half of their wager.
     *
     * @param game - stores the game state
     * @return GameState - stores the game state
     */
    Game surrender(Game game);

}
