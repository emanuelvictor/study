package domain.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BusinessLogicException extends RuntimeException {
    private List<String> errors = new ArrayList<>();

    private BusinessLogicException() {
        super();
    }

    private BusinessLogicException(final String message, final String... errors) {
        super(message);
        this.errors = Arrays.asList(errors);
    }

    private BusinessLogicException(final String message, final List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public BusinessLogicException whenTrue(final boolean condition, final String message) {
        if (condition) {
            this.errors.add(message);
        }
        return this;
    }

    public BusinessLogicException whenNull(final Object object, final String message) {
        if (Objects.isNull(object)) {
            this.errors.add(message);
        }
        return this;
    }


    public void thenThrows() {
        if (!this.errors.isEmpty()) {
            final String message = buildMessage();
            throw new BusinessLogicException(message, this.errors);
        }
    }

    private String buildMessage() {
        return buildMessage(errors);
    }

    private static String buildMessage(final List<String> errors) {
        final StringBuilder message = new StringBuilder();

        for (final String erro : errors) {
            message.append(erro).append("\n");
        }

        return message.toString();
    }


    public static BusinessLogicException create() {
        return new BusinessLogicException();
    }

    public static BusinessLogicException create(final String message, final String... errors) {
        return new BusinessLogicException(message, errors);
    }

}
