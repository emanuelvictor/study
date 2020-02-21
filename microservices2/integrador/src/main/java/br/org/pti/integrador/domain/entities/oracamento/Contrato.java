package br.org.pti.integrador.domain.entities.oracamento;

import br.org.pti.integrador.domain.entities.oracamento.converter.SituacaoContatoConverter;
import br.org.pti.integrador.infrastructure.utils.jpa.ReadOnlyProtheusPersistentEntity;
import br.org.pti.integrador.infrastructure.utils.jpa.converters.StringDateConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 16/01/2019
 */
@Entity
@Table(name = "cn9010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Contrato extends ReadOnlyProtheusPersistentEntity {

    @Getter
    @Setter
    @Column(name = "cn9_numero", nullable = false)
    private String codigo;
    @Getter
    @Setter
    @Column(name = "cn9_revisa", nullable = false)
    private String revisao;
    @Getter
    @Setter
    @Column(name = "cn9_situac", nullable = false)
    @Convert(converter = SituacaoContatoConverter.class)
    private SituacaoContrato situacaoContrato;
    @Getter
    @Setter
    @Column(name = "cn9_dtinic", nullable = false)
    @Convert(converter = StringDateConverter.class)
    private LocalDate dataInicio;
    @Getter
    @Setter
    @Column(name = "cn9_dtfim", nullable = false)
    @Convert(converter = StringDateConverter.class)
    private LocalDate dataFim;
    @Getter
    @Setter
    @Column(name = "cn9_vlini", nullable = false)
    private BigDecimal valorInicial;
    @Getter
    @Setter
    @Column(name = "cn9_vlatu", nullable = false)
    private BigDecimal valorAtual;
    @Getter
    @Setter
    @Column(name = "cn9_saldo", nullable = false)
    private BigDecimal valorSaldo;
    @Getter
    @Setter
    @Transient
    @JsonProperty("itensContrato")
    private List<ItemContrato> itensContrato;
}
