package br.org.pti.compras.application.security;

import br.org.pti.compras.application.handler.AuthenticationFailureHandler;
import br.org.pti.compras.application.handler.AuthenticationSuccessHandler;
import br.org.pti.compras.application.ldap.LdapDbAuthenticationProvider;
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

@Configuration
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableOAuth2Client
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

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
                .userDetailsService(userDetailsService());
    }

    /**
     * @return AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(ldapDbAuthenticationProvider));
    }

}
