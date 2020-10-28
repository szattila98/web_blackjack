package hu.miskolc.uni.web_blackjack.service.impl;

import hu.miskolc.uni.web_blackjack.model.*;
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
        games.removeIf(game -> game.getPlayers().size() > 1);
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
        newGame.setPlayers(new ArrayList<>());
        newGame.setState(GameStateType.PENDING);

        creatorCards.add(dealCard(newGame.getDealtCards()));
        creatorCards.add(dealCard(newGame.getDealtCards()));

        Set<Card> dealerCards = new HashSet<>();
        dealerCards.add(dealCard(newGame.getDealtCards()));
        dealerCards.add(dealCard(newGame.getDealtCards()));

        Player newPlayer = new Player(creator, creatorCards, 0, PlayerStateType.IN_GAME);
        newPlayer = calculatePoints(newPlayer);
        newGame.getPlayers().add(newPlayer);
        newGame.setDealer(new Dealer(dealerCards, 0, PlayerStateType.IN_GAME));
        newGame.setCurrentPlayerIndex(0);
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
        Player newPlayer = new Player(user, userCards, 0, PlayerStateType.IN_GAME);
        newPlayer = calculatePoints(newPlayer);
        game.getPlayers().add(newPlayer);
        game.setState(GameStateType.IN_PROGRESS);
        return gameRepository.save(game);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game hit(String gameId, String userId)
            throws PlayerAlreadyStoppedException, GameNotFoundException, NotThisPlayersTurnException {
        Game game = gameRepository.findById(gameId).orElseThrow(GameNotFoundException::new);
        Set<Card> currentCards = game.getDealtCards();
        List<Player> currentPlayers = game.getPlayers();

        Card newCard = dealCard(currentCards);
        currentCards.add(newCard);
        game.setDealtCards(currentCards);

        for (Player p : currentPlayers) {
            if (p.getUser().getId().equals(userId) && p.getState() == PlayerStateType.STOPPED) {
                throw new PlayerAlreadyStoppedException();
            }
            if (p.getUser().getId().equals(userId) && currentPlayers.indexOf(p) != game.getCurrentPlayerIndex()) {
                throw new NotThisPlayersTurnException();
            }
        }
        currentPlayers.forEach((p) -> {
            if (p.getUser().getId().equals(userId)) {
                p.getCards().add(newCard);
                p = calculatePoints(p);
            }
        });
        game.setPlayers(currentPlayers);

        return gameRepository.save(game);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game stand(String gameId, String userId)
            throws PlayerAlreadyStoppedException, GameNotFoundException, NotThisPlayersTurnException {
        Game game = gameRepository.findById(gameId).orElseThrow(GameNotFoundException::new);
        List<Player> currentPlayers = game.getPlayers();

        for (Player p : currentPlayers) {
            if (p.getUser().getId().equals(userId) && p.getState() == PlayerStateType.STOPPED) {
                throw new PlayerAlreadyStoppedException();
            }
            if (p.getUser().getId().equals(userId) && currentPlayers.indexOf(p) != game.getCurrentPlayerIndex()) {
                throw new NotThisPlayersTurnException();
            }
        }
        currentPlayers.forEach((p) -> {
            if (p.getUser().getId().equals(userId)) {
                p.setState(PlayerStateType.STOPPED);
            }
        });
        game.setCurrentPlayerIndex(game.getCurrentPlayerIndex() + 1);
        game.setPlayers(currentPlayers);
        return gameRepository.save(game);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game exitGame(String gameId, String userId) throws GameInProgressException, GameNotFoundException {
        Game game = gameRepository.findById(gameId).orElseThrow(GameNotFoundException::new);
        List<Player> currentPlayers = game.getPlayers();

        for(Player p : currentPlayers) {
            if(p.getUser().getId().equals(userId) && p.getState() == PlayerStateType.IN_GAME) {
                throw new GameInProgressException();
            }
        }
        currentPlayers.forEach((p) -> {
            if(p.getUser().getId().equals(userId)) {
                currentPlayers.remove(p);
            }
        });
        game.setPlayers(currentPlayers);
        return gameRepository.save(game);
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

    private Player calculatePoints(Player player) {
        int points = 0;
        boolean moreAce = false;
        Set<Card> playerCards = player.getCards();
        for(Card c : playerCards) {
            if(c.getRank() == RankType.ACE && !moreAce) {
                points += c.getRank().getValue();
                moreAce = true;
            }
            else if(c.getRank() == RankType.ACE && moreAce) {
                points += 1;
            }
            else {
                points += c.getRank().getValue();
            }
        }
        player.setPoints(points);
        if(points > 21) {
            player.setState(PlayerStateType.OUT);
        }
        return player;
    }

}
