package br.org.pti.authorizationserver.domain.entities.configuration;

import br.org.pti.authorizationserver.domain.entities.EntityIdResolver;
import br.org.pti.authorizationserver.domain.entities.PersistentEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;


/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@Entity
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = AccessGroup.class,
        resolver = EntityIdResolver.class
)
public class AccessGroup extends PersistentEntity {

    /**
     *
     */
    @NotBlank
    @Length(max = 50)
    @Column(nullable = false, unique = true, length = 50)
    private String nome;

    /**
     *
     */
    @Column(nullable = false)
    private boolean ativo = true;

    /**
     *
     */
    @EqualsAndHashCode.Exclude
    @OneToMany(targetEntity = AccessGroupPermission.class, mappedBy = "grupoAcesso", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<AccessGroupPermission> gruposAcessoPermissoes;

}
