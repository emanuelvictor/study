package br.org.pti.api.functional.integrator.application.resource;

import br.org.pti.api.functional.integrator.infrastructure.utils.components.security.Rule;
import br.org.pti.api.functional.integrator.domain.entities.financeiro.Banco;
import br.org.pti.api.functional.integrator.domain.repositories.BancoRepository;
import br.org.pti.api.functional.integrator.infrastructure.utils.components.RestPreconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Willian Brecher
 * @version 1.0.0
 * @since 1.0.0, 30/04/2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/bancos")
public class BancoResource {

    private final BancoRepository bancoRepository;

    /**
     * @return
     */
    @GetMapping
    @PreAuthorize(Rule.FINANCEIRO_READ)
    public List<Banco> buscarBancos() {
        return RestPreconditions.checkFound(this.bancoRepository.findAll());
    }

    /**
     * @param codigo
     * @return
     */
    @GetMapping("{codigo}/porCodigo")
    @PreAuthorize(Rule.FINANCEIRO_READ)
    public Banco buscarPorCodigo(@PathVariable("codigo") final String codigo) {
        RestPreconditions.validateFilter(codigo);
        return RestPreconditions.checkFound(this.bancoRepository
                .findByCodigo(codigo));
    }

    /**
     * @param descricao
     * @return
     */
    @PreAuthorize(Rule.FINANCEIRO_READ)
    @GetMapping("{descricao}/porDescricao")
    public List<Banco> buscarPorDescricao(@PathVariable("descricao") final String descricao) {
        RestPreconditions.validateFilter(descricao);
        return RestPreconditions.checkFound(this.bancoRepository.findByDescricaoLike(descricao));
    }
}
