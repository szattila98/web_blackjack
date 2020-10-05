package hu.miskolc.uni.web_blackjack.service;

import hu.miskolc.uni.web_blackjack.model.GameState;

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
    GameState start();

    /**
     * Joins an ongoing game of blackjack. Sends an object which stores the game state.
     *
     * @return GameState - stores the game state
     */
    GameState start(GameState gameState);

    /**
     * Gives the current player another card.
     *
     * @param gameState - stores the game state
     * @return GameState - stores the game state
     */
    GameState hit(GameState gameState);

    /**
     * Ends the current player's turn.
     *
     * @param gameState - stores the game state
     * @return GameState - stores the game state
     */
    GameState stand(GameState gameState);

    /**
     * Surrenders the round of the current player with them only losing half of their wager.
     *
     * @param gameState - stores the game state
     * @return GameState - stores the game state
     */
    GameState surrender(GameState gameState);

}
