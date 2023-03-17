/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package br.com.emanuelvictor.infrastructure.adapters;

import br.com.emanuelvictor.domain.entities.Resposta;
import br.com.emanuelvictor.domain.ports.repositories.RespostaRepository;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Emanuel Victor
 */
@Stateful
public class RespostaRepositoryImpl implements RespostaRepository {

    private final List<Resposta> respostas = new ArrayList<>();

    @Override
    public Resposta save(Resposta resposta) {
        respostas.add(resposta);
        return resposta;
    }

    @Override
    public List<Resposta> getAll() {
        return respostas;
    }
}
