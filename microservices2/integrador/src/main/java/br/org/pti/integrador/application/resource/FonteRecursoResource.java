package br.org.pti.integrador.application.resource;

import br.org.pti.integrador.domain.entities.contabilidade.FonteDeRecurso;
import br.org.pti.integrador.domain.repositories.FonteRecursoRepository;
import br.org.pti.integrador.infrastructure.utils.components.RestPreconditions;
import br.org.pti.integrador.infrastructure.utils.components.security.Rule;
import br.org.pti.integrador.infrastructure.utils.exceptions.ValidationException;
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
 * @since 1.0.0, 14/01/2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/fontesrecurso")
public class FonteRecursoResource {

    private final FonteRecursoRepository fonteRecursoRepository;

    /**
     * Busca uma lista de todas as fontes de recurso nao deletados
     *
     * @return lista de fontes de recurso
     */
    @GetMapping
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    public Page<FonteDeRecurso> listarTodos(final Pageable pageable) {
        return this.fonteRecursoRepository.findAllNotDeleted(pageable);
    }

    /**
     * Busca a fonte de recurso pelo codigo informado
     *
     * @param codigo
     * @return
     */
    @GetMapping("{codigo}")
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    public FonteDeRecurso buscarPorCodigo(@PathVariable("codigo") final String codigo) {
        RestPreconditions.validateFilter(codigo);
        return RestPreconditions.checkFound(this.fonteRecursoRepository.findByCodigo(codigo));
    }

    /**
     * Busca fontes de recurso contendo o codigo informado
     *
     * @param codigo
     * @return
     */
    @GetMapping("{codigo}/porCodigoContendo")
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    public List<FonteDeRecurso> buscarPorCodigoContendo(final @PathVariable("codigo") String codigo) {
        RestPreconditions.validateFilter(codigo);
        if (codigo.length() < 3) {
            throw new ValidationException("fonte-recurso.tamanho-invalido");
        }

        return RestPreconditions.checkFound(this.fonteRecursoRepository.findByCodigoLike(codigo));
    }

    /**
     * Busca fontes de recurso contendo a descricao informado
     *
     * @param descricao
     * @return
     */
    @GetMapping("{descricao}/porDescricaoContendo")
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    public List<FonteDeRecurso> buscarPorDescricaoContendo(@PathVariable("descricao") final String descricao) {
        RestPreconditions.validateFilter(descricao);
        if (descricao.length() < 3) {
            throw new ValidationException("fonte-recurso.tamanho-invalido");
        }
        return RestPreconditions.checkFound(
                this.fonteRecursoRepository.findByDescricaoLike(descricao));
    }
}
