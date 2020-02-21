package br.org.pti.inventario.domain.service;

import br.org.pti.inventario.domain.entity.configuracao.Usuario;
import br.org.pti.inventario.domain.repository.IUsuarioRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;

public class UsuarioServiceIntegrationTests extends AbstractIntegrationTests {
	
	/**
	 *
	 */
	@Autowired
	private UsuarioService usuarioService;


	/**
	 *
	 */
	@Autowired
	private IUsuarioRepository usuarioRepository;

	/**
     *
     */
	@Test
    public void findUsuarioByIdMustPass() {
    	final Usuario usuario = this.usuarioService.findByLdapUsername( "emanuel.fonseca" );

    	assertNotNull( usuario );
    	assertEquals("emanuel.fonseca@pti.org.br", usuario.getEmail());
    }

	/**
	 *
	 */
	@Test
	public void showAdminPasswordHash() {

		final String hash = new BCryptPasswordEncoder().encode("admin");

		assertTrue(new BCryptPasswordEncoder().matches("admin", hash));

	}

}
