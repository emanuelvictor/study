package br.org.pti.integrador.domain.entities.contabilidade;

import br.org.pti.integrador.domain.entities.contabilidade.converters.StatusIntegracaoConverter;
import br.org.pti.integrador.infrastructure.utils.jpa.ProtheusPersistentEntity;
import br.org.pti.integrador.infrastructure.utils.jpa.converters.StringDateConverter;
import br.org.pti.integrador.infrastructure.utils.jpa.converters.StringDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 1.0.0, 29/08/2017
 */
@Entity
@Table(name = "zi1010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PreLancamento extends ProtheusPersistentEntity {

    @Id
    @Getter
    @Column(name = "r_e_c_n_o_", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ZI1_SEQ")
    @SequenceGenerator(name = "ZI1_SEQ", sequenceName = "ZI1010_SEQ", allocationSize = 1)
    private Long recno;

    @Getter
    @Setter
    @Column(name = "zi1_codlct", nullable = false, length = 3)
    @NotBlank(message = "pre-lancamento.codigo-vazio")
    private String codigo;
    @Getter
    @Setter
    @Column(name = "zi1_sequen", nullable = false, length = 3)
    @NotBlank(message = "pre-lancamento.sequencia-vazia")
    private String sequencia;
    @Getter
    @Setter
    @Column(name = "zi1_lote", nullable = false, length = 6)
    private long lote;
    @Getter
    @Setter
    @Column(name = "zi1_data", nullable = false)
    @NotNull(message = "pre-lancamento.data-invalida")
    @Convert(converter = StringDateConverter.class)
    private LocalDate dataLancamento;
    @Getter
    @Setter
    @Column(name = "zi1_valor", nullable = false)
    @NotNull(message = "pre-lancamento.valor-invalido")
    private BigDecimal valor;
    @Getter
    @Setter
    @Column(name = "zi1_histor", nullable = false, length = 40)
    private String descricao;
    @Getter
    @Setter
    @Column(name = "zi1_dthrin")
    @Convert(converter = StringDateTimeConverter.class)
    private LocalDateTime dataIntegracao;

    @Getter
    @Setter
    @Column(name = "zi1_stats", nullable = false)
    @Convert(converter = StatusIntegracaoConverter.class)
    private StatusIntegracao statusIntegracao;

    /**
     * Construtor da classe
     */
    public PreLancamento() {
        this.statusIntegracao = StatusIntegracao.NAO_INTEGRADO;
    }

    /**
     * @return se o pre-lancamento ainda esta pendente de integracao
     */
    @JsonIgnore
    public boolean isNaoIntegrado() {
        return this.statusIntegracao == StatusIntegracao.NAO_INTEGRADO;
    }

    /**
     * @return se o pre-lancamento ja encontra-se integrado
     */
    @JsonIgnore
    public boolean isIntegrado() {
        return this.statusIntegracao == StatusIntegracao.INTEGRADO;
    }
}
