/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package br.com.emanuelvictor.domain.ports.repositories;

import br.com.emanuelvictor.domain.entities.Resposta;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Emanuel Victor
 */
@Local
public interface RespostaRepository {

    Resposta save(final Resposta resposta);

    List<Resposta> getAll();

}
