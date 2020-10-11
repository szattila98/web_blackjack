package hu.miskolc.uni.web_blackjack.model;

import hu.miskolc.uni.web_blackjack.model.enums.PlayerStateType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Data model for the Dealer object.
 *
 * @author Tamás Sólyom
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Dealer {

    private List<Card> cards;
    private int points;
    private PlayerStateType state;

}
