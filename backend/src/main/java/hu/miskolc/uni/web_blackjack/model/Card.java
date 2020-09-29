package hu.miskolc.uni.web_blackjack.model;

import hu.miskolc.uni.web_blackjack.model.enums.FaceCardType;
import hu.miskolc.uni.web_blackjack.model.enums.SuitType;
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

    private SuitType suit;
    private FaceCardType faceCard;
    private int numeral;
}
