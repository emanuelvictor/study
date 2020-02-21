package br.org.pti.authorizationserver.domain.entities.security;

import br.org.pti.authorizationserver.domain.entities.PersistentEntity;
import lombok.*;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 2.0.0, 01/01/2020
 */
@Entity
@Audited
@ToString
@NoArgsConstructor
@Table(name = "permissao")
@EqualsAndHashCode(callSuper = true)
public class Permissao extends PersistentEntity implements GrantedAuthority {

    /**
     *
     */
    @Setter
    @Getter
    private String nome;

    /**
     *
     */
    @Setter
    @Getter
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "permissoes")
    private Set<Aplicacao> aplicacoes;

    /**
     * @param id long
     */
    public Permissao(final Long id) {
        this.setId(id);
    }

    /**
     * @param nome
     */
    public Permissao(final String nome) {
        this.nome = nome;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public String getAuthority() {
        return nome;
    }
}
