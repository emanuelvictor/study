package br.org.pti.api.functional.integrator.application.resource;

import br.org.pti.api.functional.integrator.domain.entities.ativofixo.Localizacao;
import br.org.pti.api.functional.integrator.domain.repositories.LocalizacaoRepository;
import br.org.pti.api.functional.integrator.infrastructure.utils.components.RestPreconditions;
import br.org.pti.api.functional.integrator.infrastructure.utils.components.security.Rule;
import br.org.pti.api.functional.integrator.infrastructure.utils.exceptions.ValidationException;
import br.org.pti.api.functional.integrator.infrastructure.utils.protheus.ProtheusUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.2.0, 20/09/2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/localizacoes")
public class LocalizacaoResource {

    private final LocalizacaoRepository localizacaoRepository;

    /**
     * @param pageable Pageable
     * @return Page<Localizacao>
     */
    @GetMapping
    @PreAuthorize(Rule.ATIVO_FIXO_READ)
    public Page<Localizacao> listarTodos(final Pageable pageable) {
        return this.localizacaoRepository.findAllNotDeleted(pageable);
    }

    /**
     * @param codigo String
     * @return Localizacao
     */
    @GetMapping("/{codigo}")
    @PreAuthorize(Rule.ATIVO_FIXO_READ)
    public Localizacao buscarPorCodigo(@PathVariable("codigo") final String codigo) {
        RestPreconditions.validateFilter(codigo);
        return RestPreconditions.checkFound(this.localizacaoRepository.findByCodigo(ProtheusUtils.preencheComZeros(codigo, 6)));
    }

    /**
     * @param descricao String
     * @param pageable  Pageable
     * @return Page<Localizacao>
     */
    @PreAuthorize(Rule.ATIVO_FIXO_READ)
    @GetMapping("{descricao}/porDescricao")
    public Page<Localizacao> buscarPorDescricao(@PathVariable("descricao") final String descricao, final Pageable pageable) {

        if (descricao != null && !descricao.isEmpty()) {
            RestPreconditions.validateFilter(descricao);

            if (descricao.length() < 3) {
                throw new ValidationException("localizacao.tamanho-invalido");
            }
        }

        return RestPreconditions.checkFound(this.localizacaoRepository.findByDescricao(descricao, pageable));
    }
}
