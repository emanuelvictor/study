package br.org.pti.api.functional.integrator.domain.entities.contabilidade;

import br.org.pti.api.functional.integrator.infrastructure.utils.jpa.ReadOnlyProtheusPersistentEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 24/10/2017
 */
@Entity
@Table(name = "ct1010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ContaContabil extends ReadOnlyProtheusPersistentEntity {

    @Getter
    @Setter
    @Column(name = "ct1_conta", nullable = false)
    private String conta;
    @Getter
    @Setter
    @Column(name = "ct1_desc01", nullable = false)
    private String descricao;
    @Getter
    @Setter
    @JsonIgnore
    @Column(name = "ct1_classe", nullable = false)
    private String classe;
    @Getter
    @Setter
    @JsonIgnore
    @Column(name = "ct1_bloq", nullable = false)
    private String bloqueada;    

    /**
     * Construtor da classe
     */
    public ContaContabil() {}
    
}
