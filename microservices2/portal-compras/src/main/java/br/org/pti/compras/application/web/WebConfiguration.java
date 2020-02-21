package br.org.pti.compras.application.web;

import br.org.pti.compras.application.context.ContextHolder;
import br.org.pti.compras.domain.entity.configuracao.Usuario;
import br.org.pti.compras.domain.repository.IUsuarioRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.Locale;
import java.util.Set;

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

                final Usuario usuario = usuarioRepository.findById(ContextHolder.getAuthenticatedUser().getId()).get();

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
        FilterRegistrationBean<ForwardedHeaderFilter> filterRegBean = new FilterRegistrationBean<ForwardedHeaderFilter>();
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
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
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
        registry.addResourceHandler("/static/**", "/mobile/static/**") //TODO fazer mapeamento automático, como **api**
                .addResourceLocations("classpath:/META-INF/resources/static/");
//
////        registry.addResourceHandler("/api**", "/api/**")
////                .addResourceLocations("/api");
////        registry.addResourceHandler("/modules/**")
////                .addResourceLocations("classpath:/META-INF/resources/modules/");
    }
}
