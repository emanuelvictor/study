package br.org.pti.integrator.application.resource;

import br.org.pti.integrator.domain.entities.pontoeletronico.BancoHora;
import br.org.pti.integrator.domain.services.BancoHoraService;
import br.org.pti.integrator.infrastructure.utils.components.security.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 1.0.0, 20/07/2017
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/bancohoras")
public class BancoHoraResource {

    private final BancoHoraService bancoHorasServico;

    /**
     * @param matricula
     * @return
     */
    @GetMapping("{matricula}/porMatricula")
    @PreAuthorize(Rule.PONTO_ELETRONICO_READ)
    public List<BancoHora> listarBancoHorasPorMatricula(@PathVariable("matricula") final String matricula) {
        return this.bancoHorasServico.listarBancoHorasPorMatricula(matricula);
    }
}
