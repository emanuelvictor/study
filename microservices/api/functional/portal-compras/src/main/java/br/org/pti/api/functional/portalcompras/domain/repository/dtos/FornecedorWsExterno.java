package br.org.pti.api.functional.portalcompras.domain.repository.dtos;

import br.org.pti.api.functional.portalcompras.domain.entity.fornecedor.AtividadeEconomica;
import lombok.Data;

@Data
public class FornecedorWsExterno {

    private String nome;

    private String uf;

    private String telefone;

    private String email;

    private String logradouro;

    private String numero;

    private String cep;

    private String municipio;

    private String status;

    private String fantasia;

    private String complemento;

    private AtividadeEconomica[] atividade_principal;

    private AtividadeEconomica[] atividades_secundarias;
}
