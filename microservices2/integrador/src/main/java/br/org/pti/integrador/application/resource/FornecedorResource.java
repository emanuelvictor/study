package br.org.pti.integrador.application.resource;

import br.org.pti.integrador.domain.entities.compras.Fornecedor;
import br.org.pti.integrador.domain.entities.dto.FornecedorDTO;
import br.org.pti.integrador.domain.services.FornecedorService;
import br.org.pti.integrador.infrastructure.utils.components.RestPreconditions;
import br.org.pti.integrador.infrastructure.utils.components.security.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Willian Brecher
 * @version 1.0.0
 * @since 1.0.0, 30/04/2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/fornecedores")
public class FornecedorResource {

    private final FornecedorService fornecedorService;

    /**
     * @param fornecedor
     * @return
     */
    @PostMapping
    @PreAuthorize(Rule.COMPRAS_WRITE)
    public Fornecedor criar(@Valid FornecedorDTO fornecedor) {
        return this.fornecedorService.salvar(fornecedor);
    }

    /**
     * Atualiza um fornecedor existente
     *
     * @param fornecedor o fornecedor a ser atualizado
     */
    @PutMapping
    @PreAuthorize(Rule.COMPRAS_WRITE)
    public Fornecedor atualizar(@Valid FornecedorDTO fornecedor) {
        return this.fornecedorService.atualizar(fornecedor);
    }

    /**
     * Consulta um fornecedor por codigo e loja
     *
     * @param codigo o codigo do fornecedor
     * @param loja   a loja do fornecedor
     * @return o fornecedor
     */
    @GetMapping("/{codigo}/{loja}")
    @PreAuthorize(Rule.COMPRAS_READ)
    public Fornecedor buscarPorCodigoLoja(@PathVariable("codigo") final String codigo, final @PathVariable("loja") String loja) {
        return RestPreconditions.checkFound(this.fornecedorService.buscarPorCodigoELoja(codigo, loja));
    }

    /**
     * Consulta um fornecedor por email
     *
     * @param email o email do fornecedor
     * @return o fornecedor localizado
     */
    @GetMapping("/{email}/porEmail")
    @PreAuthorize(Rule.COMPRAS_READ)
    public Fornecedor buscarPorEmail(@PathVariable("email") final String email) {
        return RestPreconditions.checkFound(this.fornecedorService.buscarPorEmail(email));
    }

    /**
     * Consulta um fornecedor por documento
     *
     * @param documento o documento do fornecedor
     * @return o fornecedor localizado
     */
    @PreAuthorize(Rule.COMPRAS_READ)
    @GetMapping("/{documento}/porDocumento")
    public Fornecedor buscarPorDocumento(@PathVariable("documento") String documento) {
        return RestPreconditions.checkFound(this.fornecedorService.buscarPorDocumento(documento));
    }
}
