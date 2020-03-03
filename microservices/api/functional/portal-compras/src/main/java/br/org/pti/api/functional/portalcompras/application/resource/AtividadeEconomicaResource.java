package br.org.pti.api.functional.portalcompras.application.resource;

import br.org.pti.api.functional.portalcompras.domain.repository.IAtividadeEconomicaRepository;
import br.org.pti.api.functional.portalcompras.domain.entity.fornecedor.AtividadeEconomica;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping({Roles.ATIVIDADE_ECONOMICA_MAPPING_RESOURCE, "/sistema/" + Roles.ATIVIDADE_ECONOMICA_MAPPING_RESOURCE})
public class AtividadeEconomicaResource {


    private final IAtividadeEconomicaRepository atividadeEconomicaRepository;

    @GetMapping
    public Page<AtividadeEconomica> listByFilters(final String defaultFilter, final Pageable pageable) {
        return this.atividadeEconomicaRepository.listByFilters(defaultFilter, pageable);
    }

    @GetMapping("/{code}")
    public Optional<AtividadeEconomica> findById(@PathVariable final String code) {
        return this.atividadeEconomicaRepository.findById(code);
    }

}
