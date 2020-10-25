package hu.miskolc.uni.web_blackjack.controller.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO class for post requests where some user input is required.
 *
 * @author Attila Szőke
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserInput {

    private String inputValue;
}
