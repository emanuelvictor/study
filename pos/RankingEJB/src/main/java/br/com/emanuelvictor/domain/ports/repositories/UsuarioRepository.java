/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package br.com.emanuelvictor.domain.ports.repositories;

import br.com.emanuelvictor.domain.entities.Usuario;

import javax.ejb.Local;
import java.io.Serializable;
import java.util.List;

/**
 * @author Emanuel Victor
 */
@Local
public interface UsuarioRepository extends Serializable {

    Usuario save(final Usuario usuario);

    List<Usuario> getAll();

    Usuario findByName(final String nome);
}
