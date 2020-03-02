package br.org.pti.integrator.application.resource;

import br.org.pti.integrator.domain.entities.compras.Municipio;
import br.org.pti.integrator.domain.repositories.MunicipioRepository;
import br.org.pti.integrator.infrastructure.utils.components.RestPreconditions;
import br.org.pti.integrator.infrastructure.utils.components.security.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("api/municipios")
public class MunicipioResource {

    private final MunicipioRepository municipioRepository;

    /**
     * @param pageable
     * @return
     */
    @GetMapping
    @PreAuthorize(Rule.COMPRAS_READ)
    public Page<Municipio> listarTodos(final Pageable pageable) {
        return this.municipioRepository.findAllNotDeleted(pageable);
    }

    /**
     * @param uf
     * @return
     */
    @GetMapping("/{uf}/porEstado")
    @PreAuthorize(Rule.COMPRAS_READ)
    public List<Municipio> buscarPorCodigoLoja(@PathVariable("uf") String uf) {
        return RestPreconditions.checkFound(this.municipioRepository.findByEstado(uf));
    }
}
