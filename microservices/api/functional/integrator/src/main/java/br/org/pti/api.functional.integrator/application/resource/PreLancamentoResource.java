package br.org.pti.integrator.application.resource;

import br.org.pti.integrator.domain.entities.contabilidade.PreLancamento;
import br.org.pti.integrator.domain.services.PreLancamentoService;
import br.org.pti.integrator.infrastructure.utils.components.RestPreconditions;
import br.org.pti.integrator.infrastructure.utils.components.security.Rule;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST resource de lancamentos contabeis
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 29/08/2017
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/prelancamentos")
public class PreLancamentoResource {

    private final PreLancamentoService preLancamentoService;

    /**
     * Metodo utilizado para criar os lancamentos contabeis
     *
     * @param lancamentos a lista de lancamentos a serem criados
     * @return o numero de lote para os lancamentos realizados
     */
    @PostMapping
    @PreAuthorize(Rule.CONTABILIDADE_WRITE)
    public Long criar(@Valid @NotEmpty(message = "pre-lancamento.lista-vazia") final List<PreLancamento> lancamentos) {
        return this.preLancamentoService.criar(lancamentos);
    }

    /**
     * Metodo para deletar um conjunto de lancamentos atraves do seu lote
     *
     * @param lote o lote que queremos deletar
     */
    @DeleteMapping("/{lote}")
    @PreAuthorize(Rule.CONTABILIDADE_WRITE)
    public void deletar(@PathVariable("lote") final long lote) {
        this.preLancamentoService.deletar(lote);
    }

    /**
     * Lista os lancamentos de um lote em especifico
     *
     * @param lote o lote da qual queremos saber os lancamentos
     * @return a lista com os lancamentos do lote informado
     */
    @GetMapping("/{lote}")
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    public List<PreLancamento> listarPorLote(@PathVariable("lote") final long lote) {
        return RestPreconditions.checkFound(this.preLancamentoService.listarPorLote(lote));
    }
}
