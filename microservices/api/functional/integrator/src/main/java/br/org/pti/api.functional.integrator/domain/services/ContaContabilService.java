package br.org.pti.api.functional.integrator.domain.services;

import br.org.pti.api.functional.integrator.domain.entities.contabilidade.ContaContabil;
import br.org.pti.api.functional.integrator.domain.repositories.ContaContabilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servico responsavel por agrupar todos os metodos referentes a contas
 * contabeis
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 26/12/2017
 */
@Service
@Transactional
public class ContaContabilService {

    @Autowired
    private ContaContabilRepository contaContabilRepository;

    /**
     * Metodo para busca de todas as contas ativas
     * 
     * @param pageable objeto utilizado para paginacao do resultado
     * @return lista paginada das contas
     */
    @Transactional(readOnly = true)
    public Page<ContaContabil> listarTodos(final Pageable pageable){
        return this.contaContabilRepository.findAllNotDeleted(pageable);
    }
    
    /**
     * Metodo utilizado para buscar uma conta contabil pelo numero informado
     *
     * @param conta numero da conta
     * @return ContaContabil
     */
    @Transactional(readOnly = true)
    public ContaContabil buscaContaPorNumero(String conta) {
        return this.contaContabilRepository.findByNumero(conta);
    }

}
