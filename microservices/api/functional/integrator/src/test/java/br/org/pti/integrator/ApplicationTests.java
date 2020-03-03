package br.org.pti.integrator;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 2.0.0, 30/01/2020
 */
class ApplicationTests {

	/**
	 *
	 */
	@Test
	void contextLoads() {
		System.out.println(this.encoder().encode("patrimonio"));
	}

	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(12);
	}
}
