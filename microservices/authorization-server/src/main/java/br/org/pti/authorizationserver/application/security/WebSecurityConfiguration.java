package br.org.pti.authorizationserver.application.security;

import br.org.pti.authorizationserver.application.components.Autorizacoes;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 2.0.0, 31/01/2020
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * {@inheritDoc}
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(final WebSecurity web) {
        web.ignoring().antMatchers("/resources/**");
    }

    /**
     * {@inheritDoc}
     *
     * @param builder
     * @throws Exception
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder builder) throws Exception {
        builder
                .ldapAuthentication()
                .contextSource()
                .url("ldap://dc04.fpti.pti.org.br/OU=Admins,DC=fpti,DC=pti,DC=org,DC=br")
                .managerDn("CN=bindintegrador,OU=Aplicacoes,DC=fpti,DC=pti,DC=org,DC=br")
                .managerPassword("XNTtUQK5PeeStSTwGvjfaY")
                .and()
                .userDnPatterns("OU=Admins,DC=fpti,DC=pti,DC=org,DC=br")
                .userSearchFilter("sAMAccountName={0}");
    }

    /**
     * {@inheritDoc}
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/actuator/health")
                .permitAll()
                .antMatchers("/actuator/**")
                .hasAnyAuthority(Autorizacoes.ADMINISTRADOR)
                .antMatchers("/seguro/**")
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/autenticacao")
                .failureUrl("/autenticacao/erro")
                .loginProcessingUrl("/autenticacao/executar")
                .defaultSuccessUrl("/dashboard")
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .logoutUrl("/logout")
                .logoutSuccessUrl("/dashboard");
    }

    /**
     * @return AuthenticationManager
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
