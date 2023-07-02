package br.com.emanuelvictor.domain.model.triangle.exceptions;

public abstract class InvalidGeometricForm extends RuntimeException {

    public InvalidGeometricForm(String message) {
        super(message);
    }
}
