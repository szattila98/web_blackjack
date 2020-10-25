package hu.miskolc.uni.web_blackjack.model;

import hu.miskolc.uni.web_blackjack.model.enums.PlayerStateType;
import lombok.*;

import java.util.Set;

/**
 * Data model for the Player object.
 *
 * @author Attila Szőke
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Player {

    private User user;
    private Set<Card> cards;
    private int points;
    private PlayerStateType state;
}
