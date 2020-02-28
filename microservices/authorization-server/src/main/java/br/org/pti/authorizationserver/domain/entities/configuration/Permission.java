package br.org.pti.authorizationserver.domain.entities.configuration;

import br.org.pti.authorizationserver.domain.entities.EntityIdResolver;
import br.org.pti.authorizationserver.domain.entities.PersistentEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;


/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@Entity
@Audited
@EqualsAndHashCode(callSuper = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Permission.class,
        resolver = EntityIdResolver.class)
public class Permission extends PersistentEntity implements GrantedAuthority {

    /**
     *
     */
    @NotBlank
    @Column(nullable = false)
    private String nome;

    /**
     *
     */
    @NotNull
    @Column(nullable = false, unique = true)
    private String identificador;

    /**
     *
     */
    @JsonProperty
    @ManyToOne(fetch = FetchType.EAGER)
    private Permission permissaoSuperior;

    /**
     *
     */
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "permissaoSuperior")
    private Set<Permission> permissoesInferiores;

    /**
     *
     */
    public Permission() {
    }

    /**
     * @param identificador String
     */
    public Permission(@NotNull final String identificador) {
        this.identificador = identificador;
    }

    /**
     * @return Permissao
     */
    public static Permission getAdministratorInstance() {
        final Permission permissao = new Permission();
        permissao.setIdentificador("root"); // TODO separar em uma variável global
        return permissao;
    }

    /**
     * @return String
     */
    @Override
    @Transient
    public String getAuthority() {
        return this.identificador;
    }
}
