/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.com.emanuelvictor.userinterface.jsf;

import br.com.emanuelvictor.domain.ports.Calculadora;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author Emanuel Victor
 */
@RequestScoped
@Named(value = "calculadora")
public class CalculadoraClient {

    @EJB
    private final Calculadora calculadora;

    @Setter
    @Getter
    private int primeiroValor;

    @Setter
    @Getter
    private int segundoValor;

    @Setter
    @Getter
    private int resultado;

    @Inject
    public CalculadoraClient(final Calculadora calculadora) {
        this.calculadora = calculadora;
    }

    public void somar(){
        resultado = calculadora.somar(primeiroValor, segundoValor);
    }

}
