package br.com.emanuelvictor.domain.model.random;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static br.com.emanuelvictor.domain.model.random.MyRandomNumber.*;

public class MyRandomNumberTests {

    private MyRandomNumber myRandomNumber;

    @BeforeEach
    void beforeEach() {
        myRandomNumber = new MyRandomNumber();
    }

    @Test
    @SneakyThrows
    void mustGenerateRandomValue() {
        final int begin = 1;
        final int end = 2;

        final int generatedNumber = myRandomNumber.nextRandomNumber(begin, end);

        Assertions.assertThat(generatedNumber >= 1).isTrue();
        Assertions.assertThat(generatedNumber <= 2).isTrue();
    }

    @Test
    @SneakyThrows
    void cannotGenerateRandomValueFromHistory() {
        final int begin = 2;
        final int end = 6;
        final int randomNumberExpected = 6;
        final MyRandomNumber myRandomNumber = Mockito.spy(MyRandomNumber.class);
        final Set<Integer> historyMocked = new HashSet<>(Arrays.asList(2, 3, 4, 5));
        Mockito.when(myRandomNumber.getHistory()).thenReturn(historyMocked);

        final int generatedNumber = myRandomNumber.nextRandomNumber(begin, end);

        Assertions.assertThat(randomNumberExpected).isEqualTo(generatedNumber);
    }

    @Test
    void mustVerifyIfANumberIsInHistory() {
        final MyRandomNumber myRandomNumber = Mockito.spy(MyRandomNumber.class);
        final Set<Integer> historyMocked = new HashSet<>(Arrays.asList(2, 3, 4, 5));
        Mockito.when(myRandomNumber.getHistory()).thenReturn(historyMocked);

        final boolean randomNumberGeneratedInHistory = myRandomNumber.randomNumberGeneratedIsInHistory(5);
        final boolean randomNumberGeneratedIsNotInHistory = myRandomNumber.randomNumberGeneratedIsInHistory(6);

        Assertions.assertThat(randomNumberGeneratedInHistory).isTrue();
        Assertions.assertThat(randomNumberGeneratedIsNotInHistory).isFalse();
    }

    @Test
    void mustGenerateARandomNumberGreaterThanBeginAndLessThanEnd() {
        final int begin = 2;
        final int end = 6;

        final int generatedNumber = myRandomNumber.generate(begin, end);

        Assertions.assertThat(generatedNumber >= begin).isTrue();
        Assertions.assertThat(generatedNumber <= end).isTrue();
    }

    @ParameterizedTest
    @MethodSource("getInvalidValues")
    void cannotGenerateNextRandomNumberWithInvalidBeginOrInvalidEnd(final int begin, final int end, final String exceptionMessage) {

        final Exception exception = org.junit.jupiter.api.Assertions.assertThrows(IntervaloInvalidoException.class, () -> MyRandomNumber.validateInputs(begin, end));

        org.assertj.core.api.Assertions.assertThat(exception.getMessage()).isEqualTo(exceptionMessage);
    }

    private static Stream<Arguments> getInvalidValues() {
        return Stream.of(
                Arguments.arguments(-1, 1, ERROR_BEGIN_LESS_THAN_ZERO),
                Arguments.arguments(0, -1, ERROR_END_LESS_THAN_ZERO),
                Arguments.arguments(2, 1, ERROR_BEGIN_LESS_THAN_END)
        );
    }
}
