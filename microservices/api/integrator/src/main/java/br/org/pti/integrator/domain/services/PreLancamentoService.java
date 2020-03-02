package br.org.pti.integrator.domain.services;

import br.org.pti.integrator.domain.entities.contabilidade.PreLancamento;
import br.org.pti.integrator.domain.repositories.LancamentoRepository;
import br.org.pti.integrator.infrastructure.utils.components.RandomCodeGenerator;
import br.org.pti.integrator.infrastructure.utils.components.RestPreconditions;
import br.org.pti.integrator.infrastructure.utils.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servico responsavel por agrupar todos os metodos referentes a preLancamentos contabeis
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 15/09/2017
 */
@Service
@Transactional
public class PreLancamentoService {

    @Autowired
    private LancamentoRepository preLancamentoRepository;

    /**
     * Metodo responsavel por realizar a validacao e salvar os preLancamentos na 
     * base de dados do ERP
     *
     * @param preLancamentos a lista de pre preLancamentos
     * @return o lote gerado para a lista de preLancamentos
     */
    public long criar(List<PreLancamento> preLancamentos) {

        // gera o lote
        final long lote = RandomCodeGenerator.numericCode(6);

        // grava cada um dos preLancamentos na ZI1
        preLancamentos.forEach(preLancamento -> {

            final String codigo = preLancamento.getCodigo();
            final String sequencia = preLancamento.getSequencia();

            final long countLancamentoPadrao = this.preLancamentoRepository
                    .existeLancamentoPadrao(codigo, sequencia);

            if (countLancamentoPadrao == 1) {
                preLancamento.setLote(lote);
                this.preLancamentoRepository.save(preLancamento);
            } else {
                throw new ValidationException(
                        "pre-lancamento.codigo-sequencia-invalido", codigo, sequencia);
            }
        });

        return lote;
    }

    /**
     * Realiza a delecao dos registros no banco do ERP
     *
     * @param lote o lote que queremos deletar
     */
    public void deletar(long lote) {

        final List<PreLancamento> preLancamentos = RestPreconditions
                .checkFound(this.preLancamentoRepository.findByLote(lote));

        // checamos se algum dos preLancamentos ja foi integrado antes da 
        // exclusao acontecer, se sim, devolve com uma exception
        final long integrados = preLancamentos.stream()
                .filter(PreLancamento::isIntegrado)
                .count();
        
        if (integrados != 0) {
            throw new ValidationException(
                    "pre-lancamento.lote-possui-integracao", integrados, lote);
        }
        
        // se chegou aqui, deleta o lote todo
        this.preLancamentoRepository.deleteAll(preLancamentos);
    }

    /**
     * Metodo que lista todos os pre-preLancamentos de um lote
     *
     * @param lote o lote que queremos os pre-preLancamentos
     * @return a lista de pre-preLancamentos do lote
     */
    @Transactional(readOnly = true)
    public List<PreLancamento> listarPorLote(long lote) {
        return RestPreconditions.checkFound(this.preLancamentoRepository.findByLote(lote));
    }
}
