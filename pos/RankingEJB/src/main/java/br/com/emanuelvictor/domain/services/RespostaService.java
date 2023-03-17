/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatefulEjbClass.java to edit this template
 */
package br.com.emanuelvictor.domain.services;

import br.com.emanuelvictor.domain.entities.Resposta;
import br.com.emanuelvictor.domain.entities.Usuario;
import br.com.emanuelvictor.domain.ports.repositories.RespostaRepository;
import br.com.emanuelvictor.domain.ports.repositories.UsuarioRepository;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * @author default
 */
@Stateful
public class RespostaService implements Serializable {

    @EJB
    private final UsuarioRepository usuarioRepository;
    @EJB
    private final RespostaRepository respostaRepository;

    @Inject
    public RespostaService(UsuarioRepository usuarioRepository, RespostaRepository respostaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.respostaRepository = respostaRepository;
    }

    public void save(final Resposta resposta) {
        final Usuario usuario = usuarioRepository.findByName(resposta.getUsuario().getNome());
        if (resposta.verificarValorInformadoPeloUsuario()) {
            usuario.incrementarPontuacao();
        }
        usuarioRepository.save(usuario);
        respostaRepository.save(resposta);
    }

    public List<Usuario> getRanque() {
        return usuarioRepository.getAll();
    }

}
