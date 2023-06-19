package br.com.emanuelvictor.domain.model.craps;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static br.com.emanuelvictor.domain.model.craps.Dice.SIDES_OF_DICE;

public class DiceTests {

    @Test
    void mustRunDiceAndGetANumberLessThanSidesOfDice() {
        final Dice dice = new Dice();

        final int numberGenerated = dice.run();

        Assertions.assertThat(numberGenerated <= SIDES_OF_DICE).isTrue();
    }
}
