package br.org.pti.api.nonfunctional.authengine.domain.entities;

import br.org.pti.api.nonfunctional.authengine.domain.entities.generic.PersistentEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;
import java.util.Set;


/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@EqualsAndHashCode(callSuper = true)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id",TODO verificar
//        scope = Permission.class,
//        resolver = EntityIdResolver.class)
public class Permission extends PersistentEntity implements GrantedAuthority {

    /**
     *
     */
    @NotNull
    private String authority;

    /**
     *
     */
    @JsonProperty
    private Permission upperPermission;

    /**
     *
     */
    @EqualsAndHashCode.Exclude
    private Set<Permission> lowerPermissions;

    /**
     *
     */
    public Permission() {
    }

}
