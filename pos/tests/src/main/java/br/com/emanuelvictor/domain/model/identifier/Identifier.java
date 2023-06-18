package br.com.emanuelvictor.domain.model.identifier;

import br.com.emanuelvictor.domain.model.employee.exceptions.BusinessLogicException;

public class Identifier {
    static int MIN_LENGTH = 1;
    static String MIN_LENGTH_ERROR_MESSAGE = "The min length to the value is " + MIN_LENGTH;
    static int MAX_LENGTH = 6;
    static String MAX_LENGTH_ERROR_MESSAGE = "The max length to the value is " + MAX_LENGTH;
    static String NULL_VALUE_ERROR_MESSAGE = "The identifier value cannot be null";
    static String FIST_CHARACTER_ERROR_MESSAGE = "The first character from value must be a letter";
    static String THE_FOLLOWING_CHARS_ARE_SPECIAL = "The following chars are special: %s";

    private final String value;

    public Identifier(final String value) {
        validateIfTheValueIsNotNull(value);
        final String valueWithoutBlankSpaces = trimValue(value);
        validateIdentifier(valueWithoutBlankSpaces);
        this.value = valueWithoutBlankSpaces;
    }

    public String getValue() {
        return value;
    }

    static void validateIdentifier(final String valueWithoutBlankSpaces) {
        validateMinLengthToTheValue(valueWithoutBlankSpaces, MIN_LENGTH);
        validateIfTheFirstCharacterOfValueIsALetter(valueWithoutBlankSpaces);
        validateMaxLengthToTheValue(valueWithoutBlankSpaces, MAX_LENGTH);
        validateIfTheValueContainsOnlyNumbersAndLetters(valueWithoutBlankSpaces);
    }

    static String trimValue(final String value) {
        return value.trim();
    }

    static void validateIfTheValueIsNotNull(final String value) {
        BusinessLogicException.create()
                .whenNull(value, NULL_VALUE_ERROR_MESSAGE)
                .thenThrows();
    }

    static void validateIfTheFirstCharacterOfValueIsALetter(final String value) {
        BusinessLogicException.create()
                .whenFalse(Character.isLetter(value.charAt(0)), FIST_CHARACTER_ERROR_MESSAGE)
                .thenThrows();
    }

    static void validateMinLengthToTheValue(final String value, final int minLength) {
        BusinessLogicException.create()
                .whenFalse(value.length() >= minLength, MIN_LENGTH_ERROR_MESSAGE)
                .thenThrows();
    }

    static void validateMaxLengthToTheValue(final String value, final int maxLength) {
        BusinessLogicException.create()
                .whenFalse(value.length() <= maxLength, MAX_LENGTH_ERROR_MESSAGE)
                .thenThrows();
    }

    private static void validateIfTheValueContainsOnlyNumbersAndLetters(final String value) {
        for (int i = 0; i < value.length(); i++) {
            BusinessLogicException.create()
                    .whenFalse(isLetterOrDigit(value.charAt(i)), String.format(THE_FOLLOWING_CHARS_ARE_SPECIAL, value.charAt(i)))
                    .thenThrows();
        }
    }

    private static boolean isLetterOrDigit(char character) {
        return Character.isLetterOrDigit(character);
    }
}
