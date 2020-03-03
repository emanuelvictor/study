package br.org.pti.api.functional.portalcompras.application.security;

import br.org.pti.api.functional.portalcompras.application.handler.AuthenticationFailureHandler;
import br.org.pti.api.functional.portalcompras.application.handler.AuthenticationSuccessHandler;
import br.org.pti.api.functional.portalcompras.application.handler.AuthenticationFailureHandler;
import br.org.pti.api.functional.portalcompras.application.handler.AuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

    /**
     *
     */
    private final UserDetailsService userDetailsService;

    /**
     * @return {AuthenticationFailureHandler}
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandler();
    }

    /**
     * @return {AuthenticationSuccessHandler}
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler();
    }

    /**
     * @return {PasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     *
     */
    @Override
    public void init(AuthenticationManagerBuilder builder) throws Exception {
        builder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(this.passwordEncoder());
    }
}
