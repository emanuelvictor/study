package br.org.pti.integrator.application.resource;

import br.org.pti.integrator.domain.entities.contabilidade.*;
import br.org.pti.integrator.domain.services.ContaContabilService;
import br.org.pti.integrator.domain.services.LancamentoContabilService;
import br.org.pti.integrator.infrastructure.utils.components.security.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Willian Brecher
 * @version 1.0.0
 * @since 1.0.0, 27/12/2017
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/lancamentoscontabeis")
public class LancamentoContabilResource {

    private final ContaContabilService contaContabilService;

    private final LancamentoContabilService lancamentoContabilService;

    /**
     * Metodo responsavel pelo servico de busca do lancamento contabil por data
     *
     * @param beginDate data de inicio do periodo dos lancamentos contabeis
     * @param endDate   data final do periodo dos lancamentos contabeis
     * @param account   conta do lancamento contabil
     * @return lista de lancamentos contabeis paginada
     */
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    @GetMapping("/{begindate}/{enddate}/{account}/porData")
    public Page<LancamentoContabil> listarPorData(final @PathVariable("begindate") LocalDate beginDate,
                                                  final @PathVariable("enddate") LocalDate endDate,
                                                  final @PathVariable("account") String account,
                                                  final Pageable pageable) {

        final ContaContabil conta = this.contaContabilService.buscaContaPorNumero(account);

        return this.lancamentoContabilService.listarPorData(beginDate, endDate, conta, pageable);
    }

    /**
     * Metodo responsavel pelo servico de busca do lancamento contabil por
     * status
     *
     * @param beginDate data de inicio do periodo dos lancamentos contabeis
     * @param endDate   data final do periodo dos lancamentos contabeis
     * @param account   conta do lancamento contabil
     * @param status    status dos lancamentos contabeis a ser buscado
     * @return lista de lancamentos contabeis paginada
     */
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    @GetMapping("/{begindate}/{enddate}/{account}/{status}/porStatus")
    public Page<LancamentoContabil> listarPorStatus(final @PathVariable("begindate") LocalDate beginDate,
                                                    final @PathVariable("enddate") LocalDate endDate,
                                                    final @PathVariable("account") String account,
                                                    final @PathVariable("status") StatusConciliacao status,
                                                    final Pageable pageable) {

        final ContaContabil conta = this.contaContabilService.buscaContaPorNumero(account);

        return this.lancamentoContabilService.listarPorStatus(beginDate, endDate, conta, status, pageable);
    }

    /**
     * Metodo responsavel pelo servico de busca do lancamento contabil nao
     * conciliado
     *
     * @param beginDate data de inicio do periodo dos lancamentos contabeis
     * @param endDate   data final do periodo dos lancamentos contabeis
     * @param account   conta do lancamento contabil
     * @return lista de lancamentos contabeis paginada
     */
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    @GetMapping("/{begindate}/{enddate}/{account}/porNaoConciliado")
    public Page<LancamentoContabil> listarNaoConciliado(final @PathVariable("begindate") LocalDate beginDate,
                                                        final @PathVariable("enddate") LocalDate endDate,
                                                        final @PathVariable("account") String account,
                                                        final Pageable pageable) {

        ContaContabil conta = this.contaContabilService.buscaContaPorNumero(account);

        return this.lancamentoContabilService.listarNaoConciliado(beginDate, endDate, conta, pageable);
    }

    /**
     * Metodo responsavel pelo servico de alteração dos lancamentos contabeis
     *
     * @param listaLancamentoContabil lista de lancamentos contabeis para
     *                                alteracao
     */
    @PutMapping
    @PreAuthorize(Rule.CONTABILIDADE_WRITE)
    public void atualizaLancamento(@Valid @NotNull(message = "lancamento-contabil.lista-lancamento-contabil-vazia") final List<LancamentoContabil> listaLancamentoContabil) {

        this.lancamentoContabilService.alterar(listaLancamentoContabil);

    }

    /**
     * Metodo responsavel pelo servico de busca do lancamento contabil de
     * acertos e adiantamentos de viagens
     *
     * @param beginDate data de inicio do periodo dos lancamentos contabeis
     * @param endDate   data final do periodo dos lancamentos contabeis
     * @param account   conta do lancamento contabil
     * @return lista de lancamentos contabeis paginada
     */
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    @GetMapping("/{begindate}/{enddate}/{account}/porContasViagens")
    public List<LancamentoViagem> listarLancamentosViagem(final @PathVariable("begindate") LocalDate beginDate,
                                                          final @PathVariable("enddate") LocalDate endDate,
                                                          final @PathVariable("account") String account) {

        final ContaContabil conta = this.contaContabilService.buscaContaPorNumero(account);

        return this.lancamentoContabilService.listarLancamentosViagem(beginDate, endDate, conta);
    }

    /**
     * Metodo responsavel por retornar os lancamentos da conta que nao foram
     * conciliados. Retornando a data, descricao, valor de debito e credito
     *
     * @param beginDate data de inicio do periodo dos lancamentos contabeis
     * @param endDate   data final do periodo dos lancamentos contabeis
     * @param account   conta do lancamento contabil
     * @return lista de lancamentos contabeis nao conciliados
     */
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    @GetMapping("/{begindate}/{enddate}/{account}/porItensNaoConciliados")
    public List<LancamentoItemConciliacao> listarItensNaoConciliacao(final @PathVariable("begindate") LocalDate beginDate,
                                                                     final @PathVariable("enddate") LocalDate endDate,
                                                                     final @PathVariable("account") String account) {

        ContaContabil conta = this.contaContabilService.buscaContaPorNumero(account);

        return this.lancamentoContabilService.listarItemNaoConciliado(beginDate, endDate, conta);
    }
}
