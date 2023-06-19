package br.com.emanuelvictor.domain.model.craps;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static br.com.emanuelvictor.domain.model.craps.Winner.HOUSE;
import static br.com.emanuelvictor.domain.model.craps.Winner.PLAYER;

public class CrapsTests {

    @Test
    void mustRunFirstDice() {
        final int resultExpected = 1;
        final Craps craps = Mockito.mock(Craps.class);
        Mockito.when(craps.runFirstDice()).thenReturn(resultExpected);

        final int resultOfRunDice = craps.runFirstDice();

        Assertions.assertThat(resultOfRunDice).isEqualTo(resultExpected);
    }

    @Test
    void mustRunSecondDice() {
        final int resultExpected = 1;
        final Craps craps = Mockito.mock(Craps.class);
        Mockito.when(craps.runSecondDice()).thenReturn(resultExpected);

        final int resultOfRunDice = craps.runSecondDice();

        Assertions.assertThat(resultOfRunDice).isEqualTo(resultExpected);
    }

    @Test
    void mustRunTheTwoDices() {
        final int resultOfTwoDices = 9;
        final Craps craps = Mockito.mock(Craps.class);
        Mockito.when(craps.runTwoDices()).thenReturn(resultOfTwoDices);

        final int resultOfDices = craps.runTwoDices();

        Assertions.assertThat(resultOfDices).isEqualTo(resultOfTwoDices);
    }

    @Test
    void getPlayerHouseToFourthTurn() {
        final int pointToWin = 7;
        final Craps craps = Mockito.spy(Craps.class);
        Mockito.when(craps.runTwoDices()).thenReturn(5, 1, 3, pointToWin);
        runDices(craps);
        runDices(craps);
        runDices(craps);

        final int resultFromFourthTurn = craps.runDices();

        Assertions.assertThat(resultFromFourthTurn).isEqualTo(pointToWin);
        Assertions.assertThat(craps.isTheEnd()).isTrue();
        Assertions.assertThat(craps.playerIsWinner()).isFalse();
        Assertions.assertThat(craps.houseIsWinner()).isTrue();
    }

    @Test
    void getHouseHouseToFourthTurn() {
        final int pointToWin = 5;
        final Craps craps = Mockito.spy(Craps.class);
        Mockito.when(craps.runTwoDices()).thenReturn(pointToWin, 1, 3, pointToWin);
        runDices(craps);
        runDices(craps);
        runDices(craps);

        final int resultFromFourthTurn = craps.runDices();

        Assertions.assertThat(resultFromFourthTurn).isEqualTo(pointToWin);
        Assertions.assertThat(craps.isTheEnd()).isTrue();
        Assertions.assertThat(craps.playerIsWinner()).isTrue();
        Assertions.assertThat(craps.houseIsWinner()).isFalse();
    }

    private void runDices(final Craps craps) {
        craps.runDices();
        Assertions.assertThat(craps.isTheEnd()).isEqualTo(false);
        Assertions.assertThat(craps.playerIsWinner()).isEqualTo(false);
        Assertions.assertThat(craps.houseIsWinner()).isEqualTo(false);
    }

    @ParameterizedTest
    @MethodSource("getResultsOfDices")
    void mustRunDicesAndGetWinnerToFirstTurn(final Winner winner, final int resultOfTwoDices) {
        final Craps craps = Mockito.spy(Craps.class);
        Mockito.when(craps.runTwoDices()).thenReturn(resultOfTwoDices);

        final int resultOfDices = craps.runDices();

        Assertions.assertThat(resultOfDices).isEqualTo(resultOfTwoDices);
        Assertions.assertThat(winner).isEqualTo(craps.getWinner());
        Assertions.assertThat(true).isEqualTo(craps.isTheEnd());
    }

    private static Stream<Arguments> getResultsOfDices() {
        return Stream.of(
                Arguments.arguments(PLAYER, 11),
                Arguments.arguments(PLAYER, 7),
                Arguments.arguments(HOUSE, 2),
                Arguments.arguments(HOUSE, 3),
                Arguments.arguments(HOUSE, 12)
        );
    }
}
