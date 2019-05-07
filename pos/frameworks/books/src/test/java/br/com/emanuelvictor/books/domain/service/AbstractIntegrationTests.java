package br.com.emanuelvictor.books.domain.service;

import br.com.emanuelvictor.books.Application;
import br.com.emanuelvictor.books.domain.TestApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestApplication.class, Application.class})
public abstract class AbstractIntegrationTests {

}
