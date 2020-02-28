package br.org.pti.inventario.domain.service;

import br.org.pti.inventario.domain.TestApplication;
import br.org.pti.inventario.InventarioApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestApplication.class, InventarioApplication.class})
public abstract class AbstractIntegrationTests {

}
