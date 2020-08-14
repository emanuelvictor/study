package br.org.pti.api.nonfunctional.authengine.domain.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;


/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
public class Permission implements GrantedAuthority {

    /**
     *
     */
    @NotNull
    private String authority;

    /**
     *
     */
    public Permission() {
    }

    /**
     * @param authority
     */
    public Permission(@NotNull final String authority) {
        this.authority = authority;
    }
}
