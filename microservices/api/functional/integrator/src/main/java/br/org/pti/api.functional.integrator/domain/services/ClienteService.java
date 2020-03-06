package br.org.pti.api.functional.integrator.domain.services;

import br.org.pti.api.functional.integrator.application.clients.ClientesClient;
import br.org.pti.api.functional.integrator.domain.entities.compras.Cliente;
import br.org.pti.api.functional.integrator.domain.entities.dto.ClienteDTO;
import br.org.pti.api.functional.integrator.domain.repositories.ClienteRepository;
import br.org.pti.api.functional.integrator.infrastructure.utils.exceptions.ResourceNotFoundException;
import br.org.pti.api.functional.integrator.infrastructure.utils.exceptions.ServiceException;
import br.org.pti.api.functional.integrator.infrastructure.utils.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 29/09/2017
 */
@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClientesClient clientesClient;

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     *
     * @param cliente
     * @return
     */
    public Cliente salvar(ClienteDTO cliente) {

        final ClienteDTO result = this.clientesClient.cadastrarNoProtheus(cliente);

        if (result.obteveSucesso()) {
            return this.clienteRepository.findByEmail(cliente.getEmail());
        } else {
            throw new ServiceException(result.getErrorMessage());
        }
    }

    /**
     *
     * @param cliente
     * @return
     */
    public Cliente atualizar(ClienteDTO cliente) {

        // verificamos se o codigo foi informado
        if (!cliente.temCodigoELoja()) {
            throw new ValidationException(
                    "cliente.codigo-invalido", cliente.getCodigo());
        }
        
        if (!this.existeClienteCom(cliente.getCodigo(), cliente.getLoja())) {
            throw new ResourceNotFoundException("cliente.nao-localizado-edicao", cliente.getCodigo(), cliente.getLoja());
        }
        
        final ClienteDTO result = this.clientesClient.atualizarNoProtheus(cliente);
        
        if (result.obteveSucesso()) {
            return this.clienteRepository.findByCodigoAndLoja(cliente.getCodigo(), cliente.getLoja());
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
    public boolean existeClienteCom(String codigo, String loja) {
        long registros = this.clienteRepository.countByCodigoAndLoja(codigo, loja);
        return registros != 0;
    }
    
    /**
     *
     * @param codigo
     * @param loja
     * @return
     */
    @Transactional(readOnly = true)
    public Cliente buscarPorCodigoELoja(String codigo, String loja) {
        return this.clienteRepository.findByCodigoAndLoja(codigo, loja);
    }

    /**
     *
     * @param email
     * @return
     */
    @Transactional(readOnly = true)
    public Cliente buscarPorEmail(String email) {
        return this.clienteRepository.findByEmail(email);
    }

    /**
     *
     * @param documento
     * @return
     */
    @Transactional(readOnly = true)
    public Cliente buscarPorDocumento(String documento) {
        return this.clienteRepository.findByDocumento(documento);
    }
}
