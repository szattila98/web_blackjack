package hu.miskolc.uni.web_blackjack.repository;

import hu.miskolc.uni.web_blackjack.model.GameState;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaces with the in-memory database to manipulate with stored game states.
 *
 * @author Attila Szőke
 */
public interface GameStateRepository extends JpaRepository<GameState, Long> {
}
