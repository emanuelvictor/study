package br.com.emanuelvictor.books;

import br.com.emanuelvictor.books.application.i18n.ResourceBundleMessageSource;
import br.com.emanuelvictor.books.infrastructure.report.IReportManager;
import br.com.emanuelvictor.books.infrastructure.report.jasper.JasperReportManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@EnableAsync
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    /**
     * @param args {String[]}
     */
    public static void main(String[] args) {
        //configureApplication(new SpringApplicationBuilder()).run(args);
        SpringApplication.run(Application.class, args);
    }


    /**
     * @param application SpringApplicationBuilder
     * @return SpringApplicationBuilder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

//    /**
//     * @param builder SpringApplicationBuilder
//     * @return SpringApplicationBuilder
//     */
//    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
//        return builder.sources(Application.class).bannerMode(Banner.Mode.OFF);
//    }

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
    public IReportManager reportManager() {
        return new JasperReportManager();
    }
}
