package br.org.pti.api.functional.portalcompras;

import br.org.pti.api.functional.portalcompras.infrastructure.report.IReportManager;
import br.org.pti.api.functional.portalcompras.infrastructure.report.jasper.JasperReportManager;
import br.org.pti.api.functional.portalcompras.application.i18n.ResourceBundleMessageSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.sql.DataSource;
import javax.validation.Validator;

@EnableAsync
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ComprasApplication extends SpringBootServletInitializer {

    /*
     * ---------------------------------------------------------
     *                          Schemas
     * ---------------------------------------------------------
     */
    public static final String CADASTRO = "cadastro";
    public static final String PUBLICACAO = "publicacao";
    public static final String CONFIGURACAO = "configuracao";
    public static final String FORNECEDOR = "fornecedor";
    public static final String ENDERECO = "endereco";

    /**
     * Esquema público
     */
    public static final String PUBLIC = "public";

    /**
     * Esquema da tabela de revisão
     */
    public static final String REVISION = PUBLIC;

    public static final String AUDIT_SUFFIX = "_audit";

    /**
     * @param args {String[]}
     */
    public static void main(String[] args) {
        SpringApplication.run(ComprasApplication.class, args);
    }


    /**
     * @param application SpringApplicationBuilder
     * @return SpringApplicationBuilder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ComprasApplication.class);
    }

    /**
     * @return MessageSource
     */
    @Bean
    public MessageSource messageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setAlwaysUseMessageFormat(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasenames("classpath:i18n/exceptions", "classpath:i18n/labels", "classpath:i18n/messages");
        return messageSource;
    }

    /**
     * @return Validator
     */
    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * @return IReportManager
     */
    @Bean
    public IReportManager reportManager(final DataSource dataSource) {
        return new JasperReportManager(dataSource);
    }
}
