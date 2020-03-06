package br.org.pti.api.functional.integrator.domain.services;

import br.org.pti.api.functional.integrator.domain.entities.contabilidade.*;
import br.org.pti.api.functional.integrator.domain.entities.dto.LancamentoViagemValorDTO;
import br.org.pti.api.functional.integrator.domain.repositories.LancamentoContabilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 27/12/2017
 */
@Service
@Transactional
public class LancamentoContabilService {

    @Autowired
    private LancamentoContabilRepository lancamentoContabilRepository;

    /**
     * Metodo para busca de lista paginada de lancamentos contabeis por data
     * utilizando os paramentos informados para busca
     *
     * @param dataInicial data de inicio do periodo do lancamento contabil
     * @param dataFinal data final do periodo do lancamento contabil
     * @param contaContabil conta do lancamento contabil
     * @param pageable objeto para busca paginada
     * @return lista paginada de lancamentos contabeis
     */
    @Transactional(readOnly = true)
    public Page<LancamentoContabil> listarPorData(LocalDate dataInicial,
                                                  LocalDate dataFinal, ContaContabil contaContabil,
                                                  final Pageable pageable) {

        return this.lancamentoContabilRepository.findByDate(
                dataInicial, dataFinal, contaContabil, pageable);
    }

    /**
     * Metodo para busca de lista paginada de lancamentos contabeis por status
     * utilizando os paramentos informados para busca
     *
     * @param dataInicial data de inicio do periodo do lancamento contabil
     * @param dataFinal data final do periodo do lancamento contabil
     * @param contaContabil conta do lancamento contabil
     * @param status status do lancamento contabil
     * @param pageable objeto para busca paginada
     * @return lista paginada de lancamentos contabeis
     */
    @Transactional(readOnly = true)
    public Page<LancamentoContabil> listarPorStatus(LocalDate dataInicial,
                                                    LocalDate dataFinal, ContaContabil contaContabil, StatusConciliacao status,
                                                    final Pageable pageable) {

        return this.lancamentoContabilRepository.findByStatus(
                dataInicial, dataFinal, contaContabil, status,
                pageable);
    }

    /**
     * Metodo para busca de lista paginada de lancamentos contabeis nao
     * conciliados utilizando os paramentos informados para busca
     *
     * @param dataInicial data de inicio do periodo do lancamento contabil
     * @param dataFinal data final do periodo do lancamento contabil
     * @param contaContabil conta do lancamento contabil
     * @param pageable objeto para busca paginada
     * @return lista paginada de lancamentos contabeis
     */
    @Transactional(readOnly = true)
    public Page<LancamentoContabil> listarNaoConciliado(LocalDate dataInicial,
                                                        LocalDate dataFinal, ContaContabil contaContabil, final Pageable pageable) {

        return this.lancamentoContabilRepository.findByNotConciliatedPage(
                dataInicial, dataFinal, contaContabil, 
                StatusConciliacao.CONCILIADO, pageable);
    }

    /**
     * Metodo para realizar a alteração dos lancamentos contabeis
     *
     * @param listaLancamentoContabil lista de lancamento contabeis a ser
     * alterado
     */
    public void alterar(List<LancamentoContabil> listaLancamentoContabil) {
        listaLancamentoContabil.forEach(lancamentoContabil -> {

            LancamentoContabil lancamento = this.lancamentoContabilRepository.save(lancamentoContabil);

        });
    }

    /**
     * Metodo para fazer o calculo dos lancamentos de acertos e adiantamentos de
     * viagens
     *
     * @param dataInicial data de inicio do periodo do lancamento contabil
     * @param dataFinal data final do periodo do lancamento contabil
     * @param contaContabil conta do lancamento contabil
     * @return lista com os saldos dos acertos e adiantamentos de viagens
     */
    public List<LancamentoViagem> listarLancamentosViagem(LocalDate dataInicial,
                                                          LocalDate dataFinal, ContaContabil contaContabil) {

        List<String> listaNumerosViagem = this.lancamentoContabilRepository
                .findHistoricTravel(
                        contaContabil.getConta(),
                        dataInicial.format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                        dataFinal.format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        List<LancamentoViagem> listaLancamentosViagem = new ArrayList<>();

        listaNumerosViagem.stream().forEach(numero -> {

            final List<LancamentoViagemValorDTO> valores = this.lancamentoContabilRepository
                    .findTravelValues(contaContabil, numero, dataFinal);

            final BigDecimal creditos = valores.stream()
                    .filter(valor -> "C".equals(valor.getType()))
                    .map(LancamentoViagemValorDTO::getValue)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            final BigDecimal debitos = valores.stream()
                    .filter(valor -> "D".equals(valor.getType()))
                    .map(LancamentoViagemValorDTO::getValue)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            final BigDecimal saldo = creditos.subtract(debitos);

            if (saldo.compareTo(BigDecimal.ZERO) != 0) {
                LancamentoViagem lancamentoViagem = new LancamentoViagem();
                lancamentoViagem.setNumber(numero);
                lancamentoViagem.setBalance(saldo);

                listaLancamentosViagem.add(lancamentoViagem);
            }
        });

        return listaLancamentosViagem;
    }

    /**
     * Busca os itens de lancamento para a conciliacao
     * 
     * @param dataInicial data inicial da busca
     * @param dataFinal data final da busca
     * @param contaContabil conta contabil para a busca
     * @return lista de itens da conciliacao
     */
    @Transactional(readOnly = true)
    public List<LancamentoItemConciliacao> listarItemNaoConciliado(
            LocalDate dataInicial, LocalDate dataFinal, ContaContabil contaContabil) {

        List<LancamentoItemConciliacao> listaItensConciliacao = new ArrayList<>();

        List<LancamentoContabil> listaLancamentos
                = this.lancamentoContabilRepository.findByNotConciliatedList(
                        dataInicial, dataFinal, contaContabil, StatusConciliacao.CONCILIADO);

        listaLancamentos.forEach(item -> {

            LancamentoItemConciliacao itemConciliacao = 
                    new LancamentoItemConciliacao();
            itemConciliacao.setDate(item.getDataLancamento());
            itemConciliacao.setHistoric(item.getHistorico());

            switch (item.getTipoLancamento()) {
                case DEBITO:
                    itemConciliacao.setDebitValue(item.getValor());
                    itemConciliacao.setCreditValue(BigDecimal.ZERO);
                    break;
                case CREDITO:
                    itemConciliacao.setDebitValue(BigDecimal.ZERO);
                    itemConciliacao.setCreditValue(item.getValor());
                    break;
                case PARTIDA_DOBRADA:
                    if (item.getContaCredito().getConta()
                            .equals(contaContabil.getConta())) {
                        itemConciliacao.setCreditValue(item.getValor());
                        itemConciliacao.setDebitValue(BigDecimal.ZERO);
                    } else if (item.getContaDebito().getConta()
                            .equals(contaContabil.getConta())) {
                        itemConciliacao.setDebitValue(item.getValor());
                        itemConciliacao.setCreditValue(BigDecimal.ZERO);
                    }
                    break;
                default:
                    break;
            }

            listaItensConciliacao.add(itemConciliacao);
        });

        return listaItensConciliacao;
    }
}
