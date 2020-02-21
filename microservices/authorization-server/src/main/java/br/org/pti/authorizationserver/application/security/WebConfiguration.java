package br.org.pti.authorizationserver.application.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 2.0.0, 18/03/2020
 */
@Configuration
@RequiredArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

    private final List<Formatter<?>> formatters;

    /**
     * {@inheritDoc}
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/static/");
    }

    /**
     * {@inheritDoc}
     *
     * @param registry
     */
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/autenticacao");
        registry.addRedirectViewController("/favicon.ico", "/resources/images/favicon.png");
        registry.addRedirectViewController("/logo", "/resources/images/logo.png");
    }

    /**
     * {@inheritDoc}
     *
     * @param registry
     */
    @Override
    public void addFormatters(final FormatterRegistry registry) {
        this.formatters.forEach(registry::addFormatter);
    }
}
