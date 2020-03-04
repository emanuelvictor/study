package br.org.pti.api.functional.authengine;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@SpringBootTest
class AccountManagerApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(new BCryptPasswordEncoder(12).encode("account-manager"));
	}

}
