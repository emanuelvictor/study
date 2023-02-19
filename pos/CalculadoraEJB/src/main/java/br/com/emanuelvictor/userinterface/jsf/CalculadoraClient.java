/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.com.emanuelvictor.userinterface.jsf;

import br.com.emanuelvictor.domain.ports.Calculadora;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

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
