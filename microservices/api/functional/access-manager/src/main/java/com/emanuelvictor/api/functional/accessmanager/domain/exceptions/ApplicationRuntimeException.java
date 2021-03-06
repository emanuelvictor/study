package com.emanuelvictor.api.functional.accessmanager.domain.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 2.0.0, 12/12/2020
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ApplicationRuntimeException extends RuntimeException {

    @Getter
    protected Object[] parameters;

    /**
     *
     */
    public ApplicationRuntimeException() {
        this.parameters = new Object[]{};
    }

    /**
     * @param message
     */
    public ApplicationRuntimeException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param parameters
     */
    public ApplicationRuntimeException(String message, Object... parameters) {
        super(message);
        this.parameters = parameters;
    }

    /**
     * @param message
     * @param throwable
     * @param parameters
     */
    public ApplicationRuntimeException(String message, Throwable throwable, Object... parameters) {
        super(message, throwable);
        this.parameters = parameters;
    }
}
