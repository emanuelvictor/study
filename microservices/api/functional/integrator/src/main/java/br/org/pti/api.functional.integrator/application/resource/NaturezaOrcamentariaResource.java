package br.org.pti.api.functional.integrator.application.resource;

import br.org.pti.api.functional.integrator.domain.entities.contabilidade.NaturezaOrcamentaria;
import br.org.pti.api.functional.integrator.domain.repositories.NaturezaOrcamentariaRepository;
import br.org.pti.api.functional.integrator.infrastructure.utils.components.RestPreconditions;
import br.org.pti.api.functional.integrator.infrastructure.utils.components.security.Rule;
import br.org.pti.api.functional.integrator.infrastructure.utils.exceptions.ValidationException;
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
 * @since 1.0.0, 20/11/2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/naturezasorcamentarias")
public class NaturezaOrcamentariaResource {

    private final NaturezaOrcamentariaRepository naturezaOrcamentariaRepository;

    /**
     * Busca uma lista de todas as naturezas orcamentarias nao deletados
     *
     * @return lista de naturezas orcamentarias
     */
    @GetMapping
    @PreAuthorize(Rule.COMPRAS_READ)
    public Page<NaturezaOrcamentaria> listarTodos(final Pageable pageable) {
        return this.naturezaOrcamentariaRepository.findAllNotDeleted(pageable);
    }

    /**
     * Busca a natureza orcamentaria pelo codigo informado
     *
     * @param codigo
     * @return
     */
    @GetMapping("{codigo}")
    @PreAuthorize(Rule.COMPRAS_READ)
    public NaturezaOrcamentaria buscarPorCodigo(@PathVariable("codigo") final String codigo) {
        RestPreconditions.validateFilter(codigo);
        return RestPreconditions.checkFound(this.naturezaOrcamentariaRepository.findByCodigo(codigo));
    }

    /**
     * Busca naturezas orcamentarias contendo o codigo informado
     *
     * @param codigo
     * @return
     */
    @PreAuthorize(Rule.COMPRAS_READ)
    @GetMapping("{codigo}/porCodigoContendo")
    public List<NaturezaOrcamentaria> buscarPorCodigoContendo(@PathVariable("codigo") final String codigo) {
        RestPreconditions.validateFilter(codigo);
        if (codigo.length() < 3) {
            throw new ValidationException("natureza-orcamentaria.tamanho-invalido");
        }
        return RestPreconditions.checkFound(this.naturezaOrcamentariaRepository.findByCodigoLike(codigo));
    }

    /**
     * Busca naturezas orcamentarias contendo a descricao informado
     *
     * @param descricao
     * @return
     */
    @PreAuthorize(Rule.COMPRAS_READ)
    @GetMapping("{descricao}/porDescricaoContendo")
    public List<NaturezaOrcamentaria> buscarPorDescricaoContendo(final @PathVariable("descricao") String descricao) {
        RestPreconditions.validateFilter(descricao);
        if (descricao.length() < 3) {
            throw new ValidationException("natureza-orcamentaria.tamanho-invalido");
        }
        return RestPreconditions.checkFound(
                this.naturezaOrcamentariaRepository.findByDescricaoLike(descricao));
    }
}
