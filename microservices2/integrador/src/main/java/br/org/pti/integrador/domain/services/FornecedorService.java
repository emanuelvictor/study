package br.org.pti.integrador.domain.services;

import br.org.pti.integrador.application.clients.FornecedoresClient;
import br.org.pti.integrador.domain.entities.compras.Fornecedor;
import br.org.pti.integrador.domain.entities.dto.FornecedorDTO;
import br.org.pti.integrador.domain.repositories.FornecedorRepository;
import br.org.pti.integrador.infrastructure.utils.exceptions.ResourceNotFoundException;
import br.org.pti.integrador.infrastructure.utils.exceptions.ServiceException;
import br.org.pti.integrador.infrastructure.utils.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 10/05/2019
 */
@Service
@Transactional
public class FornecedorService {

    @Autowired
    private FornecedoresClient fornecedoresClient;

    @Autowired
    private FornecedorRepository fornecedorRepository;
    
    @Autowired
    private FornecedorValidator fornecedorValidator;

    /**
     *
     * @param fornecedor
     * @return
     */
    public Fornecedor salvar(FornecedorDTO fornecedor) {

        fornecedor = this.fornecedorValidator.validaOpcoes(fornecedor);
        
        final FornecedorDTO result = this.fornecedoresClient.cadastrarNoProtheus(fornecedor);

        if (result.obteveSucesso()) {
            return this.fornecedorRepository.findByEmail(fornecedor.getEmail());
        } else {
            throw new ServiceException(result.getErrorMessage());
        }
    }   
    
    /**
     *
     * @param fornecedor
     * @return
     */
    public Fornecedor atualizar(FornecedorDTO fornecedor) {
        
        fornecedor = this.fornecedorValidator.validaOpcoes(fornecedor);

        // verificamos se o codigo foi informado
        if (!fornecedor.temCodigoELoja()) {
            throw new ValidationException(
                    "fornecedor.codigo-invalido", fornecedor.getCodigo());
        }
        
        if (!this.existeFornecedorCom(fornecedor.getCodigo(), fornecedor.getLoja())) {
            throw new ResourceNotFoundException("fornecedor.nao-localizado-edicao", fornecedor.getCodigo(), fornecedor.getLoja());
        }
        
        final FornecedorDTO result = this.fornecedoresClient.atualizarNoProtheus(fornecedor);
        
        if (result.obteveSucesso()) {
            return this.fornecedorRepository.findByCodigoAndLoja(
                    fornecedor.getCodigo(), fornecedor.getLoja());
        } else {
            throw new ServiceException(result.getErrorMessage());
        }
    }

    /**
     *
     * @param codigo
     * @param loja
     * @return
     */
    @Transactional(readOnly = true)
    public boolean existeFornecedorCom(String codigo, String loja) {
        long registros = this.fornecedorRepository.countByCodigoAndLoja(codigo, loja);
        return registros != 0;
    }    
    
    /**
     *
     * @param codigo
     * @param loja
     * @return
     */
    @Transactional(readOnly = true)
    public Fornecedor buscarPorCodigoELoja(String codigo, String loja) {
        return this.fornecedorRepository.findByCodigoAndLoja(codigo, loja);
    }

    /**
     *
     * @param email
     * @return
     */
    @Transactional(readOnly = true)
    public Fornecedor buscarPorEmail(String email) {
        return this.fornecedorRepository.findByEmail(email);
    }

    /**
     *
     * @param documento
     * @return
     */
    @Transactional(readOnly = true)
    public Fornecedor buscarPorDocumento(String documento) {
        return this.fornecedorRepository.findByDocumento(documento);
    }    
    
}
