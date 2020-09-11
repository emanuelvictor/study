package br.org.pti.api.functional.accountmanager.domain.services;

import br.org.pti.api.functional.accountmanager.application.resource.ApplicationResource;
import br.org.pti.api.functional.accountmanager.application.spring.oauth.custom.JwtTokenStore;
import br.org.pti.api.functional.accountmanager.domain.entities.Application;
import br.org.pti.api.functional.accountmanager.domain.entities.Permission;
import br.org.pti.api.functional.accountmanager.domain.logics.application.ApplicationSavingLogic;
import br.org.pti.api.functional.accountmanager.domain.logics.application.ApplicationUpdatingLogic;
import br.org.pti.api.functional.accountmanager.domain.repositories.ApplicationRepository;
import br.org.pti.api.functional.accountmanager.domain.repositories.PermissionRepository;
import br.org.pti.api.functional.accountmanager.infrastructure.misc.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 2.0.0, 04/01/2020
 */
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TokenService {

    private final Logger LOG = LoggerFactory.getLogger(TokenService.class);

    /**
     *
     */
    private final TokenStore tokenStore;

    /**
     * @param token String
     */
    public void revoke(final String token) {
        ((JwtTokenStore) this.tokenStore).revoke(token);
    }
}
