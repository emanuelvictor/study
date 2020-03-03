package br.org.pti.integrator.application.resource;

import br.org.pti.integrator.domain.entities.compras.Pais;
import br.org.pti.integrator.domain.repositories.PaisRepository;
import br.org.pti.integrator.infrastructure.utils.components.security.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Willian Brecher
 * @version 1.0.0
 * @since 1.0.0, 14/05/2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/paises")
public class PaisResource {

    private final PaisRepository paisRepository;

    @GetMapping
    @PreAuthorize(Rule.COMPRAS_READ)
    public List<Pais> buscarPorTodos() {
        return this.paisRepository.findAllNotDeletedOrdered();
    }
}
