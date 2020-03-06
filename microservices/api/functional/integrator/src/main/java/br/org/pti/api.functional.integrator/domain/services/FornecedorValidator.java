package br.org.pti.api.functional.integrator.domain.services;

import br.org.pti.api.functional.integrator.domain.entities.dto.FornecedorDTO;
import br.org.pti.api.functional.integrator.infrastructure.utils.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 14/05/2019
 */
@Component
public class FornecedorValidator {

    private List<String> opcoes = Arrays.asList("S", "N", "1", "2");
    private List<String> tiposContas = Arrays.asList("C", "P");
    private List<String> formasPagamento = Arrays.asList("D", "B");
    private List<String> tiposPessoaJuridica = Arrays.asList("1", "2", "3", "4", "5");
    private List<String> calculaIRRF = Arrays.asList("1", "2", "3", "4");
    private List<String> tiposPessoa = Arrays.asList("CI", "PF", "OS");

    /**
     *
     * @param fornecedorDTO
     * @return
     */
    public FornecedorDTO validaOpcoes(FornecedorDTO fornecedorDTO) {

        if (!this.opcoes.contains(fornecedorDTO.getCooperativa())) {
            throw new ValidationException("fornecedor.campo-cooperativa-invalido");
        }
        if (!this.opcoes.contains(fornecedorDTO.getCalculaINSS())) {
            throw new ValidationException("fornecedor.campo-calculainss-invalido");
        }
        if (!this.calculaIRRF.contains(fornecedorDTO.getCalculaIRRF())) {
            throw new ValidationException("fornecedor.campo-calculairrf-invalido");
        }
        if (!this.opcoes.contains(fornecedorDTO.getRecolhimentoISS())) {
            throw new ValidationException("fornecedor.campo-recolhimentoiss-invalido");
        }
        if (!this.opcoes.contains(fornecedorDTO.getRecolhimentoCSLL())) {
            throw new ValidationException("fornecedor.campo-recolhimentocsll-invalido");
        }
        if (!this.opcoes.contains(fornecedorDTO.getRecolhimentoCOFINS())) {
            throw new ValidationException("fornecedor.campo-recolhimentoconfins-invalido");
        }
        if (!this.opcoes.contains(fornecedorDTO.getRecolhimentoPIS())) {
            throw new ValidationException("fornecedor.campo-recolhimentopis-invalido");
        }
        if (!this.opcoes.contains(fornecedorDTO.getSimplesNacional())) {
            throw new ValidationException("fornecedor.campo-simplesnacional-invalido");
        }
        if (!this.tiposContas.contains(fornecedorDTO.getTipoConta())) {
            throw new ValidationException("fornecedor.campo-tipoconta-invalido");
        }
        if (!this.formasPagamento.contains(fornecedorDTO.getFormaPagamento())) {
            throw new ValidationException("fornecedor.campo-formapagamento-invalido");
        }
        if (!this.tiposPessoaJuridica.contains(fornecedorDTO.getTipoPessoaJuridica())) {
            throw new ValidationException("fornecedor.campo-tipopessoajuridica-invalido");
        }
        if (!this.tiposPessoa.contains(fornecedorDTO.getTipoPessoa())) {
            throw new ValidationException("fornecedor.campo-tipopessoa-invalido");
        }

        return fornecedorDTO;
    }

}
