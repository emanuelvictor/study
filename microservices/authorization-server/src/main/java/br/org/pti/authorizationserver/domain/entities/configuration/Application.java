package br.org.pti.authorizationserver.domain.entities.configuration;

import br.org.pti.authorizationserver.domain.entities.PersistentEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 2.0.0, 01/01/2020
 */
@Entity
@Audited
@ToString
@Table(name = "aplicacao")
@EqualsAndHashCode(callSuper = true)
public class Application extends PersistentEntity /*implements UserDetails*/ implements ClientDetails {

    /**
     *
     */
    @Getter
    @Setter
    @NotBlank(message = "Um nome deve ser informado")
    @Column(name = "nome", nullable = false, length = 90)
    private String nome;

    /**
     * Corresponds to the clientId
     */
    @Getter
    @Setter
    @NotBlank(message = "Um identificador deve ser informado")
    @Column(name = "identificador", length = 45, nullable = false)
    private String identificador;

    /**
     *
     */
    @Getter
    @Setter
    @NotBlank(message = "Uma senha deve ser informada")
    @Column(name = "senha", length = 90, nullable = false)
    private String senha;

    /**
     *
     */
    @Getter
    @Setter
    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    /**
     *
     */
    @ManyToOne
    private AccessGroup grupoAcesso;


    /**
     *
     */
    public Application() {
        this.ativo = true;
    }

    /**
     * @return String
     */
    @Override
    public String getClientId() {
        return this.identificador;
    }

    /**
     * @return String
     */
    @Override
    public boolean isSecretRequired() {
        return true;
    }

    /**
     * @return String
     */
    @Override
    public String getClientSecret() {
        return this.senha;
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
        return this.grupoAcesso.getGruposAcessoPermissoes().stream().map(AccessGroupPermission::getGrupoAcesso).flatMap((Function<AccessGroup, Stream<?>>) AccessGroup::getGruposAcessoPermissoes).map(o -> o.)
    }

    @Setter
    @Transient
    private String nomePermissoes;

    /**
     * @return String
     */
    public String getNomePermissoes() {
        return String.join(";", getScope());
    }

    /**
     * @return Set<String>
     */
    @Override
    public Set<String> getAuthorizedGrantTypes() {
        final Set<String> authorizedGrantTypes = new HashSet<>();
        authorizedGrantTypes.add(GrantType.AUTHORIZATION_CODE.getGrantType());
        authorizedGrantTypes.add(GrantType.CLIENT_CREDENTIALS.getGrantType());
        authorizedGrantTypes.add(GrantType.IMPLICIT.getGrantType());
        authorizedGrantTypes.add(GrantType.PASSWORD.getGrantType());
        authorizedGrantTypes.add(GrantType.REFRESH_TOKEN.getGrantType());
        return authorizedGrantTypes;
    }


    /**
     * @return Collection<GrantedAuthority>
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return new ArrayList<>(permissoes);
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
        return null;
    }

    /**
     * Non necessary
     *
     * @return Set<String>
     */
    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    /**
     * Non necessary
     *
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
