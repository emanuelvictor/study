package br.org.pti.integrator.domain.entities.contabilidade;

import br.org.pti.integrator.domain.entities.compras.Cliente;
import br.org.pti.integrator.domain.entities.contabilidade.converters.StatusIntegracaoConverter;
import br.org.pti.integrator.infrastructure.utils.jpa.ProtheusPersistentEntity;
import br.org.pti.integrator.infrastructure.utils.jpa.converters.StringDateConverter;
import br.org.pti.integrator.infrastructure.utils.jpa.converters.StringTimeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * A classe que representa as notas fiscais
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.1
 * @since 1.0.0, 19/09/17
 */
@Entity
@Table(name = "zi2010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PreNota extends ProtheusPersistentEntity {

    @Id
    @Getter
    @Column(name = "r_e_c_n_o_", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ZI2_SEQ")
    @SequenceGenerator(name = "ZI2_SEQ", sequenceName = "ZI2010_SEQ", allocationSize = 1)
    private Long recno;

    @Getter
    @Setter
    @Column(name = "zi2_codnfe", nullable = false)
    @NotBlank(message = "pre-nota.codigo-vazio")
    private String codigo;
    @Getter
    @Setter
    @Column(name = "zi2_nfelet", nullable = false)
    @NotBlank(message = "pre-nota.numero-vazio")
    private String numero;
    @Getter
    @Setter
    @Column(name = "zi2_serie", nullable = false)
    @NotBlank(message = "pre-nota.serie-vazio")
    private String serie;
    @Getter
    @Setter
    @Column(name = "zi2_eminfe", nullable = false)
    @NotNull(message = "pre-nota.data-emissaonfe-invalido")
    @Convert(converter = StringDateConverter.class)
    private LocalDate dataEmissaoNfe;
    @Getter
    @Setter
    @Column(name = "zi2_hornfe", nullable = false)
    @NotNull(message = "pre-nota.hora-emissaonfe-invalido")
    @Convert(converter = StringTimeConverter.class)
    private LocalTime horaEmissaoNfe;
    @Getter
    @Setter
    @Column(name = "zi2_descon", nullable = false)
    @NotNull(message = "pre-nota.valor-desconto-invalido")
    private BigDecimal valorDesconto;
    @Getter
    @Setter
    @Column(name = "zi2_valfat", nullable = false)
    @NotNull(message = "pre-nota.valor-fatura-invalido")
    private BigDecimal valorFatura;
    @Getter
    @Setter
    @Column(name = "zi2_especi", nullable = false)
    @NotBlank(message = "pre-nota.especie-vazio")
    private String especie;
    @Getter
    @Setter
    @Column(name = "zi2_tipo", nullable = false)
    @NotBlank(message = "pre-nota.tipo-nota-vazio")
    private String tipoNota;
    @Getter
    @Setter
    @Column(name = "zi2_doc", nullable = false)
    @NotBlank(message = "pre-nota.documento-vazio")
    private String documento;
    @Getter
    @Setter
    @Column(name = "zi2_est", nullable = false)
    @NotBlank(message = "pre-nota.estado-vazio")
    private String estado;
    @Getter
    @Setter
    @Column(name = "zi2_lote", nullable = false, length = 6)
    private long lote;    
    @Getter
    @Setter
    @Column(name = "zi2_stats", nullable = false)
    @Convert(converter = StatusIntegracaoConverter.class)
    private StatusIntegracao statusIntegracao;

    @Getter
    @Setter
    @ManyToOne
    @NotNull(message = "pre-nota.cliente-nao-informado")
    @JoinColumns({
        @JoinColumn(name = "zi2_client", referencedColumnName = "a1_cod")
        ,
        @JoinColumn(name = "zi2_loja", referencedColumnName = "a1_loja")
    })
    private Cliente cliente;

    @Getter
    @Setter
    @Transient
    @JsonProperty("itensNotaFiscal")
    @NotEmpty(message = "pre-nota.itens-nota-vazio")
    private List<ItemPreNota> itensPreNota;

    /**
     * Construtor da classe
     */
    public PreNota() {
        this.statusIntegracao = StatusIntegracao.NAO_INTEGRADO;
    }
    
    /**
     * @return se a nota ainda esta pendente de integracao
     */
    @JsonIgnore
    public boolean isNaoIntegrado() {
        return this.statusIntegracao == StatusIntegracao.NAO_INTEGRADO;
    }
    
    /**
     * @return se a nota ja encontra-se integrado
     */
    @JsonIgnore
    public boolean isIntegrado() {
        return this.statusIntegracao == StatusIntegracao.INTEGRADO;
    }    
}
