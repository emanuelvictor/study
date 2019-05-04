package br.com.emanuelvictor.books.application.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

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
                .permitAll();
    }

    /**
     * Override this method to configure {@link WebSecurity}. For example, if you wish to
     * ignore certain requests.
     */
    @Override
    public void configure(final WebSecurity web) {
        web.ignoring().antMatchers("/**/favicon.ico", "/static/**", "/modules/**", "/bundles/**");
    }

}
