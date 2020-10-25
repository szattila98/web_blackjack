package hu.miskolc.uni.web_blackjack.model.enums;

/**
 * Symbolizes the face-card property of the cards in the standard 52-card French deck.
 *
 * @author Tamás Sólyom
 */
public enum RankType {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9),
    TEN(10), JACK(10), QUEEN(10), KING(10), ACE(11);

    private final int value;

    RankType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
