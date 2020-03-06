package br.org.pti.api.functional.integrator.domain.services;

import br.org.pti.api.functional.integrator.domain.entities.contabilidade.ItemPreNota;
import br.org.pti.api.functional.integrator.domain.entities.contabilidade.PreNota;
import br.org.pti.api.functional.integrator.domain.repositories.ItemPreNotaRepository;
import br.org.pti.api.functional.integrator.domain.repositories.PreNotaRepository;
import br.org.pti.api.functional.integrator.infrastructure.utils.components.RandomCodeGenerator;
import br.org.pti.api.functional.integrator.infrastructure.utils.components.RestPreconditions;
import br.org.pti.api.functional.integrator.infrastructure.utils.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servico responsavel por agrupar todos os metodos referentes a notas fiscais
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 24/10/2017
 */
@Service
@Transactional
public class PreNotaService {

    @Autowired
    private PreNotaValidator notaFiscalValidator;
    @Autowired
    private PreNotaRepository notaFiscalRepository;
    @Autowired
    private ItemPreNotaRepository itemNotaFiscalRepository;

    /**
     * Metodo responsavel por realizar a validacao e salvar a nota fiscal na
     * base de dados do ERP
     *
     * @param notasFiscais
     * @return NotaFiscal
     */
    public long salvar(List<PreNota> notasFiscais) {

        // gera o lote
        final long lote = RandomCodeGenerator.numericCode(6);

        notasFiscais.forEach(nota -> {

            // pega a nota fiscal com os dados validados
            final PreNota notaValidada = this.notaFiscalValidator.validaNota(nota);
            notaValidada.setLote(lote);

            // pega a lista de itens antes de salvar a nota
            final List<ItemPreNota> itens = notaValidada.getItensPreNota();

            // salva a nota
            final PreNota notaSalva = this.notaFiscalRepository.save(notaValidada);

            // salva os itens
            itens.forEach(item -> {
                item.setItemNota(String.format("%02d", itens.indexOf(item) + 1));
                item.setNotaFiscal(notaSalva);
                this.itemNotaFiscalRepository.save(item);
            });
        });

        return lote;
    }

    /**
     * Metodo utilizado para buscar uma nota fiscal referente ao numero infomado
     *
     * @param numero
     * @return NotaFiscal
     */
    @Transactional(readOnly = true)
    public PreNota buscarNotaPorNumero(String numero) {

        return this.notaFiscalRepository.findByNumero(numero);
    }

    /**
     * Metodo utilizado para buscar uma nota fiscal referente ao lote infomado
     * 
     * @param lote
     * @return 
     */
    @Transactional(readOnly = true)
    public List<PreNota> buscarPorLote(long lote) {
        return this.notaFiscalRepository.findByLote(lote);
    }

    /**
     * Realiza a delecao dos registros no banco do ERP
     *
     * @param lote o lote que queremos deletar
     */
    public void deletar(long lote) {

        final List<PreNota> listaNotaFiscal = RestPreconditions.checkFound(this.notaFiscalRepository.findByLote(lote));

        // checamos se alguma das notas ja foi integrada antes da 
        // exclusao acontecer, se sim, devolve com uma exception
        final long integrados = listaNotaFiscal.stream()
                .filter(PreNota::isIntegrado)
                .count();

        if (integrados != 0) {
            throw new ValidationException("nota-fiscal.lote-possui-integracao", integrados, lote);
        }

        // se chegou aqui, deleta o lote todo
        listaNotaFiscal.forEach(nota -> {

            final List<ItemPreNota> itensNotaFiscal = this.itemNotaFiscalRepository.findByDocumento(nota.getDocumento());

            itensNotaFiscal.forEach(item -> {
                this.itemNotaFiscalRepository.physicallyDelete(item.getRecno());
            });
            
            this.notaFiscalRepository.physicallyDelete(nota.getRecno());            
        });
    }

    /**
     * Metodo utilizado para buscar os itens da nota fiscal referente ao 
     * numero infomado.
     * 
     * @param documento
     * @return objeto com dados da nota fiscal
     */
    @Transactional(readOnly = true)
    public List<ItemPreNota> buscarItensPorDocumento(String documento) {
        return this.itemNotaFiscalRepository.findByDocumento(documento);
    }   
}
