package hu.miskolc.uni.web_blackjack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.miskolc.uni.web_blackjack.model.enums.GameStateType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

/**
 * Data model for the Game object.
 *
 * @author Attila Szőke
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "games")
public class Game {

    @Id
    private String id;
    private List<Player> players;
    private Dealer dealer;
    private int currentPlayerIndex;
    @JsonIgnore
    private Set<Card> dealtCards;
    private GameStateType state;
}
