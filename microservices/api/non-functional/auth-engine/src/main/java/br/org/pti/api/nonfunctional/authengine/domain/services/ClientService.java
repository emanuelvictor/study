package br.org.pti.api.nonfunctional.authengine.domain.services;

import br.org.pti.api.nonfunctional.authengine.domain.entities.GrantType;
import br.org.pti.api.nonfunctional.authengine.domain.repositories.feign.IClientFeignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 2.0.0, 04/01/2020
 */
@Service
@RequiredArgsConstructor
public class ClientService implements ClientDetailsService {

    /**
     *
     */
    private final IClientFeignRepository clientFeignRepository;

    /**
     * @param clientId String
     * @return ClientDetails
     * @throws ClientRegistrationException
     */
    @Override
    public ClientDetails loadClientByClientId(final String clientId) throws ClientRegistrationException {

        if (clientId.equals("auth-engine")) {
            final ClientDetails clientDetails = new ClientDetails() {

                /**
                 * @return String
                 */
                @Override
                public boolean isSecretRequired() {
                    return true;
                }

                /**
                 *
                 * @return String
                 */
                @Override
                public String getClientSecret() {
                    return "$2a$12$V.mEGBHyJ7Feo2I48fYmi.je.ir5nqAPWjtNwGZv5XUZHUmgoz1Ne";
                }

                /**
                 * @return boolean
                 */
                @Override
                public boolean isScoped() {
                    return true;
                }

                /**
                 * @return Set<String>
                 */
                @Override
                public Set<String> getScope() {
                    return Set.of("root");
                }

                /**
                 * @return Set<String>
                 */
                @Override
                public Set<String> getAuthorizedGrantTypes() {
                    return getGrantTypeStrings();
                }


                /**
                 * @return Collection<GrantedAuthority>
                 */
                @Override
                public Collection<GrantedAuthority> getAuthorities() {
                    final List<GrantedAuthority> role = new ArrayList<>();
                    role.add(() -> "root");
                    return role;
                }

                /**
                 * @return Integer
                 */
                @Override
                public Integer getAccessTokenValiditySeconds() {
                    return 60;
                }

                /**
                 * @return Integer
                 */
                @Override
                public Integer getRefreshTokenValiditySeconds() {
                    return 999999999;
                }

                /**
                 * @param scope String
                 * @return boolean
                 */
                @Override
                public boolean isAutoApprove(final String scope) {
                    return true;
                }

                /**
                 * Non necessary for now.
                 *
                 * @return Set<String></String>
                 */
                @Override
                public Set<String> getRegisteredRedirectUri() {
                    return Set.of("http://0.0.0.0:9000/#/");
                }

                /**
                 *
                 * @return String
                 */
                @Override
                public String getClientId() {
                    return "auth-engine";
                }

                /**
                 * Non necessary
                 *
                 * @return Set<String>
                 */
                @Override
                public Set<String> getResourceIds() {
                    return Set.of();
                }

                /**
                 * Non necessary
                 *
                 * @return Map<String, Object>
                 */
                @Override
                public Map<String, Object> getAdditionalInformation() {
                    return Map.of();
                }

            };

            return clientDetails;
        }

        return this.clientFeignRepository.loadClientByClientId(clientId)
                .orElseThrow(() -> new UsernameNotFoundException("ClientId " + clientId + " não localizado!"));
    }

    public static Set<String> getGrantTypeStrings() {
        final Set<String> authorizedGrantTypes = new HashSet<>();
        authorizedGrantTypes.add(GrantType.AUTHORIZATION_CODE.getGrantType());
        authorizedGrantTypes.add(GrantType.CLIENT_CREDENTIALS.getGrantType());
        authorizedGrantTypes.add(GrantType.IMPLICIT.getGrantType());
        authorizedGrantTypes.add(GrantType.PASSWORD.getGrantType());
        authorizedGrantTypes.add(GrantType.REFRESH_TOKEN.getGrantType());

        authorizedGrantTypes.addAll(Set.of("password", "authorization_code", "implicit", "token", "refresh_token", "client_credentials"));

        return authorizedGrantTypes;
    }

}
