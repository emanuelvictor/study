package br.org.pti.integrador.domain.entities.compras;

import br.org.pti.integrador.domain.entities.compras.converters.TipoPessoaConverter;
import br.org.pti.integrador.infrastructure.utils.jpa.ReadOnlyProtheusPersistentEntity;
import br.org.pti.integrador.infrastructure.utils.jpa.converters.BlockedClientConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 19/09/17
 */
@Entity
@Table(name = "sa1010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Cliente extends ReadOnlyProtheusPersistentEntity {

    @Getter
    @Setter
    @Column(name = "a1_cod", nullable = false)
    private String codigo;
    @Getter
    @Setter
    @Column(name = "a1_loja", nullable = false)
    private String loja;
    @Getter
    @Setter
    @Column(name = "a1_cgc", nullable = false)
    private String documento;
    @Getter
    @Setter
    @Column(name = "a1_nome", nullable = false)
    private String nome;
    @Getter
    @Setter
    @Column(name = "a1_email", nullable = false)
    private String email;
    @Getter
    @Setter
    @Column(name = "a1_end", nullable = false)
    private String endereco;
    @Getter
    @Setter
    @Column(name = "a1_bairro", nullable = false)
    private String bairro;
    @Getter
    @Setter
    @Column(name = "a1_ddd", nullable = false)
    private String ddd;
    @Getter
    @Setter
    @Column(name = "a1_tel", nullable = false)
    private String telefone;
    @Setter
    @Column(name = "a1_msblql", nullable = false)
    @Convert(converter = BlockedClientConverter.class)
    private boolean bloqueado;  
    
    @Getter
    @Setter
    @Column(name = "a1_pessoa", nullable = false)
    @Convert(converter = TipoPessoaConverter.class)
    private TipoPessoa tipoCliente;

    @Getter
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "a1_est", referencedColumnName = "cc2_est"),
        @JoinColumn(name = "a1_cod_mun", referencedColumnName = "cc2_codmun")
    })
    private Municipio municipio;
    @Getter
    @ManyToOne
    @JoinColumn(name = "a1_pais", referencedColumnName = "ya_codgi")
    private Pais pais;
}
