package br.org.pti.integrator.application.resource;

import br.org.pti.integrator.domain.entities.contabilidade.CentroCusto;
import br.org.pti.integrator.domain.entities.contabilidade.Classe;
import br.org.pti.integrator.domain.entities.contabilidade.GestorCentroCusto;
import br.org.pti.integrator.domain.repositories.CentroCustoRepository;
import br.org.pti.integrator.domain.repositories.GestorCentroCustoRepository;
import br.org.pti.integrator.infrastructure.utils.components.RestPreconditions;
import br.org.pti.integrator.infrastructure.utils.components.security.Rule;
import br.org.pti.integrator.infrastructure.utils.exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 1.4.0, 22/08/2018
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/centroscusto")
public class CentroCustoResource {

    private final CentroCustoRepository centroCustoRepository;

    private final GestorCentroCustoRepository gestorCentroCustoRepository;

    /**
     * Busca uma lista de todos os centros de custo nao deletados
     *
     * @return lista de centros de custos
     */
    @GetMapping
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    public Page<CentroCusto> listarTodos(final Pageable pageable) {
        return this.centroCustoRepository.findAllNotDeleted(pageable);
    }

    /**
     * Busca o centro de custo pelo codigo informado
     *
     * @param codigo numero do centro de custo
     * @param classe
     * @return retorna o centro de custo
     */
    @GetMapping("{codigo}")
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    public CentroCusto buscarPorCodigo(@PathVariable("codigo") final String codigo, @RequestParam(value = "classe", defaultValue = "2") final int classe) {
        RestPreconditions.validateFilter(codigo);
        return RestPreconditions.checkFound(this.centroCustoRepository.findByCodigo(codigo, Classe.fromValor(classe)));
    }

    /**
     * @param classe   String
     * @param pageable Pageable
     * @return Page<CentroCusto>
     */
    @GetMapping("{classe}/porClasse")
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    public Page<CentroCusto> listarTodos(@PathVariable("classe") final int classe, final Pageable pageable) {
        return this.centroCustoRepository.findAllNotDeletedByClasse(Classe.fromValor(classe), pageable);
    }

    /**
     * @param codigo String
     * @param classe int
     * @return CentroCusto
     */
    @GetMapping("{codigo}/{classe}")
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    public CentroCusto buscarPorCodigoClasse(@PathVariable("codigo") final String codigo, @PathVariable("classe") final int classe) {

        RestPreconditions.validateFilter(codigo);
        RestPreconditions.validateFilter(classe);

        return RestPreconditions.checkFound(this.centroCustoRepository.findByCodigoAndClasse(codigo, Classe.fromValor(classe)));
    }

    /**
     * Busca o centro de custo contendo o codigo informado
     *
     * @param codigo   String
     * @param pageable Pageable
     * @return Page<CentroCusto>
     */
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    @GetMapping("{codigo}/porCodigoContendo")
    public Page<CentroCusto> buscarPorCodigoContendo(final @PathVariable("codigo") String codigo, final Pageable pageable) {

        RestPreconditions.validateFilter(codigo);
        if (codigo.length() < 3) {
            throw new ValidationException("centro-custo.tamanho-invalido");
        }
        return RestPreconditions.checkFound(this.centroCustoRepository.findByCodigoLike(codigo, pageable));
    }

    /**
     * Busca o centro de custo contendo a descricao informado
     *
     * @param descricao Optional<String>
     * @param classe    int
     * @param pageable  Pageable
     * @return Page<CentroCusto>
     */
    @GetMapping("/porDescricaoContendo")
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    public Page<CentroCusto> buscarPorDescricaoContendo(@RequestParam(value = "descricao", defaultValue = "") final String descricao, @RequestParam(value = "classe", defaultValue = "2") final int classe, final Pageable pageable) {
        if (descricao != null && !descricao.isEmpty()) {

            if (descricao.length() < 3) {
                throw new ValidationException("centro-custo.tamanho-invalido");
            }

            return RestPreconditions.checkFound(this.centroCustoRepository.findByDescricaoLike(descricao, Classe.fromValor(classe), pageable));
        } else
            return RestPreconditions.checkFound(this.centroCustoRepository.findAllNotDeletedByClasse(Classe.fromValor(classe), pageable));
    }

    /**
     * Busca o centro de custo contendo a descricao e classe informada
     *
     * @param classe    int
     * @param descricao String
     * @param pageable  Pageable
     * @return Page<CentroCusto>
     */
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    @GetMapping("{classe}/{descricao}/porClasseDescricaoContendo")
    public Page<CentroCusto> buscarPorClasseDescricaoContendo(@PathVariable("classe") final int classe, @PathVariable("descricao") final String descricao, final Pageable pageable) {
        RestPreconditions.validateFilter(descricao);

        if (descricao.length() < 3) {
            throw new ValidationException("centro-custo.tamanho-invalido");
        }
        return RestPreconditions.checkFound(this.centroCustoRepository.findByclasseAndDescricaoLike(Classe.fromValor(classe), descricao, pageable));
    }

    /**
     * Busca os centros de custo contendo a descricao e o gestor
     *
     * @param centrocusto String
     * @param pageable    Pageable
     * @return Page<GestorCentroCusto>
     */
    @GetMapping("/comGestores")
    @PreAuthorize(Rule.CONTABILIDADE_READ)
    public Page<GestorCentroCusto> listarTodosComGestores(@RequestParam(value = "centrocusto", defaultValue = "") final String centrocusto, final Pageable pageable) {

        if (centrocusto != null && !centrocusto.isEmpty()) {
            return this.gestorCentroCustoRepository.findAllByCentroCusto(centrocusto, pageable);
        } else {
            return this.gestorCentroCustoRepository.findAllGestorCentroCustos(pageable);
        }
    }
}
