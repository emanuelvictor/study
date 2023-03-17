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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public boolean save(final Resposta resposta) {
        final Usuario usuario = usuarioRepository.findByName(resposta.getUsuario().getNome());
        final boolean usuarioAcertou = resposta.verificarValorInformadoPeloUsuario();
        if (usuarioAcertou) {
            usuario.incrementarPontuacao();
        }
        usuarioRepository.save(usuario);
        respostaRepository.save(resposta);
        return usuarioAcertou;
    }

    public List<Usuario> getRanque() {
        return usuarioRepository.getAll().stream()
                .sorted(Comparator.comparingInt(Usuario::getPontuacao).reversed())
                .collect(Collectors.toList());
    }

}
