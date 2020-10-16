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

    /**
     * Finds all the games, where among the players a user's id is not present. Implementation is generated with Spring Data derived methods.
     *
     * @param userId the user's Id to filter with
     * @return list of games
     */
    List<Game> findAllByPlayersUserIdNot(String userId);
}
