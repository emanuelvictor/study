package br.org.pti.integrador.application.resource;

import br.org.pti.integrador.domain.entities.ativofixo.Patrimonio;
import br.org.pti.integrador.domain.repositories.PatrimonioRepository;
import br.org.pti.integrador.infrastructure.utils.components.RestPreconditions;
import br.org.pti.integrador.infrastructure.utils.components.security.Rule;
import br.org.pti.integrador.infrastructure.utils.exceptions.ValidationException;
import br.org.pti.integrador.infrastructure.utils.protheus.ProtheusUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.5.0, 20/09/2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/patrimonios")
public class PatrimonioResource {

    private final PatrimonioRepository patrimonioRepository;

    /**
     * @param plaqueta String
     * @param pageable Pageable
     * @return Page<Patrimonio>
     */
    @GetMapping("/{plaqueta}")
    @PreAuthorize(Rule.ATIVO_FIXO_READ)
    public Page<Patrimonio> buscarPorPlaqueta(@PathVariable("plaqueta") final String plaqueta, final Pageable pageable) {
        RestPreconditions.validateFilter(plaqueta);
        return RestPreconditions.checkFound(this.patrimonioRepository.findByPlaqueta(ProtheusUtils.preencheComZeros(plaqueta, 6), pageable));
    }

    /**
     * @param centroCusto          String
     * @param descricaoLocalizacao String
     * @param descricaoPatrimonio  String
     * @param pageable             Pageable
     * @return Page<Patrimonio>
     */
    @PreAuthorize(Rule.ATIVO_FIXO_READ)
    @GetMapping("/{centroCusto}/porCentroCusto")
    public Page<Patrimonio> listarPorCentroCusto(@PathVariable("centroCusto") final String centroCusto,
                                                 @RequestParam(value = "descricaoLocalizacao", defaultValue = "") final String descricaoLocalizacao,
                                                 @RequestParam(value = "descricaoPatrimonio", defaultValue = "") final String descricaoPatrimonio,
                                                 final Pageable pageable) {
        RestPreconditions.validateFilter(centroCusto);

        if (descricaoPatrimonio != null && !descricaoPatrimonio.isEmpty()) {
            RestPreconditions.validateFilter(descricaoPatrimonio);

            if (descricaoPatrimonio.length() < 3) {
                throw new ValidationException("patrimonio.tamanho-invalido");
            }

            return RestPreconditions.checkFound(this.patrimonioRepository.findByCentroCustoAndPatrimonioLike(centroCusto, descricaoPatrimonio, pageable));

        } else if (descricaoLocalizacao != null && !descricaoLocalizacao.isEmpty()) {
            RestPreconditions.validateFilter(descricaoLocalizacao);

            if (descricaoLocalizacao.length() < 3) {
                throw new ValidationException("localizacao.tamanho-invalido");
            }

            return RestPreconditions.checkFound(this.patrimonioRepository.findByCentroCustoAndLocalizacaoLike(centroCusto, descricaoLocalizacao, pageable));
        } else {
            return RestPreconditions.checkFound(this.patrimonioRepository.findByCentroCusto(centroCusto, pageable));
        }

    }

    /**
     * @param localizacao String
     * @param pageable    Pageable
     * @return Page<Patrimonio>
     */
    @PreAuthorize(Rule.ATIVO_FIXO_READ)
    @GetMapping("/{localizacao}/porLocalizacao")
    public Page<Patrimonio> listarPorLocalizacao(@PathVariable("localizacao") final String localizacao, final Pageable pageable) {
        RestPreconditions.validateFilter(localizacao);
        return RestPreconditions.checkFound(this.patrimonioRepository.findByLocalizacao(ProtheusUtils.preencheComZeros(localizacao, 6), pageable));
    }

    /**
     * @param matricula String
     * @param pageable  Pageable
     * @return Page<Patrimonio>
     */
    @PreAuthorize(Rule.ATIVO_FIXO_READ)
    @GetMapping("/{matricula}/porResponsavel")
    public Page<Patrimonio> listarPorResponsavel(@PathVariable("matricula") final String matricula, final Pageable pageable) {
        RestPreconditions.validateFilter(matricula);
        return RestPreconditions.checkFound(this.patrimonioRepository.findByResponsavel(ProtheusUtils.preencheComZeros(matricula, 6), pageable));
    }

}
