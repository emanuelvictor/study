package br.org.pti.api.nonfunctional.authengine.domain.entities;

import br.org.pti.api.nonfunctional.authengine.domain.entities.generic.PersistentEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;


/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIdentityInfo(
        property = "id",
        scope = AccessGroup.class,
        generator = ObjectIdGenerators.PropertyGenerator.class
)
public class AccessGroup extends PersistentEntity {

    /**
     *
     */
    private String name;

    /**
     *
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<AccessGroupPermission> accessGroupPermissions;

}
