package br.org.pti.compras.application.resource;

import br.org.pti.compras.domain.entity.fornecedor.AtividadeEconomica;
import br.org.pti.compras.domain.repository.IAtividadeEconomicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static br.org.pti.compras.application.resource.Roles.ATIVIDADE_ECONOMICA_MAPPING_RESOURCE;

@RestController
@RequiredArgsConstructor
@RequestMapping({ATIVIDADE_ECONOMICA_MAPPING_RESOURCE, "/sistema/" + ATIVIDADE_ECONOMICA_MAPPING_RESOURCE})
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
