/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.com.emanuelvictor.userinterface.jsf;

import br.com.emanuelvictor.domain.entities.Questao;
import br.com.emanuelvictor.infrastructure.adapters.QuestaoRepositoryImpl;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author default
 */
@Named(value = "jsfQuestao")
@RequestScoped
public class JsfQuestao {

    @EJB
    private QuestaoRepositoryImpl questaoRepositoryImpl; // TODO não precisa ser a implementação

    /**
     * Creates a new instance of JsfProduto
     */
    public JsfQuestao() {
    }

    public Questao gerarNovaQuestao() {
        return questaoRepositoryImpl.gerarNovaQuestao();
    }

}
