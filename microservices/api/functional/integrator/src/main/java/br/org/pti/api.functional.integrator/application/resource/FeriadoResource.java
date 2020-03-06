package br.org.pti.api.functional.integrator.application.resource;

import br.org.pti.api.functional.integrator.domain.entities.pontoeletronico.Feriado;
import br.org.pti.api.functional.integrator.domain.entities.pontoeletronico.TipoFeriado;
import br.org.pti.api.functional.integrator.domain.repositories.FeriadoRepository;
import br.org.pti.api.functional.integrator.infrastructure.utils.components.RestPreconditions;
import br.org.pti.api.functional.integrator.infrastructure.utils.components.security.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Willian Brecher
 * @version 1.0.0
 * @since 1.0.0, 08/02/2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/feriados")
public class FeriadoResource {

    private final FeriadoRepository feriadoRepository;

    /**
     * @param dataInicio
     * @param dataFim
     * @return
     */
    @GetMapping("{dataInicio}/{dataFim}/porPeriodo")
    @PreAuthorize(Rule.PONTO_ELETRONICO_READ)
    public List<Feriado> buscaFeriadosPeriodo(@PathVariable("dataInicio") final LocalDate dataInicio,
                                              @PathVariable("dataFim") final LocalDate dataFim) {

        return RestPreconditions.checkFound(
                this.feriadoRepository.buscaFeriadosPeriodo(dataInicio, dataFim));
    }

    /**
     * @param tipoferiado
     * @return
     */
    @GetMapping("{tipoferiado}/porTipo")
    @PreAuthorize(Rule.PONTO_ELETRONICO_READ)
    public List<Feriado> buscaPorTipo(@PathParam("tipoferiado") final String tipoferiado) {
        return RestPreconditions.checkFound(this.feriadoRepository.listarFeriados(TipoFeriado.valueOf(tipoferiado)));
    }
}
