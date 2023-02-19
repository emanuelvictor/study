/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package br.com.emanuelvictor.domain.ports;

import br.com.emanuelvictor.infrastrcture.adapters.CalculadoraImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Emanuel Victor
 */
public class CalculadoraTests {

    /**
     *
     */
    private final Calculadora calculadora = new CalculadoraImpl();

    /**
     *
     */
    @Test
    public void deveSomarDoisValores() {
        final int primeiroValor = 1;
        final int segundoValor = 34;
        final int terceiroValor = 19;
        final int resultadoEsperado = primeiroValor + segundoValor + terceiroValor;

        final int resultado = calculadora.somar(primeiroValor, segundoValor, terceiroValor);

        Assertions.assertEquals(resultadoEsperado, resultado);
    }

}
