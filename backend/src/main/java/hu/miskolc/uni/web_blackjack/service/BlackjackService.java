package hu.miskolc.uni.web_blackjack.service;

import hu.miskolc.uni.web_blackjack.controller.dto.GameStateDTO;

/**
 * Interface of the Blackjack Service
 * Defines all the possible operations for the service
 *
 * @author Attila Szőke
 */
public interface BlackjackService {

    /**
     * Starts a game of blackjack. Sends a dto which stores the game state.
     *
     * @return GameStateDTO - stores the game state
     */
    GameStateDTO start();

    /**
     * Gives the current player another card.
     *
     * @param dto - stores the game state
     * @return GameStateDTO - stores the game state
     */
    GameStateDTO hit(GameStateDTO dto);

    /**
     * Ends the current player's turn.
     *
     * @param dto - stores the game state
     * @return GameStateDTO - stores the game state
     */
    GameStateDTO stand(GameStateDTO dto);

    /**
     * Surrenders the round of the current player.
     *
     * @param dto - stores the game state
     * @return GameStateDTO - stores the game state
     */
    GameStateDTO surrender(GameStateDTO dto);

}
