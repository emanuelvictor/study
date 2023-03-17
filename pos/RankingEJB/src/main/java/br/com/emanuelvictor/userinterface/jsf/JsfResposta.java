/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.com.emanuelvictor.userinterface.jsf;

import br.com.emanuelvictor.domain.entities.Resposta;
import br.com.emanuelvictor.domain.entities.Usuario;
import br.com.emanuelvictor.domain.ports.repositories.QuestaoRepository;
import br.com.emanuelvictor.domain.services.RespostaService;
import br.com.emanuelvictor.infrastructure.adapters.QuestaoRepositoryImpl;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


/**
 * @author default
 */
@SessionScoped
@Named(value = "jsfResposta")
public class JsfResposta implements Serializable {
    @Setter
    @Getter
    private Resposta resposta;

    @Setter
    @Getter
    private String mensagem;

    @EJB
    private RespostaService respostaService;

    @EJB
    private final QuestaoRepository questaoRepository;

    @Inject
    public JsfResposta(final QuestaoRepository questaoRepository) {
        this.questaoRepository = questaoRepository;
        this.resposta = new Resposta();
        this.resposta.setQuestao(questaoRepository.gerarNovaQuestao());
    }

    public void save() {
        if (respostaService.save(resposta))
            mensagem = "Você acertou :)";
        else
            mensagem = "Você errou :(";
        resposta = new Resposta();
        resposta.setQuestao(questaoRepository.gerarNovaQuestao());
    }

    public List<Usuario> getRanque() {
        return respostaService.getRanque();
    }

}
