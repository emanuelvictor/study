package br.org.pti.api.functional.integrator.application.resource;

import br.org.pti.api.functional.integrator.domain.entities.rh.Departamento;
import br.org.pti.api.functional.integrator.domain.repositories.DepartamentoRepository;
import br.org.pti.api.functional.integrator.infrastructure.utils.components.RestPreconditions;
import br.org.pti.api.functional.integrator.infrastructure.utils.components.security.Rule;
import br.org.pti.api.functional.integrator.infrastructure.utils.protheus.ProtheusUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.4.0, 21/08/2018
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/departamentos")
public class DepartamentoResource {

    private final DepartamentoRepository departamentoRepository;

    /**
     * 
     * @return 
     */
    @GetMapping
    @PreAuthorize(Rule.RH_READ)
    public List<Departamento> listarTodos() {
        return this.departamentoRepository.findAllNotDeleted();
    }
    
    /**
     * 
     * @param codigo
     * @return 
     */
    @GetMapping("{codigo}")
    @PreAuthorize(Rule.RH_READ)
    public Departamento buscarPorCodigo(@PathVariable("codigo") String codigo) {

        RestPreconditions.validateFilter(codigo);

        final String qb_depto = ProtheusUtils.preencheComZeros(codigo, 9);
        
        return RestPreconditions.checkFound(this.departamentoRepository.findByCodigo(qb_depto));
    }
}
