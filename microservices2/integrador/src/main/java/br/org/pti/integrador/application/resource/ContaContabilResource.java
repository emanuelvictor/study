package br.org.pti.integrador.application.resource;

import br.org.pti.integrador.domain.entities.contabilidade.ContaContabil;
import br.org.pti.integrador.domain.services.ContaContabilService;
import br.org.pti.integrador.infrastructure.utils.components.RestPreconditions;
import br.org.pti.integrador.infrastructure.utils.components.security.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Willian Brecher
 * @version 1.0.0
 * @since 1.0.0, 26/12/2017
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/contascontabeis")
public class ContaContabilResource {

    private final ContaContabilService contaContabilService;

    /**
     * Metodo responsavel pelo servico de busca de todas as contas contabeis
     *
     * @return lista paginada de contas contabeis
     */
    @GetMapping
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    public Page<ContaContabil> listarTodos(final Pageable pageable) {
        return this.contaContabilService.listarTodos(pageable);
    }

    /**
     * Metodo responsavel pelo servico de busca da conta contabil pelo numero da
     * conta informado
     *
     * @param numero numero da conta
     * @return ContaContabil
     */
    @GetMapping("/{numero}/porNumero")
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    public ContaContabil listaPorNumero(@PathVariable("numero") final String numero) {
        return RestPreconditions
                .checkFound(this.contaContabilService.
                        buscaContaPorNumero(numero));
    }

}
