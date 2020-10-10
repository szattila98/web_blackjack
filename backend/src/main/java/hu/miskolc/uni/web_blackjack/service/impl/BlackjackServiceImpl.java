package hu.miskolc.uni.web_blackjack.service.impl;

import hu.miskolc.uni.web_blackjack.model.Card;
import hu.miskolc.uni.web_blackjack.model.Game;
import hu.miskolc.uni.web_blackjack.model.Player;
import hu.miskolc.uni.web_blackjack.model.User;
import hu.miskolc.uni.web_blackjack.model.enums.GameStateType;
import hu.miskolc.uni.web_blackjack.model.enums.PlayerStateType;
import hu.miskolc.uni.web_blackjack.repository.GameRepository;
import hu.miskolc.uni.web_blackjack.repository.UserRepository;
import hu.miskolc.uni.web_blackjack.service.BlackjackService;
import hu.miskolc.uni.web_blackjack.service.exceptions.GameNotFoundException;
import hu.miskolc.uni.web_blackjack.service.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of Blackjack Service interface.
 *
 * @author Attila Szőke
 */
@Service
public class BlackjackServiceImpl implements BlackjackService {

    // TODO logging

    private UserRepository userRepository;
    private GameRepository gameRepository;

    @Autowired
    public BlackjackServiceImpl(UserRepository userRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User createUser(String name) {
        return userRepository.insert(new User(name));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Game> getGames(String userId) {
        return gameRepository.findAllByPlayersUserIdNot(userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game getGame(String gameId) throws GameNotFoundException {
        return gameRepository.findById(gameId).orElseThrow(GameNotFoundException::new);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game createGame(String userId) throws UserNotFoundException {
        User creator = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<Card> cards = new ArrayList<>();
        // TODO give cards to begin the game with, when the card dealer logic is done
        Game game = new Game();
        List<Player> initialPlayers = new ArrayList<>();
        initialPlayers.add(new Player(creator, cards, 0, PlayerStateType.IN_GAME));
        game.setPlayers(initialPlayers);
        game.setDealtCards(new ArrayList<>());
        // TODO add the cards here too so they won't be dealt again
        game.setState(GameStateType.PENDING);
        return gameRepository.insert(game);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game start() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game start(Long id) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game hit(Game game) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game stand(Game game) {
        return null;
    }
}
