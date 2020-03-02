package br.org.pti.integrator.application.resource;

import br.org.pti.integrator.domain.entities.rh.Funcionario;
import br.org.pti.integrator.domain.entities.rh.SituacaoFolha;
import br.org.pti.integrator.domain.repositories.FuncionarioRepository;
import br.org.pti.integrator.infrastructure.utils.components.RestPreconditions;
import br.org.pti.integrator.infrastructure.utils.components.security.Rule;
import br.org.pti.integrator.infrastructure.utils.protheus.ProtheusUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 1.0.0, 31/01/2018
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/funcionarios")
public class FuncionarioResource {

    private final FuncionarioRepository funcionarioRepository;

    /**
     * @param situacaoFolha
     * @param matricula
     * @return
     */
    @PreAuthorize(Rule.RH_READ)
    @GetMapping("{situacaoFolha}/{matricula}")
    public Funcionario buscarPorMatricula(final @PathVariable("situacaoFolha") SituacaoFolha situacaoFolha, final @PathVariable("matricula") String matricula) {

        RestPreconditions.validateFilter(matricula);

        final String ra_mat = ProtheusUtils.preencheComZeros(matricula, 6);

        return RestPreconditions.checkFound(this.funcionarioRepository.findByMatriculaAndSituacaoFolha(ra_mat, situacaoFolha));
    }

    /**
     * @param situacaoFolha
     * @param email
     * @return
     */
    @PreAuthorize(Rule.RH_READ)
    @GetMapping("{situacaoFolha}/{email}/porEmail")
    public Funcionario buscarPorEmail(final @PathVariable("situacaoFolha") SituacaoFolha situacaoFolha, final @PathVariable("email") String email) {

        RestPreconditions.validateFilter(email);

        return RestPreconditions.checkFound(this.funcionarioRepository.findByEmailAndSituacaoFolha(email, situacaoFolha));
    }

    /**
     * @param situacaoFolha
     * @param nome
     * @param pageable
     * @return
     */
    @PreAuthorize(Rule.RH_READ)
    @GetMapping("{situacaoFolha}/{nome}/porNome")
    public Page<Funcionario> buscarPorNome(final @PathVariable("situacaoFolha") SituacaoFolha situacaoFolha,
                                           final @PathVariable("nome") String nome, final Pageable pageable) {

        RestPreconditions.validateFilter(nome);

        return RestPreconditions.checkFound(this.funcionarioRepository.findByNomeAndSituacaoFolha(nome, situacaoFolha, pageable));
    }

    /**
     * @param departamento
     * @param pageable
     * @return
     */
    @PreAuthorize(Rule.RH_READ)
    @GetMapping("{departamento}/porDepartamento")
    public Page<Funcionario> buscarPorDepartamento(final @PathVariable("departamento") String departamento, final Pageable pageable) {

        RestPreconditions.validateFilter(departamento);

        final String ra_depto = ProtheusUtils.preencheComZeros(departamento, 9);


        return RestPreconditions.checkFound(this.funcionarioRepository.findByDepartamentoAndSituacaoFolha(ra_depto, pageable));
    }

    /**
     * @param centroCusto
     * @param pageable
     * @return
     */
    @PreAuthorize(Rule.RH_READ)
    @GetMapping("{centroCusto}/porCentroCusto")
    public Page<Funcionario> buscarPorCentroCusto(final @PathVariable("centroCusto") String centroCusto, final Pageable pageable) {

        RestPreconditions.validateFilter(centroCusto);

        return RestPreconditions.checkFound(this.funcionarioRepository.findByCentroCusto(centroCusto, pageable));
    }

    /**
     * @param matricula
     * @return
     */
    @PreAuthorize(Rule.RH_READ)
    @GetMapping("{matricula}/porMatricula")
    public Funcionario buscarPorMatricula(final @PathVariable("matricula") String matricula) {

        RestPreconditions.validateFilter(matricula);

        final String ra_mat = ProtheusUtils.preencheComZeros(matricula, 6);

        return RestPreconditions.checkFound(this.funcionarioRepository.findByMatricula(ra_mat));
    }
}
