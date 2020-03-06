package br.org.pti.api.functional.integrator.domain.entities.contabilidade;

import br.org.pti.api.functional.integrator.infrastructure.utils.jpa.ReadOnlyProtheusPersistentEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Esta classe representa a tabela onde e feito o vinculo entre colaborador e
 * usuario no Protheus
 *
 * @author willian.brecherju
 *
 * @version 1.0.0
 * @since 1.0.0, 29/08/2019
 */
@Entity
@ToString
@NoArgsConstructor
@Table(name = "gestor_centrocusto")
@EqualsAndHashCode(callSuper = true)
public class GestorCentroCusto extends ReadOnlyProtheusPersistentEntity {
    
    @Getter
    @Column(name = "centro_custo")
    private String centroCusto;
    @Getter
    @Column(name = "descricao_centro_custo")
    private String descricaoCentroCusto;  
    @Getter
    @Column(name = "nome_usuario")
    private String nomeGestor;
    @Getter
    @Column(name = "email_usuario")
    private String emailGestor;
    @Getter
    @Column(name = "matricula_usuario")
    private String matriculaGestor;
    
}
