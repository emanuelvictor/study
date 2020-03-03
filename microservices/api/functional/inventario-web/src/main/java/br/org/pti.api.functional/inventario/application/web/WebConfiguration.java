package br.org.pti.api.functional.inventario.application.web;

import br.org.pti.api.functional.inventario.application.context.ContextHolder;
import br.org.pti.api.functional.inventario.application.i18n.ResourceBundleMessageSource;
import br.org.pti.api.functional.inventario.domain.entity.configuracao.Usuario;
import br.org.pti.api.functional.inventario.domain.repository.IUsuarioRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.filter.ForwardedHeaderFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static br.org.pti.api.functional.inventario.InventarioApplication.LOGGER;

/**
 * Define a configuração web da aplicação
 *
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Configuration
@RequiredArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

    /**
     *
     */
    private final IUsuarioRepository usuarioRepository;

    /**
     * @return {LocaleChangeInterceptor}
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    /**
     * @return {HandlerInterceptor}
     */
    @Bean
    public HandlerInterceptor verifyAccessInterceptor() {

        return new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

                if (!ContextHolder.isAuthenticated())
                    return true;

                final Usuario usuario = usuarioRepository.findById(ContextHolder.getAuthenticatedUser().getId()).orElseThrow();

                if (!usuario.getAtivo() || !usuario.isEnabled()) {
                    SecurityContextHolder.clearContext();
                    return true;
                }

                final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

                final Set<GrantedAuthority> updatedAuthorities = usuario.getAuthorities();

                final Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);

                SecurityContextHolder.getContext().setAuthentication(newAuth);

                return true;
            }
        };
    }

    /**
     *
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.localeChangeInterceptor());
        registry.addInterceptor(this.verifyAccessInterceptor());
    }

    /**
     * @return {CommonsMultipartResolver}
     */
    @Bean
    public CommonsMultipartResolver commonsMultipartResolver() {
        final CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000000);
        return multipartResolver;
    }

    /**
     * @return {FilterRegistrationBean<ForwardedHeaderFilter>}
     */
    @Bean
    public FilterRegistrationBean<ForwardedHeaderFilter> forwardedHeaderFilter() {
        final FilterRegistrationBean<ForwardedHeaderFilter> filterRegBean = new FilterRegistrationBean<>();
        filterRegBean.setFilter(new ForwardedHeaderFilter());
        filterRegBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegBean;
    }

    /**
     * @return {LocaleResolver}
     */
    @Bean
    public LocaleResolver localeResolver() {
        final CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("pt", "BR"));
        localeResolver.setCookieMaxAge(604800); // 1 month
        return localeResolver;
    }

    /**
     * Habilita o Jackson para retornar a data formatada
     *
     * @param builder {Jackson2ObjectMapperBuilder}
     * @return {ObjectMapper}
     */
    @Bean
    public ObjectMapper objectMapper(final Jackson2ObjectMapperBuilder builder) {
        final ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

    /**
     * @param registry {ResourceHandlerRegistry}
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/modules/");
        registry.addResourceHandler("/static/**", "/mobile/static/**")
                .addResourceLocations("classpath:/META-INF/resources/static/");
    }


    /**
     * @return ApplicationListener<ApplicationReadyEvent>
     */
    @Bean
    public ApplicationListener<ApplicationReadyEvent> getApplicationReadyEvent() {
        return applicationReadyEvent -> {
            LOGGER.info("--------------------------------------------------");

            final List<String> profiles = Arrays.asList(applicationReadyEvent.getApplicationContext().getEnvironment().getActiveProfiles());

            if (profiles.isEmpty()) {
                LOGGER.info("Sistema iniciado com o perfil de configuração: dev");
            }

            profiles.forEach(profile ->
                    LOGGER.info("Sistema iniciado com o perfil de configuração: {}", profile)
            );
            LOGGER.info("--------------------------------------------------");
        };
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
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    /**
     * @return Validator
     */
    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
