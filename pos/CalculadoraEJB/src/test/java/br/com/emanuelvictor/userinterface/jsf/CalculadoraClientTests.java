package br.com.emanuelvictor.userinterface.jsf;

import br.com.emanuelvictor.domain.ports.Calculadora;
import br.com.emanuelvictor.infrastrcture.adapters.CalculadoraImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculadoraClientTests {

    /**
     *
     */
    private final Calculadora calculadora = new CalculadoraImpl();

    /**
     *
     */
    private final CalculadoraClient calculadoraClient = new CalculadoraClient(calculadora);

    /**
     *
     */
    @Test
    public void deveSomarDoisValores() {
        final int primeiroValor = 1;
        final int segundoValor = 34;
        final int resultadoEsperado = calculadora.somar(primeiroValor, segundoValor);
        calculadoraClient.setPrimeiroValor(primeiroValor);
        calculadoraClient.setSegundoValor(segundoValor);

        calculadoraClient.somar();

        Assertions.assertEquals(resultadoEsperado, calculadoraClient.getResultado());
    }
}