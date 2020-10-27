package hu.miskolc.uni.web_blackjack.model;

import hu.miskolc.uni.web_blackjack.model.enums.PlayerStateType;
import lombok.*;

import java.util.Set;

/**
 * Data model for the Dealer object.
 *
 * @author Tamás Sólyom
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dealer {

    private Set<Card> cards;
    private int points;
    private PlayerStateType state;

}
