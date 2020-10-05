package hu.miskolc.uni.web_blackjack.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Data model for the Player object.
 *
 * @author Attila Szőke
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Player {

    private String name;
    private List<Card> cards;
    private int money;
    private int wager;
}
