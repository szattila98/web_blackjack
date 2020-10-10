package hu.miskolc.uni.web_blackjack.repository;

import hu.miskolc.uni.web_blackjack.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Interfaces with the in-memory database to manipulate with stored game objects.
 *
 * @author Attila Szőke
 */
public interface GameRepository extends MongoRepository<Game, String> {

    List<Game> findAllByPlayersUserIdNot(String id);
}
