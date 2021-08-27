package com.emanuelvictor.java1.exercicios.exercicios5.application.exceptions;

/**
 * Emanuel Victor De Oliveira Fonseca
 */
public class VeicExistException extends Exception {

    public VeicExistException() {
        super("Esse veículo já existe!");
    }

    public VeicExistException(String message) {
        super(message);
    }
}
