package br.org.pti.integrator.application.resource;

import br.org.pti.integrator.domain.entities.oracamento.Atividade;
import br.org.pti.integrator.domain.repositories.AtividadeRepository;
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

/**
 * @author Willian Brecher
 * @version 1.0.0
 * @since 1.0.0, 14/01/2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/atividades")
public class AtividadeResource {

    private final AtividadeRepository atividadeRepository;

    /**
     * @param codigo
     * @return
     */
    @GetMapping("{codigo}")
    @PreAuthorize(Rule.ORCAMENTO_READ)
    public Atividade buscarPorCodigo(final @PathVariable("codigo") String codigo) {
        RestPreconditions.validateFilter(codigo);
        return RestPreconditions.checkFound(this.atividadeRepository.findByCodigo(codigo));
    }

    /**
     * @param pageable
     * @return
     */
    @GetMapping
    @PreAuthorize(Rule.ORCAMENTO_READ)
    public Page<Atividade> listarTodos(final Pageable pageable) {
        return this.atividadeRepository.findAllNotDeleted(pageable);
    }

    /**
     * @param descricao
     * @param pageable
     * @return
     */
    @PreAuthorize(Rule.ORCAMENTO_READ)
    @GetMapping("{descricao}/porDescricao")
    public Page<Atividade> buscarPorDescricao(final @PathVariable("descricao") String descricao, final Pageable pageable) {
        return this.atividadeRepository.findByDescricaoContaining(descricao, pageable);
    }

}
