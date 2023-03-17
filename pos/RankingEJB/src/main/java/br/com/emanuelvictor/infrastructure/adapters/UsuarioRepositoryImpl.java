/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package br.com.emanuelvictor.infrastructure.adapters;

import br.com.emanuelvictor.domain.entities.Usuario;
import br.com.emanuelvictor.domain.ports.repositories.UsuarioRepository;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Emanuel Victor
 */
@Stateful
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final Set<Usuario> usuarios = new HashSet<>();

    @Override
    public Usuario save(Usuario usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    @Override
    public List<Usuario> getAll() {
        return new ArrayList<>(usuarios);
    }

    @Override
    public Usuario findByName(final String nome) {
        return usuarios.stream().filter(usuario -> usuario.getNome().equals(nome)).findFirst().orElse(new Usuario(nome));
    }
}
