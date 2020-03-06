package br.org.pti.api.functional.integrator.application.resource;

import br.org.pti.api.functional.integrator.domain.entities.contabilidade.Classe;
import br.org.pti.api.functional.integrator.domain.entities.dto.DadosOrcamentariosDTO;
import br.org.pti.api.functional.integrator.domain.services.ExecucaoOrcamentariaService;
import br.org.pti.api.functional.integrator.infrastructure.utils.components.RestPreconditions;
import br.org.pti.api.functional.integrator.infrastructure.utils.components.security.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * @author Willian Brecher
 * @version 1.0.0
 * @since 1.0.0, 14/01/2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/execucoesorcamentarias")
public class ExecucaoOrcamentariaResouce {

    private final ExecucaoOrcamentariaService execucaoOrcamentariaService;

    /**
     * @param centroCusto
     * @param classe
     * @return
     */
    @PreAuthorize(Rule.ORCAMENTO_READ)
    @GetMapping("{centroCusto}/porCentroCusto")
    public DadosOrcamentariosDTO burcaPorCentroCusto(@PathVariable("centroCusto") final String centroCusto, @RequestParam(value = "classe", defaultValue = "2") final int classe) {
        RestPreconditions.validateFilter(centroCusto);
        return RestPreconditions.checkFound(this.execucaoOrcamentariaService.buscaDadosPorCentroCusto(centroCusto, Classe.fromValor(classe)));
    }

    /**
     * @param fonteRecurso
     * @return
     */
    @PreAuthorize(Rule.ORCAMENTO_READ)
    @GetMapping("{fonteRecurso}/porFonteRecurso")
    public DadosOrcamentariosDTO burcaPorFonteRecurso(@PathVariable("fonteRecurso") String fonteRecurso) {
        RestPreconditions.validateFilter(fonteRecurso);
        return RestPreconditions.checkFound(this.execucaoOrcamentariaService.buscaDadosPorFonteDeRecurso(fonteRecurso));
    }

    /**
     * @param centroCusto
     * @param fonteRecurso
     * @param classe
     * @return
     */
    @GetMapping("{centroCusto}/{fonteRecurso}/porCentroCustoEFonteRecurso")
    @PreAuthorize(Rule.ORCAMENTO_READ)
    public DadosOrcamentariosDTO burcaPorCentroCustoEFonteRecurso(@PathVariable("centroCusto") final String centroCusto,
                                                                  @PathVariable("fonteRecurso") final String fonteRecurso,
                                                                  @RequestParam(value = "classe", defaultValue = "2") final int classe) {
        RestPreconditions.validateFilter(fonteRecurso);
        RestPreconditions.validateFilter(centroCusto);
        return RestPreconditions.checkFound(
                this.execucaoOrcamentariaService
                        .buscaDadosPorCentroCustoEFonteDeRecurso(
                                centroCusto, fonteRecurso, Classe.fromValor(classe)));
    }

}
