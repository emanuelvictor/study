package br.org.pti.api.functional.portalcompras.application.ldap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * @author Wellington Felipe Fucks
 * @version 1.0
 * @since 1.0, 11/07/2016
 */
@Configuration
public class LdapConfig {

    @Value("${ldap.url}")
    private String ldapUrl;

    @Value("${ldap.username}")
    private String usernameLdap;

    @Value("${ldap.password}")
    private String passwordLdap;

    @Bean
    public LdapTemplate ldapTemplate() {
        final LdapTemplate template = new LdapTemplate();
        final LdapContextSource cs = new LdapContextSource();

        cs.setUrl(ldapUrl);
        cs.setUserDn(usernameLdap);
        cs.setPassword(passwordLdap);
        cs.afterPropertiesSet();

        template.setContextSource(cs);

        return template;
    }

}
