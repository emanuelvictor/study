package br.com.emanuelvictor.domain.entities.identifier;

import br.com.emanuelvictor.domain.exceptions.BusinessLogicException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static br.com.emanuelvictor.domain.entities.identifier.Identifier.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IdentifierTests {

    @ParameterizedTest
    @MethodSource("getInvalidValuesFromIdentifier")
    void cannotCreateIdentifierInstanceWithInvalidValues(final String value, final String message) {
        final Exception businessLogicException = assertThrows(BusinessLogicException.class, () -> new Identifier(value));

        Assertions.assertThat(businessLogicException).isInstanceOf(BusinessLogicException.class).hasMessageContaining(message);
    }

    @ParameterizedTest
    @MethodSource("getValidValuesFromIdentifier")
    void mustCreateIdentifierInstanceFromValidValues(final String value) {
        final Identifier identifier = new Identifier(value);

        Assertions.assertThat(identifier).isNotNull()
        ;
    }

    private static Stream<Arguments> getInvalidValuesFromIdentifier() {
        return Stream.of(
                Arguments.arguments(null, NULL_VALUE_ERROR_MESSAGE),
                Arguments.arguments("123456", FIST_CHARACTER_ERROR_MESSAGE),
                Arguments.arguments("2B3", FIST_CHARACTER_ERROR_MESSAGE),
                Arguments.arguments("", MIN_LENGTH_ERROR_MESSAGE),
                Arguments.arguments("      ", MIN_LENGTH_ERROR_MESSAGE),
                Arguments.arguments("a123456", MAX_LENGTH_ERROR_MESSAGE),
                Arguments.arguments("A1b2C3d", MAX_LENGTH_ERROR_MESSAGE),
                Arguments.arguments("a1!345", String.format(THE_FOLLOWING_CHARS_ARE_SPECIAL, '!')),
                Arguments.arguments("a1@34#", String.format(THE_FOLLOWING_CHARS_ARE_SPECIAL, "@")),
                Arguments.arguments("cont*1", String.format(THE_FOLLOWING_CHARS_ARE_SPECIAL, "*")),
                Arguments.arguments("cont 1", String.format(THE_FOLLOWING_CHARS_ARE_SPECIAL, " ")),
                Arguments.arguments("Z-12", String.format(THE_FOLLOWING_CHARS_ARE_SPECIAL, "-")),
                Arguments.arguments("1soma", FIST_CHARACTER_ERROR_MESSAGE)
        );
    }

    private static Stream<Arguments> getValidValuesFromIdentifier() {
        return Stream.of(
                Arguments.arguments("a12345"),
                Arguments.arguments("abc12"),
                Arguments.arguments("a1"),
                Arguments.arguments("Z47a ")
        );
    }

}
