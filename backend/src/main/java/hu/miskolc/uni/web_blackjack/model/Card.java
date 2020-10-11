package hu.miskolc.uni.web_blackjack.model;

import hu.miskolc.uni.web_blackjack.model.enums.ColorType;
import hu.miskolc.uni.web_blackjack.model.enums.RankType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Data model for the Card object.
 *
 * @author Attila Szőke
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Card {

    private int number;
    private RankType rank;
    private ColorType color;
}
