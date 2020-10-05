package hu.miskolc.uni.web_blackjack.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Data model for the GameState object.
 *
 * @author Attila Szőke
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class GameState {

    private Long id;
    private String creatorName;
    private List<Player> players;
    private List<Card> dealtCards;
    private Player currentPlayer;
}
