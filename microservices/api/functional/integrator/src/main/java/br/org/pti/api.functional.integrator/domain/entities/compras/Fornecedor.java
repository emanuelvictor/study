package br.org.pti.api.functional.integrator.domain.entities.compras;

import br.org.pti.api.functional.integrator.domain.entities.compras.converters.*;
import br.org.pti.api.functional.integrator.infrastructure.utils.jpa.ReadOnlyProtheusPersistentEntity;
import br.org.pti.api.functional.integrator.infrastructure.utils.jpa.converters.BlockedClientConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Willian Brecher
 * @version 1.0.0
 * @since 1.0.0, 02/05/2019
 */
@Entity
@Table(name = "sa2010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Fornecedor extends ReadOnlyProtheusPersistentEntity {

    @Getter
    @Setter
    @Column(name = "a2_cod", nullable = false)
    private String codigo;
    @Getter
    @Setter
    @Column(name = "a2_loja", nullable = false)
    private String loja;
    @Getter
    @Setter
    @Column(name = "a2_cgc", nullable = false)
    private String documento;
    @Getter
    @Setter
    @Column(name = "a2_nome", nullable = false)
    private String nome;
    @Getter
    @Setter
    @Column(name = "a2_nreduz", nullable = false)
    private String nomeFantasia;
    @Getter
    @Setter
    @Column(name = "a2_email", nullable = false)
    private String email;
    @Getter
    @Setter
    @Column(name = "a2_end", nullable = false)
    private String endereco;
    @Getter
    @Setter
    @Column(name = "a2_nr_end", nullable = false)
    private String numero;
    @Getter
    @Setter
    @Column(name = "a2_bairro", nullable = false)
    private String bairro;
    @Getter
    @Setter
    @Column(name = "a2_cep", nullable = false)
    private String cep;
    @Getter
    @Setter
    @Column(name = "a2_ddd", nullable = false)
    private String ddd;
    @Getter
    @Setter
    @Column(name = "a2_tel", nullable = false)
    private String telefone;
    @Getter
    @Setter
    @Column(name = "a2_inscrm", nullable = false)
    private String inscricaoMunicipal;
    @Getter
    @Setter
    @Column(name = "a2_inscr", nullable = false)
    private String inscricaoEstadual;
    @Getter
    @Setter
    @Column(name = "a2_complem", nullable = false)
    private String complemento;
    @Getter
    @Setter
    @Column(name = "a2_agencia", nullable = false)
    private String agencia;
    @Getter
    @Setter
    @Column(name = "a2_numcon", nullable = false)
    private String numeroConta;

    @Setter
    @Column(name = "a2_msblql", nullable = false)
    @Convert(converter = BlockedClientConverter.class)
    private boolean bloqueado;

    @Getter
    @Setter
    @Column(name = "a2_simpnac", nullable = false)
    @Convert(converter = OpcionalNumericoConverter.class)
    private OpcionalNumerico simplesNacional;
    @Getter
    @Setter
    @Column(name = "a2_recpis", nullable = false)
    @Convert(converter = OpcionalNumericoConverter.class)
    private OpcionalNumerico recolhimentoPIS;
    @Getter
    @Setter
    @Column(name = "a2_reccofi", nullable = false)
    @Convert(converter = OpcionalNumericoConverter.class)
    private OpcionalNumerico recolhimentoCOFINS;
    @Getter
    @Setter
    @Column(name = "a2_reccsll", nullable = false)
    @Convert(converter = OpcionalNumericoConverter.class)
    private OpcionalNumerico recolhimentoCSLL;

    @Getter
    @Setter
    @Column(name = "a2_reciss", nullable = false)
    @Convert(converter = OpcionalStringConverter.class)
    private OpcionalString recolhimentoISS;
    @Getter
    @Setter
    @Column(name = "a2_calcirf", nullable = false)
    @Convert(converter = CalculaIRRFConverter.class)
    private CalculaIRRF calculaIRRF;
    @Getter
    @Setter
    @Column(name = "a2_recinss", nullable = false)
    @Convert(converter = OpcionalStringConverter.class)
    private OpcionalString calculaINSS;
    @Getter
    @Setter
    @Column(name = "a2_x_coop", nullable = false)
    @Convert(converter = OpcionalStringConverter.class)
    private OpcionalString cooperativa;

    @Getter
    @Setter
    @Column(name = "a2_tipo", nullable = false)
    @Convert(converter = TipoPessoaConverter.class)
    private TipoPessoa tipoPessoa;

    @Getter
    @Setter
    @Column(name = "a2_tpessoa", nullable = false)
    @Convert(converter = TipoFornecedorConverter.class)
    private TipoFornecedor tipoFornecedor;

    @Getter
    @Setter
    @Column(name = "a2_tpj", nullable = false)
    @Convert(converter = TipoPessoaJuridicaFornecedorConverter.class)
    private TipoPessoaJuridicaFornecedor tipoPessoaJuridicaFornecedor;

    @Getter
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "a2_est", referencedColumnName = "cc2_est"),
            @JoinColumn(name = "a2_cod_mun", referencedColumnName = "cc2_codmun")
    })
    private Municipio municipio;

    @Getter
    @ManyToOne
    @JoinColumn(name = "a2_pais", referencedColumnName = "ya_codgi")
    private Pais pais;

    @Getter
    @ManyToOne
    @JoinColumn(name = "a2_naturez", referencedColumnName = "ed_codigo")
    private Natureza natureza;

    @Getter
    @ManyToOne
    @JoinColumn(name = "a2_banco", referencedColumnName = "codigo")
    private ViewBancos banco;
}
