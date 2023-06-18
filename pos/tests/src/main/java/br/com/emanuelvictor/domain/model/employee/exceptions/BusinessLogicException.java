package br.com.emanuelvictor.domain.model.employee.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BusinessLogicException extends RuntimeException {

    static final long serialVersionUID = -7034897190745766148L;
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

    public BusinessLogicException whenFalse(final boolean condition, final String message) {
        if (!condition) {
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

        for (final String error : errors) {
            message.append(error).append("\n");
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
