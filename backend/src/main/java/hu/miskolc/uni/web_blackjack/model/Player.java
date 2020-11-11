package hu.miskolc.uni.web_blackjack.model;

import hu.miskolc.uni.web_blackjack.model.enums.PlayerStateType;
import hu.miskolc.uni.web_blackjack.model.enums.RankType;
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
    private int bid;

    public int getPoints() {
        int points = 0;
        boolean moreAce = false;
        for(Card c : cards) {
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
        setPoints(points);
        if(points > 21) {
            setState(PlayerStateType.OUT);
        }
        return points;
    }

}
