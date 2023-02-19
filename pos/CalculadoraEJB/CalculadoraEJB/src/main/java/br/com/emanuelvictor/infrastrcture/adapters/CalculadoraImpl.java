/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package br.com.emanuelvictor.infrastrcture.adapters;

import br.com.emanuelvictor.domain.ports.Calculadora;

import javax.ejb.Stateless;
import java.util.Arrays;

/**
 * @author Emanuel Victor
 */
@Stateless
public class CalculadoraImpl implements Calculadora {

    /**
     * @param valores {@link int.. }
     * @return {@link int}
     */
    @Override
    public int somar(final int... valores) {
        return Arrays.stream(valores).sum();
    }
}
