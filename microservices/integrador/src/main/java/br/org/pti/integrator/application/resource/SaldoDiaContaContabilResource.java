package br.org.pti.integrator.application.resource;

import br.org.pti.integrator.domain.entities.contabilidade.SaldoDiaContaContabil;
import br.org.pti.integrator.domain.entities.contabilidade.SaldoIntervaloContas;
import br.org.pti.integrator.domain.entities.contabilidade.SaldoPeriodoContaContabil;
import br.org.pti.integrator.domain.services.SaldoDiaContaContabilService;
import br.org.pti.integrator.infrastructure.utils.components.security.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Willian Brecher
 * @version 1.0.0
 * @since 1.0.0, 26/12/2017
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/saldoscontacontabeis")
public class SaldoDiaContaContabilResource {

    private final SaldoDiaContaContabilService saldoDiaContaContabilService;

    /**
     * Busca o saldo da conta na data informada
     *
     * @param conta numero da conta
     * @param data  data do saldo
     * @return saldo da conta
     */
    @GetMapping("/{conta}/{data}/porData")
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    public SaldoDiaContaContabil buscaPorData(@PathVariable("conta") final String conta,
                                              @PathVariable("data") final LocalDate data) {
        return this.saldoDiaContaContabilService.buscarSaldoDia(conta, data);
    }

    /**
     * Busca o saldo total da conta no periodo informado
     *
     * @param conta      numero da conta
     * @param dataInicio data de inicio de busca do periodo
     * @param dataFim    data final de busca do periodo
     * @return saldo da conta
     */
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    @GetMapping("/{conta}/{datainicio}/{datafim}/porPeriodo")
    public SaldoPeriodoContaContabil buscaPorPeriodo(final @PathVariable("conta") String conta,
                                                     final @PathVariable("datainicio") LocalDate dataInicio,
                                                     final @PathVariable("datafim") LocalDate dataFim) {
        return this.saldoDiaContaContabilService.buscarSaldoPeriodo(conta, dataInicio, dataFim);
    }

    /**
     * Busca o saldo anterior data da conta informada
     *
     * @param conta numero da conta
     * @param data  data do saldo
     * @return saldo da conta
     */
    @GetMapping("/{conta}/{data}/saldoAnterior")
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    public SaldoDiaContaContabil buscaSaldoAnterior(@PathVariable("conta") final String conta, @PathVariable("data") final LocalDate data) {
        return this.saldoDiaContaContabilService.buscarSaldoAnterior(conta, data);
    }

    /**
     * Busca os saldos das contas do intervalo na data informada
     *
     * @param contaInicio numero da conta inicio
     * @param contaFim    numero da conta fim
     * @param dataInicio  data de inicio da busca
     * @param datafim     data de fim da busca
     * @return lista com saldos
     */
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    @GetMapping("/{containicio}/{contafim}/{datainicio}/{datafim}/porIntervaloContas")
    public List<SaldoIntervaloContas> listarPorData(final @PathVariable("containicio") String contaInicio,
                                                    final @PathVariable("contafim") String contaFim,
                                                    final @PathVariable("datainicio") LocalDate dataInicio,
                                                    final @PathVariable("datafim") LocalDate datafim) {
        return this.saldoDiaContaContabilService.buscarSaldoIntervalo(contaInicio, contaFim, dataInicio, datafim);
    }
}
