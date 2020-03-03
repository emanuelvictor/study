package br.org.pti.api.functional.inventario.domain.service;

import br.org.pti.api.functional.inventario.domain.TestApplication;
import br.org.pti.api.functional.inventario.InventarioApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@SpringBootTest(classes = {TestApplication.class, InventarioApplication.class})
public abstract class AbstractIntegrationTests {

}
