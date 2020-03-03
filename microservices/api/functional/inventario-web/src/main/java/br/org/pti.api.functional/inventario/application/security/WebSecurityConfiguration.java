package br.org.pti.api.functional.inventario.application.security;

import br.org.pti.api.functional.inventario.application.handler.AuthenticationFailureHandler;
import br.org.pti.api.functional.inventario.application.handler.AuthenticationSuccessHandler;
import br.org.pti.api.functional.inventario.application.ldap.LdapDbAuthenticationProvider;
import br.org.pti.api.functional.inventario.domain.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import java.util.Collections;


/**
 * Define a configuração de autenticação da aplicação
 *
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Configuration
@EnableOAuth2Client
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;

    /**
     *
     */
    private final LdapDbAuthenticationProvider ldapDbAuthenticationProvider;

    /**
     *
     */
    private final AuthenticationFailureHandler authenticationFailureHandler;

    /**
     *
     */
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     *
     */
    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();

        httpSecurity
                .authorizeRequests().antMatchers("/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("senha")
                .loginPage("/authentication")
                .loginProcessingUrl("/authenticate")
                .failureHandler(this.authenticationFailureHandler)
                .successHandler(this.authenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout").logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
    }

    /**
     * Override this method to configure {@link WebSecurity}. For example, if you wish to
     * ignore certain requests.
     */
    @Override
    public void configure(final WebSecurity web) {
        web.ignoring().antMatchers("/**/favicon.ico", "/static/**", "/modules/**", "/bundles/**");
    }

    /**
     * @param auth AuthenticationManagerBuilder
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(ldapDbAuthenticationProvider)
                .userDetailsService(authenticationService);
    }

    /**
     * @return AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(ldapDbAuthenticationProvider));
    }

}
