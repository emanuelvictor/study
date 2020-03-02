package br.org.pti.compras.domain.repository.dtos;

import lombok.Data;

/**
 * DTO á ser enviado para integração com o protheus.
 */
@Data
public class FornecedorProtheus {

    public static final String REGEX_TO_REMOVE_ESPECIAL_CHARACTERS_TO_PROTHEUS = "[&+-/-\\\\\"-#-$-%-\\]\\[{}()@*']+";

    private String nome;
    private String nomeFantasia;
    private String documento;
    private String loja;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String tipo;
    private String tipoPessoa;
    private String tipoPessoaJuridica;
    private String email;
    private String municipio;
    private String pais;
    private String uf;
    private String rua;
    private String numero;
    private String bairro;
    private String complemento;
    private String natureza;
    private String cep;
    private String telefone;
    private String ddd;
    private String banco;
    private String agencia;
    private String numeroConta;
    private String tipoConta;
    private String formaPagamento;
    private String simplesNacional;
    private String digitoConta;
    private String recolhimentoPIS;
    private String recolhimentoCOFINS;
    private String recolhimentoCSLL;
    private String recolhimentoISS;
    private String calculaIRRF;
    private String calculaINSS;
    private String cooperativa;
    private String codigo;

    public void setNome(String nome) {
        if (nome != null) {
            if (nome.length() > 39)
                nome = nome.substring(0, 39);
            this.nome = nome.replaceAll(REGEX_TO_REMOVE_ESPECIAL_CHARACTERS_TO_PROTHEUS, "").toUpperCase().trim();
        } else
            this.nome = null;
    }

    public void setNomeFantasia(String nomeFantasia) {
        if (nomeFantasia != null) {
            if (nomeFantasia.length() > 39)
                nomeFantasia = nomeFantasia.substring(0, 39);
            this.nomeFantasia = nomeFantasia.toUpperCase().trim();
        } else {
            this.nomeFantasia = null;
        }
    }

    public void setEmail(String email) {
        if (email != null) {
            if (email.length() > 99)
                email = email.substring(0, 99).toUpperCase();
        }
        this.email = email;
    }
}
