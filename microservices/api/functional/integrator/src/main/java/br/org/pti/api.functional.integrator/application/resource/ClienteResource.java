package br.org.pti.api.functional.integrator.application.resource;

import br.org.pti.api.functional.integrator.domain.entities.compras.Cliente;
import br.org.pti.api.functional.integrator.domain.entities.dto.ClienteDTO;
import br.org.pti.api.functional.integrator.domain.services.ClienteService;
import br.org.pti.api.functional.integrator.infrastructure.utils.components.security.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Servico REST para consulta/inclusao de clientes no protheus
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 29/09/2017
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/clientes")
public class ClienteResource {

    private final ClienteService clienteService;
    
    /**
     * Cria um novo cliente na base de dados do ERP
     * 
     * @param cliente os dados do cliente a ser criado
     * @return o codigo do cliente criado
     */
    @PostMapping
    @PreAuthorize(Rule.COMPRAS_WRITE)
    public Cliente criar(@Valid ClienteDTO cliente) {

        return this.clienteService.salvar(cliente);

    }
    
    /**
     * Atualiza um cliente existente
     * 
     * @param cliente o cliente a ser atualizado
     */
    @PutMapping
    @PreAuthorize(Rule.COMPRAS_WRITE)
    public void atualizar(@Valid ClienteDTO cliente) {
        this.clienteService.atualizar(cliente);
    }
    
    /**
     * Consulta um cliente por codigo e loja
     * 
     * @param codigo o codigo do cliente
     * @param loja a loja do cliente
     * @return o cliente
     */
    @GetMapping("/{codigo}/{loja}")
    @PreAuthorize(Rule.COMPRAS_READ)
    public Cliente buscarPorCodigoLoja(@PathVariable("codigo") final String codigo, @PathVariable("loja") final String loja) {
        return br.org.pti.api.functional.integrator.infrastructure.utils.components.RestPreconditions.checkFound(this.clienteService.buscarPorCodigoELoja(codigo, loja));
    }
    
    /**
     * Consulta um cliente por email
     * 
     * @param email o email do cliente
     * @return o cliente localizado
     */
    @GetMapping("/{email}/porEmail")
    @PreAuthorize(Rule.COMPRAS_READ)
    public Cliente buscarPorEmail(@PathVariable("email") final String email) {
        return br.org.pti.api.functional.integrator.infrastructure.utils.components.RestPreconditions.checkFound(this.clienteService.buscarPorEmail(email));
    }
    
    /**
     * Consulta um cliente por documento
     * 
     * @param documento o documento do cliente
     * @return o cliente localizado
     */
    @PreAuthorize(Rule.COMPRAS_READ)
    @GetMapping("/{documento}/porDocumento")
    public Cliente buscarPorDocumento(@PathVariable("documento") final String documento) {
        return br.org.pti.api.functional.integrator.infrastructure.utils.components.RestPreconditions.checkFound(this.clienteService.buscarPorDocumento(documento));
    }
}
