package br.org.pti.inventario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@EnableAsync
@EnableFeignClients
@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean.class)
public class Application extends SpringBootServletInitializer {

    /*
     * ---------------------------------------------------------
     *                          Schemas
     * ---------------------------------------------------------
     */
    public static final String CONFIGURACAO = "configuracao";
    public static final String ENDERECO = "endereco";
    public static final String PATRIMONIO = "patrimonio";
    public static final String PESSOAL = "pessoal";

    /**
     * Esquema público
     */
    public static final String PUBLIC = "public";

    /**
     * Esquema da tabela de revisão
     */
    public static final String REVISION = PUBLIC;

    /**
     *
     */
    public static final String AUDIT_SUFFIX = "_audit";

    /**
     *
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    /**
     * Run the Spring Boot application
     *
     * @param args {String[]} command line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }


    /**
     * @param application SpringApplicationBuilder
     * @return SpringApplicationBuilder
     */
    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

}
