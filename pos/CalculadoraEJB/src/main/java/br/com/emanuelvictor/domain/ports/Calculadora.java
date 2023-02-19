/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package br.com.emanuelvictor.domain.ports;

import javax.ejb.Local;

/**
 * @author Emanuel Victor
 */
@Local
public interface Calculadora {

    /**
     * @param valores {@link int.. }
     * @return {@link int}
     */
    int somar(int... valores);

}
