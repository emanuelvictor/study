package br.org.pti.integrador.domain.services;

import br.org.pti.integrador.domain.entities.contabilidade.ContaContabil;
import br.org.pti.integrador.domain.entities.contabilidade.SaldoDiaContaContabil;
import br.org.pti.integrador.domain.entities.contabilidade.SaldoIntervaloContas;
import br.org.pti.integrador.domain.entities.contabilidade.SaldoPeriodoContaContabil;
import br.org.pti.integrador.domain.repositories.ContaContabilRepository;
import br.org.pti.integrador.domain.repositories.SaldoDiaContaContabilRepository;
import br.org.pti.integrador.infrastructure.utils.components.RestPreconditions;
import br.org.pti.integrador.infrastructure.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
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
 * @since 1.0.0, 02/01/2018
 */
@Service
@Transactional
public class SaldoDiaContaContabilService {

    @Autowired
    private SaldoDiaContaContabilRepository saldoDiaContaContabilRepository;
    @Autowired
    private ContaContabilRepository contaContabilRepository;

    /**
     * Metodo responsavel por retornar a pesquisa realizada do saldo pelo dia
     * informado
     *
     * @param numero numero da conta
     * @param data data do saldo solicitado
     * @return saldo da conta
     */
    @Transactional(readOnly = true)
    public SaldoDiaContaContabil buscarSaldoDia(String numero, LocalDate data) {

        ContaContabil conta = this.contaContabilRepository.findByNumero(numero);

        SaldoDiaContaContabil saldo = RestPreconditions.checkFound(
                this.saldoDiaContaContabilRepository.findByDate(conta, data));

        return saldo;
    }

    /**
     * Metodo responsavel por retornar a pesquisa realizada do saldo pelo
     * periodo informado
     *
     * @param numero numero da conta
     * @param dataInicio data de inicio do periodo necessario para busca o saldo
     * @param dataFim data final do periodo necessario para busca o saldo
     * @return saldo da conta
     */
    @Transactional(readOnly = true)
    public SaldoPeriodoContaContabil buscarSaldoPeriodo(String numero, LocalDate dataInicio,
                                                        LocalDate dataFim) {

        BigDecimal credito = new BigDecimal(0);
        BigDecimal debito = new BigDecimal(0);

        ContaContabil conta = this.contaContabilRepository.findByNumero(numero);

        List<SaldoDiaContaContabil> listaSaldo
                = RestPreconditions.checkFound(
                        this.saldoDiaContaContabilRepository.findByPeriod(conta,
                                dataInicio, dataFim));

        credito = listaSaldo.stream()
                .map(SaldoDiaContaContabil::getValorCredito)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        debito = listaSaldo.stream()
                .map(SaldoDiaContaContabil::getValorDebito)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        SaldoPeriodoContaContabil saldoPeriodoContaContabil
                = new SaldoPeriodoContaContabil();

        saldoPeriodoContaContabil.setContaContabil(conta);
        saldoPeriodoContaContabil.setValorCredito(credito);
        saldoPeriodoContaContabil.setValorDebito(debito);

        return saldoPeriodoContaContabil;
    }

    /**
     * Metodo responsavel por retornar a pesquisa realizada do saldo anterior ao
     * dia informado
     *
     * @param numero numero da conta
     * @param data data do saldo solicitado
     * @return saldo da conta
     */
    @Transactional(readOnly = true)
    public SaldoDiaContaContabil buscarSaldoAnterior(String numero, LocalDate data) {
        final List<String> sort = new ArrayList<String>();
        sort.add("dataSaldo");

        ContaContabil conta = this.contaContabilRepository.findByNumero(numero);

        List<SaldoDiaContaContabil> listaSaldo = this.saldoDiaContaContabilRepository
                .findByLastDate(conta, data,
                        PageRequest.of(1, 1,
                                Sort.Direction.fromString("desc"),
                                sort.toArray(new String[0])
                        ));

        if (listaSaldo.size() > 0) {
            return listaSaldo.get(0);
        } else {
            throw new ResourceNotFoundException("webservice.resource-not-found");
        }
    }

    /**
     * Metodo para retornar os saldos das contas no periodo informado 
     * 
     * @param contaInicio numero da conta inicial do intervalo
     * @param contaFim numero da conta final do intervalo
     * @param dataInicio data inicial do intervalo
     * @param dataFim data final do intervalo
     * @return lista de saldo de intervalo de contas
     */
    @Transactional(readOnly = true)
    public List<SaldoIntervaloContas> buscarSaldoIntervalo(String contaInicio,
                                                           String contaFim, LocalDate dataInicio, LocalDate dataFim) {

        ContaContabil beginAccount = this.contaContabilRepository
                .findByNumero(contaInicio);
        ContaContabil endAccount = this.contaContabilRepository
                .findByNumero(contaFim);

        List<Object[]> listaSaldosIntervalo = this.saldoDiaContaContabilRepository.findByInterval(
                beginAccount.getConta().trim(), endAccount.getConta().trim(),
                dataInicio.format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                dataFim.format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        List<SaldoIntervaloContas> listaSaldo = new ArrayList<>();

        listaSaldosIntervalo.forEach(item -> {
            SaldoIntervaloContas saldoIntervaloContas = new SaldoIntervaloContas();
            saldoIntervaloContas.setConta(Array.get(item,0).toString());
            saldoIntervaloContas.setDescricao(Array.get(item,1).toString());
            saldoIntervaloContas.setCreditoAnterior((BigDecimal)Array.get(item,4));
            saldoIntervaloContas.setDebitoAnterior((BigDecimal)Array.get(item,5));
            saldoIntervaloContas.setCreditoPeriodo((BigDecimal)Array.get(item,2));
            saldoIntervaloContas.setDebitoPeriodo((BigDecimal)Array.get(item,3));
            
            listaSaldo.add(saldoIntervaloContas);
        });

        return listaSaldo;
    }
}
