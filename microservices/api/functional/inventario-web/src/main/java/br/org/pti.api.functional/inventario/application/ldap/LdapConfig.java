package br.org.pti.api.functional.inventario.application.ldap;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Configuration
public class LdapConfig {

    /**
     *
     */
    @Getter
    @Value("${ldap.url:}")
    private String url;

    /**
     *
     */
    @Getter
    @Value("${ldap.username:}")
    private String username;

    /**
     *
     */
    @Getter
    @Value("${ldap.password:}")
    private String password;

    /**
     *
     */
    @Getter
    @Value("${ldap.base:}")
    private String base;

    /**
     *
     */
    @Getter
    @Value("${ldap.searchBase:}")
    private String searchBase;

    /**
     *
     */
    @Getter
    @Value("${ldap.basedn}")
    private String baseDN;


    /**
     * @return LdapTemplate
     */
    @Bean
    public LdapTemplate ldapTemplate() {
        final LdapTemplate template = new LdapTemplate();
        final LdapContextSource cs = new LdapContextSource();

        cs.setUrl(url);
        cs.setUserDn(username);
        cs.setPassword(password);
        cs.afterPropertiesSet();

        template.setContextSource(cs);

        return template;
    }

}
