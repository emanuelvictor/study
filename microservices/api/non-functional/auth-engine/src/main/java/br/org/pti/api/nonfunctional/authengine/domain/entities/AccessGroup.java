package br.org.pti.api.nonfunctional.authengine.domain.entities;

import br.org.pti.api.nonfunctional.authengine.domain.entities.generic.PersistentEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Set;


/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
//@JsonIdentityInfo( TODO verificar
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id",
//        scope = AccessGroup.class,
//        resolver = EntityIdResolver.class
//)
public class AccessGroup extends PersistentEntity {

    /**
     *
     */
    @NotBlank
    @Length(max = 50)
    private String name;

    /**
     *
     */
    @EqualsAndHashCode.Exclude
    private Set<AccessGroupPermission> accessGroupPermissions;

}
