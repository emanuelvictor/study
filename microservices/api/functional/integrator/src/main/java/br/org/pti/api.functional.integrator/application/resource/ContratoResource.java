package br.org.pti.api.functional.integrator.application.resource;

import br.org.pti.api.functional.integrator.domain.entities.oracamento.Contrato;
import br.org.pti.api.functional.integrator.domain.services.ContratoService;
import br.org.pti.api.functional.integrator.infrastructure.utils.components.RestPreconditions;
import br.org.pti.api.functional.integrator.infrastructure.utils.components.security.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 16/01/2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/contratos")
public class ContratoResource {
    
    private final ContratoService contratoService;

    /**
     *
     * @param codigo
     * @return
     */
    @GetMapping("{codigo : (.+)?}")
    @PreAuthorize(Rule.ORCAMENTO_READ)
    public Contrato buscarPorCodigo(@PathVariable("codigo") final String codigo) {
        RestPreconditions.validateFilter(codigo);
        return RestPreconditions.checkFound(this.contratoService.buscaContrato(codigo));
    }

}
