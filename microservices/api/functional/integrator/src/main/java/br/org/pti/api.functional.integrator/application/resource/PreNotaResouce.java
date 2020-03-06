package br.org.pti.api.functional.integrator.application.resource;

import br.org.pti.api.functional.integrator.infrastructure.utils.components.security.Rule;
import br.org.pti.api.functional.integrator.domain.entities.contabilidade.ItemPreNota;
import br.org.pti.api.functional.integrator.domain.entities.contabilidade.PreNota;
import br.org.pti.api.functional.integrator.domain.services.PreNotaService;
import br.org.pti.api.functional.integrator.infrastructure.utils.components.RestPreconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * REST resource de lancamento de notas fiscais
 *
 * @author Willian Brecher
 * @version 1.0.0
 * @since 1.0.0, 24/10/2017
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/prenotas")
public class PreNotaResouce {

    private final PreNotaService preNotaService;

    /**
     * Integra as pre notas fiscais
     *
     * @param preNotas a lista de pre notas a ser criada
     * @return a response com o lote de notas criado
     */
    @PostMapping
    @PreAuthorize(Rule.CONTABILIDADE_WRITE)
    public Long criar(@Valid @NotNull(message = "pre-nota.lista-notas-vazia") final List<PreNota> preNotas) {
        return this.preNotaService.salvar(preNotas);
    }

    /**
     * Metodo para deletar um conjunto de notas atraves do seu lote
     *
     * @param lote o lote que queremos deletar
     */
    @DeleteMapping("/{lote}")
    @PreAuthorize(Rule.CONTABILIDADE_WRITE)
    public void deletar(@PathVariable("lote") final long lote) {
        this.preNotaService.deletar(lote);
    }

    /**
     * Metodo responsavel pelo servico de busca das notas pelo lote informado
     *
     * @param lote lote das notas a serem buscadas
     * @return lista de pre notas
     */
    @GetMapping("/{lote}")
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    public List<PreNota> listaPorNumero(@PathVariable("lote") final long lote) {

        final List<PreNota> listaNotasFiscais = RestPreconditions.checkFound(this.preNotaService.buscarPorLote(lote));

        final List<PreNota> novaListaNotasFiscais = new ArrayList<>();

        listaNotasFiscais.forEach(nota -> {

            final List<ItemPreNota> itensNotaFiscal = this.preNotaService.buscarItensPorDocumento(nota.getDocumento());

            nota.setItensPreNota(itensNotaFiscal);
            novaListaNotasFiscais.add(nota);
        });

        return novaListaNotasFiscais;
    }

    /**
     * Busca a nota fiscal referente ao numero especificado
     *
     * @param numero numero da nota fiscal
     * @return NotaFiscal
     */
    @GetMapping("/{numero}/porNumero")
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    public PreNota listaPorNumero(@PathVariable("numero") final String numero) {

        final PreNota notaFiscal = RestPreconditions.checkFound(this.preNotaService.buscarNotaPorNumero(numero));

        final List<ItemPreNota> itensNotaFiscal = this.preNotaService.buscarItensPorDocumento(notaFiscal.getDocumento());

        notaFiscal.setItensPreNota(itensNotaFiscal);

        return notaFiscal;
    }
}
