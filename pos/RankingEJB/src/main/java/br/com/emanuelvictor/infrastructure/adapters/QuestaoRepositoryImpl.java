/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package br.com.emanuelvictor.infrastructure.adapters;

import br.com.emanuelvictor.domain.entities.Questao;
import br.com.emanuelvictor.domain.ports.repositories.QuestaoRepository;

import javax.ejb.Stateless;

/**
 * @author Emanuel Victor
 * EJB Stateless
 */
@Stateless
public class QuestaoRepositoryImpl implements QuestaoRepository {

    public Questao gerarNovaQuestao() {
        return new Questao();
    }
}
