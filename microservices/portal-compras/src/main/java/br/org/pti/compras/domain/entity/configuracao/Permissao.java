package br.org.pti.compras.domain.entity.configuracao;

import br.org.pti.compras.application.resource.Roles;
import br.org.pti.compras.domain.entity.generic.AbstractEntity;
import br.org.pti.compras.domain.entity.generic.EntityIdResolver;
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

import static br.org.pti.compras.ComprasApplication.AUDIT_SUFFIX;
import static br.org.pti.compras.ComprasApplication.CONFIGURACAO;

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

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Column(nullable = false, unique = true)
    private String identificador;

    @JsonProperty
    @ManyToOne(fetch = FetchType.EAGER)
    private Permissao permissaoSuperior;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "permissaoSuperior")
    private Set<Permissao> permissoesInferiores;

    public Permissao() {
    }

    public Permissao(@NotNull String identificador) {
        this.identificador = identificador;
    }

    public static Permissao getAdministratorInstance() {
        final Permissao permissao = new Permissao();
        permissao.setIdentificador(Roles.ADMINISTRADOR);
        return permissao;
    }

    @Override
    @Transient
    public String getAuthority() {
        return this.identificador;
    }
}
