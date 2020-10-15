package hu.miskolc.uni.web_blackjack.service.impl;

import hu.miskolc.uni.web_blackjack.model.Card;
import hu.miskolc.uni.web_blackjack.model.Game;
import hu.miskolc.uni.web_blackjack.model.Player;
import hu.miskolc.uni.web_blackjack.model.User;
import hu.miskolc.uni.web_blackjack.model.enums.ColorType;
import hu.miskolc.uni.web_blackjack.model.enums.GameStateType;
import hu.miskolc.uni.web_blackjack.model.enums.PlayerStateType;
import hu.miskolc.uni.web_blackjack.model.enums.RankType;
import hu.miskolc.uni.web_blackjack.repository.GameRepository;
import hu.miskolc.uni.web_blackjack.repository.UserRepository;
import hu.miskolc.uni.web_blackjack.service.BlackjackService;
import hu.miskolc.uni.web_blackjack.service.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Default implementation of Blackjack Service interface.
 *
 * @author Attila Szőke
 * @author Tamás Sólyom
 */
@Slf4j
@Service
public class BlackjackServiceImpl implements BlackjackService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;

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
        User user = new User(name);
        log.debug("Inserting new user {} into the database!", user);
        return userRepository.insert(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Game> getGames(String userId) {
        List<Game> games = gameRepository.findAllByPlayersUserIdNot(userId);
        log.debug("Fetched games {}!", games);
        return games;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game getGame(String gameId) throws GameNotFoundException {
        Game game = gameRepository.findById(gameId).orElseThrow(GameNotFoundException::new);
        log.debug("Fetched game {}!", game);
        return game;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game createGame(String userId) throws UserNotFoundException {
        User creator = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Set<Card> creatorCards = new HashSet<>();

        Game newGame = new Game();
        newGame.setDealtCards(new HashSet<>());
        newGame.setPlayers(new HashSet<>());
        newGame.setState(GameStateType.PENDING);

        creatorCards.add(dealCard(newGame.getDealtCards()));
        creatorCards.add(dealCard(newGame.getDealtCards()));

        newGame.getPlayers().add(new Player(creator, creatorCards, 0, PlayerStateType.IN_GAME));
        log.debug("Inserting new game {} into the database!", newGame);
        return gameRepository.insert(newGame);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game joinGame(String gameId, String userId) throws GameNotFoundException, UserNotFoundException, GameFullException, PlayerAlreadyInGameException, GameAlreadyClosedException {
        Game game = gameRepository.findById(gameId).orElseThrow(GameNotFoundException::new);
        if (game.getState() == GameStateType.CLOSED) {
            throw new GameAlreadyClosedException();
        }
        if (game.getPlayers().size() > 1) {
            throw new GameFullException();
        }
        for (Player player : game.getPlayers()) {
            if (player.getUser().getId().equals(userId)) {
                throw new PlayerAlreadyInGameException();
            }
        }
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Set<Card> userCards = new HashSet<>();
        userCards.add(dealCard(game.getDealtCards()));
        userCards.add(dealCard(game.getDealtCards()));
        game.getPlayers().add(new Player(user, userCards, 0, PlayerStateType.IN_GAME));
        game.setState(GameStateType.IN_PROGRESS);
        return gameRepository.save(game);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game hit(Game game, Player player) {
        Set<Card> currentCards = game.getDealtCards();
        Set<Player> currentPlayers = game.getPlayers();

        Card newCard = dealCard(currentCards);
        currentCards.add(newCard);
        game.setDealtCards(currentCards);

        currentPlayers.forEach((p) -> {
            if(p.getUser().getId().equals(player.getUser().getId())) p.getCards().add(newCard);
        });
        game.setPlayers(currentPlayers);
        return game;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game stand(Game game, Player player) {
        Set<Player> currentPlayers = game.getPlayers();
        currentPlayers.forEach((p) -> {
            if(p.getUser().getId().equals(player.getUser().getId())) {
                p.setState(PlayerStateType.STOPPED);
            }
        });
        game.setPlayers(currentPlayers);
        return game;
    }

    /**
     * Deals a random card, except cards contained the parameter set.
     * Also if the card is new adds it to the parameter list.
     *
     * @param dealtCards - the set of cards which should not be dealt
     * @return random card
     */
    private Card dealCard(Set<Card> dealtCards) {
        Card card = new Card();
        do {
            List<RankType> ranks = new ArrayList<>(EnumSet.allOf(RankType.class));
            List<ColorType> colors = new ArrayList<>(EnumSet.allOf(ColorType.class));
            card.setRank(ranks.get(rand(0, 12)));
            card.setNumber(card.getRank().getValue());
            card.setColor(colors.get(rand(0, 3)));
            log.debug("Generated card {} with dealt cards {}!", card, dealtCards);
        } while (dealtCards.contains(card));
        dealtCards.add(card);
        log.debug("Generated card {}, which was not in the dealt cards {}", card, dealtCards);
        return card;
    }

    /**
     * Generates a random integer.
     *
     * @param min lower inclusive threshold
     * @param max higher inclusive threshold
     * @return random integer
     */
    private int rand(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}
