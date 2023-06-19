package br.com.emanuelvictor.domain.model.line;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static br.com.emanuelvictor.domain.model.line.NewLine.*;

public class NewLineTests {

    @Test
    public void mustCollapseString() {
        final String expected = "abc\n123\nzwy\n123";
        final String lineToCollapse = "abc\n123\nzwy\n\n123";
        final NewLine newLine = new NewLine();

        final String result = newLine.collapseNewlines(lineToCollapse);

        Assertions.assertThat(expected).isEqualTo(result);
    }

    @ParameterizedTest
    @MethodSource("getInvalidStrings")
    public void cannotCollapseInvalidString(final String invalidString, final String errorMessage) {
        final NewLine newLine = new NewLine();

        org.junit.jupiter.api.Assertions.assertThrows(
                RuntimeException.class,
                () -> newLine.collapseNewlines(invalidString),
                errorMessage
        );
    }

    private static Stream<Arguments> getInvalidStrings() {
        return Stream.of(
                Arguments.arguments(null, NULL_STRING_MESSAGE_ERROR), // This data test null string validation (if (Objects.isNull(string)))
                Arguments.arguments("", EMPTY_STRING_MESSAGE_ERROR) // This data test empty string validation (if(string.isEmpty()))
        );
    }
}

