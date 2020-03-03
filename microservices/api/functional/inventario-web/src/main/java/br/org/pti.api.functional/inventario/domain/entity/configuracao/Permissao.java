package br.org.pti.api.functional.inventario.domain.entity.configuracao;

import br.org.pti.api.functional.inventario.application.resource.Roles;
import br.org.pti.api.functional.inventario.domain.entity.AbstractEntity;
import br.org.pti.api.functional.inventario.domain.entity.EntityIdResolver;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

import static br.org.pti.api.functional.inventario.InventarioApplication.AUDIT_SUFFIX;
import static br.org.pti.api.functional.inventario.InventarioApplication.CONFIGURACAO;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@Entity
@Audited
@Table(schema = CONFIGURACAO)
@EqualsAndHashCode(callSuper = true)
@AuditTable(value = "permissao" + AUDIT_SUFFIX, schema = CONFIGURACAO + AUDIT_SUFFIX)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Permissao.class,
        resolver = EntityIdResolver.class)
public class Permissao extends AbstractEntity implements GrantedAuthority {

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
    private Permissao permissaoSuperior;

    /**
     *
     */
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "permissaoSuperior")
    private Set<Permissao> permissoesInferiores;

    /**
     *
     */
    public Permissao() {
    }

    /**
     * @param identificador String
     */
    public Permissao(@NotNull final String identificador) {
        this.identificador = identificador;
    }

    /**
     * @return Permissao
     */
    public static Permissao getAdministratorInstance() {
        final Permissao permissao = new Permissao();
        permissao.setIdentificador(Roles.ROOT);
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
