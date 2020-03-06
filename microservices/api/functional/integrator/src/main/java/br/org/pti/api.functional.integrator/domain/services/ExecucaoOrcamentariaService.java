package br.org.pti.api.functional.integrator.domain.services;

import br.org.pti.api.functional.integrator.domain.entities.contabilidade.CentroCusto;
import br.org.pti.api.functional.integrator.domain.entities.contabilidade.Classe;
import br.org.pti.api.functional.integrator.domain.entities.contabilidade.FonteDeRecurso;
import br.org.pti.api.functional.integrator.domain.entities.dto.DadosOrcamentariosDTO;
import br.org.pti.api.functional.integrator.domain.entities.oracamento.ExecucaoOrcamentaria;
import br.org.pti.api.functional.integrator.domain.entities.oracamento.TipoExecucaoOrcamentaria;
import br.org.pti.api.functional.integrator.domain.repositories.CentroCustoRepository;
import br.org.pti.api.functional.integrator.domain.repositories.ExecucaoOrcamentariaRepository;
import br.org.pti.api.functional.integrator.domain.repositories.FonteRecursoRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 18/01/2019
 */
@Service
@Transactional
public class ExecucaoOrcamentariaService {

    @Autowired
    private ExecucaoOrcamentariaRepository execucaoOrcamentariaRepository;
    @Autowired
    private CentroCustoRepository centroCustoRepository;
    @Autowired
    private FonteRecursoRepository fonteRecursoRepository;
    @Getter
    @Setter
    private DadosOrcamentariosDTO dadosOrcamentariosDTO;

    /**
     * Busca os dados de execucao orcamentaria por centro de custo
     *
     * @param codigo
     * @return
     */
    public DadosOrcamentariosDTO buscaDadosPorCentroCusto(String codigo,
            Classe classe) {
        Optional<CentroCusto> centroCusto =
                this.centroCustoRepository.findByCodigo(codigo, classe);

        if (centroCusto.isPresent()) {
            List<ExecucaoOrcamentaria> execucoesOrcamentarias
                    = this.execucaoOrcamentariaRepository
                            .findByCentroCusto(centroCusto.get());

            this.dadosOrcamentariosDTO
                    = this.calculaDadosOrcamentarios(execucoesOrcamentarias);

            this.dadosOrcamentariosDTO.setCentroCusto(centroCusto.get());
        }

        return this.dadosOrcamentariosDTO;
    }

    /**
     * Busca os dados de execucao orcamentaria por fonte de recurso
     *
     * @param codigo
     * @return
     */
    public DadosOrcamentariosDTO buscaDadosPorFonteDeRecurso(String codigo) {
        Optional<FonteDeRecurso> fonteDeRecurso
                = this.fonteRecursoRepository.findByCodigo(codigo);

        if (fonteDeRecurso.isPresent()) {
            List<ExecucaoOrcamentaria> execucoesOrcamentarias
                    = this.execucaoOrcamentariaRepository
                            .findByFonteDeRecurso(fonteDeRecurso.get());

            this.dadosOrcamentariosDTO
                    = this.calculaDadosOrcamentarios(execucoesOrcamentarias);

            this.dadosOrcamentariosDTO.setFonteDeRecurso(fonteDeRecurso.get());
        }

        return this.dadosOrcamentariosDTO;
    }

    /**
     * 
     * @param centroCustoId
     * @param fonteDeRecursoId
     * @return 
     */
    public DadosOrcamentariosDTO buscaDadosPorCentroCustoEFonteDeRecurso(
            String centroCustoId, String fonteDeRecursoId, Classe classe) {

        Optional<FonteDeRecurso> fonteDeRecurso
                = this.fonteRecursoRepository.findByCodigo(fonteDeRecursoId);

        Optional<CentroCusto> centroCusto
                = this.centroCustoRepository.findByCodigo(centroCustoId, classe);

        if (fonteDeRecurso.isPresent() && centroCusto.isPresent()) {
            List<ExecucaoOrcamentaria> execucoesOrcamentarias
                    = this.execucaoOrcamentariaRepository
                            .findByCentroCustoAndFonteDeRecurso(
                                    centroCusto.get(), fonteDeRecurso.get());

            this.dadosOrcamentariosDTO
                    = this.calculaDadosOrcamentarios(execucoesOrcamentarias);

            this.dadosOrcamentariosDTO.setFonteDeRecurso(fonteDeRecurso.get());
            this.dadosOrcamentariosDTO.setCentroCusto(centroCusto.get());
        }

        return this.dadosOrcamentariosDTO;
    }

    /**
     * Este metodo realiza o calculo das movimentacoes na AKD separando os
     * valores em: Empenhado, Realizado, Orcado, Contratado e Saldo.
     *
     * @param execucoesOrcamentarias
     * @return
     */
    private DadosOrcamentariosDTO calculaDadosOrcamentarios(
            List<ExecucaoOrcamentaria> execucoesOrcamentarias) {
        DadosOrcamentariosDTO dadosOrcamentarios = new DadosOrcamentariosDTO();

        execucoesOrcamentarias.forEach(item -> {
            switch (item.getTipoSaldoExecucaoOrcamentaria()) {
                case ORCADO:
                    if (item.getTipoExecucaoOrcamentaria()
                            .equals(TipoExecucaoOrcamentaria.CREDITO)) {
                        dadosOrcamentarios.setValorOrcado(dadosOrcamentarios.getValorOrcado().add(item.getValor().abs()));
                        dadosOrcamentarios.setValorSaldo(dadosOrcamentarios.getValorSaldo().add(item.getValor()));
                    } else {
                        dadosOrcamentarios.setValorOrcado(dadosOrcamentarios.getValorOrcado().subtract(item.getValor()));
                        dadosOrcamentarios.setValorSaldo(dadosOrcamentarios.getValorSaldo().subtract(item.getValor()));
                    }
                    break;
                case EMPENHADO:
                    if (item.getTipoExecucaoOrcamentaria()
                            .equals(TipoExecucaoOrcamentaria.CREDITO)) {
                        dadosOrcamentarios.setValorEmpenhado(dadosOrcamentarios.getValorEmpenhado().add(item.getValor()));
                        dadosOrcamentarios.setValorSaldo(dadosOrcamentarios.getValorSaldo().subtract(item.getValor()));
                    } else {
                        dadosOrcamentarios.setValorEmpenhado(dadosOrcamentarios.getValorEmpenhado().subtract(item.getValor()));
                        dadosOrcamentarios.setValorSaldo(dadosOrcamentarios.getValorSaldo().add(item.getValor()));
                    }
                    break;
                case REALIZADO:
                    if (item.getTipoExecucaoOrcamentaria()
                            .equals(TipoExecucaoOrcamentaria.CREDITO)) {
                        dadosOrcamentarios.setValorRealizado(dadosOrcamentarios.getValorRealizado().add(item.getValor()));
                        dadosOrcamentarios.setValorSaldo(dadosOrcamentarios.getValorSaldo().subtract(item.getValor()));
                    } else {
                        dadosOrcamentarios.setValorRealizado(dadosOrcamentarios.getValorRealizado().subtract(item.getValor()));
                        dadosOrcamentarios.setValorSaldo(dadosOrcamentarios.getValorSaldo().add(item.getValor()));
                    }
                    break;
                case CONTRATO:
                    if (item.getTipoExecucaoOrcamentaria()
                            .equals(TipoExecucaoOrcamentaria.CREDITO)) {
                        dadosOrcamentarios.setValorContrato(dadosOrcamentarios.getValorContrato().add(item.getValor()));
                        dadosOrcamentarios.setValorSaldo(dadosOrcamentarios.getValorSaldo().subtract(item.getValor()));
                    } else {
                        dadosOrcamentarios.setValorContrato(dadosOrcamentarios.getValorContrato().subtract(item.getValor()));
                        dadosOrcamentarios.setValorSaldo(dadosOrcamentarios.getValorSaldo().add(item.getValor()));
                    }
                    break;
                default:
                    break;
            }
        });

        return dadosOrcamentarios;
    }

}
