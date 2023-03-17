/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.com.emanuelvictor.userinterface.jsf;

import br.com.emanuelvictor.domain.entities.Resposta;
import br.com.emanuelvictor.domain.entities.Usuario;
import br.com.emanuelvictor.domain.services.RespostaService;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
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
    private Resposta resposta = new Resposta();
    @EJB
    private RespostaService respostaService;

    public void save() {
        respostaService.save(resposta);
        resposta = new Resposta();
    }

    public List<Resposta> getAll() {
        return respostaService.getAll();
    }

    public List<Usuario> getRanque() {
        return respostaService.getRanque();
    }

}
