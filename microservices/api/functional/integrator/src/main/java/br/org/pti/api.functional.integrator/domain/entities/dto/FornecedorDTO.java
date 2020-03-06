package br.org.pti.api.functional.integrator.domain.entities.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 09/05/2019
 */
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class FornecedorDTO implements Serializable {

    @Getter
    @Setter
    @Size(max = 40, message = "fornecedor-dto.nome-size")
    @NotBlank(message = "fornecedor-dto.nome-nulo")
    private String nome;
    @Getter
    @Setter
    @Size(max = 40, message = "fornecedor-dto.nome-fantasia-size")
    @NotBlank(message = "fornecedor-dto.nome-fantasia-nulo")
    private String nomeFantasia;
    @Getter
    @Setter
    @Size(max = 14, message="fornecedor-dto.documento-size")
    private String documento;
    @Getter
    @Setter
    @Size(max = 18, message="fornecedor-dto.inscricao-estadual-size")
    private String inscricaoEstadual;
    @Getter
    @Setter
    @Size(max = 18, message="fornecedor-dto.inscricao-municipal-size")
    private String inscricaoMunicipal;
    @Getter
    @Setter
    @Size(max = 1, message="fornecedor-dto.tipo-size")
    @NotBlank(message = "fornecedor-dto.tipo-nulo")
    private String tipo;
    @Getter
    @Setter
    @Size(max = 2, message="fornecedor-dto.tipo-pessoa-size")
    @NotBlank(message = "fornecedor-dto.tipo-pessoa-nulo")
    private String tipoPessoa;
    @Getter
    @Setter
    @Size(max = 1, message="fornecedor-dto.tipo-pessoa-juridica-size")
    private String tipoPessoaJuridica;
    @Getter
    @Setter
    @Size(max = 100, message="fornecedor-dto.email-size")
    @NotBlank(message = "fornecedor-dto.email-nulo")
    private String email;
    @Getter
    @Setter
    @Size(max = 5, message="fornecedor-dto.municipio-size")
    private String municipio;
    @Getter
    @Setter
    @Size(max = 4, message="fornecedor-dto.pais-size")
    @NotBlank(message = "fornecedor-dto.pais-nulo")
    private String pais;
    @Getter
    @Setter
    @Size(max = 2, message="fornecedor-dto.uf-size")
    @NotBlank(message = "fornecedor-dto.uf-nulo")
    private String uf;
    @Getter
    @Setter
    @Size(max = 40, message="fornecedor-dto.rua-size")
    @NotBlank(message = "fornecedor-dto.rua-nulo")
    private String rua;
    @Getter
    @Setter
    @Size(max = 6, message="fornecedor-dto.numero-size")
    @NotBlank(message = "fornecedor-dto.numero-nulo")
    private String numero;
    @Getter
    @Setter
    @Size(max = 20, message="fornecedor-dto.bairro-size")
    @NotBlank(message = "fornecedor-dto.bairro-nulo")
    private String bairro;
    @Getter
    @Setter
    @Size(max = 50, message = "fornecedor-dto.complemento-size")
    private String complemento;
    @Getter
    @Setter
    @Size(max = 10, message="fornecedor-dto.natureza-size")
    @NotBlank(message = "fornecedor-dto.natureza-nulo")
    private String natureza;
    @Getter
    @Setter
    @Size(max = 8, message="fornecedor-dto.cep-size")
    private String cep;
    @Getter
    @Setter
    @Size(max = 3, message="fornecedor-dto.ddd-size")
    private String ddd;
    @Getter
    @Setter
    @Size(max = 50, message="fornecedor-dto.telefone-size")
    private String telefone;
    @Getter
    @Setter
    @Size(max = 3, message="fornecedor-dto.banco-size")
    @NotBlank(message = "fornecedor-dto.banco-nulo")
    private String banco;
    @Getter
    @Setter
    @Size(max = 5, message="fornecedor-dto.agencia-size")
    @NotBlank(message = "fornecedor-dto.agencia-nulo")
    private String agencia;
    @Getter
    @Setter
    @Size(max = 20, message="fornecedor-dto.numero-conta-size")
    @NotBlank(message = "fornecedor-dto.numero-conta-nulo")
    private String numeroConta;
    @Getter
    @Setter
    @Size(max = 1, message="fornecedor-dto.tipo-conta-size")
    @NotBlank(message = "fornecedor-dto.tipo-conta-nulo")
    private String tipoConta;
    @Getter
    @Setter
    @Size(max = 1, message="fornecedor-dto.forma-pagamento-size")
    @NotBlank(message = "fornecedor-dto.forma-pagamento-nulo")
    private String formaPagamento;
    @Getter
    @Size(max = 1, message="fornecedor-dto.simples-nacional-size")
    @NotBlank(message = "fornecedor-dto.simples-nacional-nulo")
    private String simplesNacional;
    @Getter
    @Setter
    @Size(max = 2, message="fornecedor-dto.digito-conta-size")
    @NotBlank(message = "fornecedor-dto.digito-conta-nulo")
    private String digitoConta;
    @Getter
    @Size(max = 1, message="fornecedor-dto.recolhimento-pis-size")
    @NotBlank(message = "fornecedor-dto.recolhimento-pis-nulo")
    private String recolhimentoPIS;
    @Getter
    @Size(max = 1, message="fornecedor-dto.recolhimento-cofins-size")
    @NotBlank(message = "fornecedor-dto.recolhimento-cofins-nulo")
    private String recolhimentoCOFINS;
    @Getter
    @Size(max = 1, message="fornecedor-dto.recolhimento-csll-size")
    @NotBlank(message = "fornecedor-dto.recolhimento-csll-nulo")
    private String recolhimentoCSLL;
    @Getter
    @Setter
    @Size(max = 1, message="fornecedor-dto.recolhimento-iss-size")
    @NotBlank(message = "fornecedor-dto.recolhimento-iss-nulo")
    private String recolhimentoISS;
    @Getter
    @Size(max = 1, message="fornecedor-dto.calcula-irrf-size")
    @NotBlank(message = "fornecedor-dto.calcula-irrf-nulo")
    private String calculaIRRF;
    @Getter
    @Setter
    @Size(max = 1, message="fornecedor-dto.calcula-inss-size")
    @NotBlank(message = "fornecedor-dto.calcula-inss-nulo")
    private String calculaINSS;
    @Getter
    @Setter
    @Size(max = 1, message="fornecedor-dto.cooperativa-size")
    @NotBlank(message = "fornecedor-dto.cooperativa-nulo")
    private String cooperativa;

    /**
     * Em caso de erro estes atributos vao estar preenchidos após a request
     */
    @Getter
    @Setter
    private int errorCode;
    @Getter
    @Setter
    private String errorMessage;

    /**
     * As duas propriedades abaixo sao utilizadas unica e exclusivamente para o
     * processo de atualizacao do fornecedor
     */
    @Getter
    @Setter
    @Size(max = 9, message="fornecedor-dto.codigo-size")
    private String codigo;
    @Getter
    @Setter
    @Size(max = 4, message="fornecedor-dto.loja-size")
    private String loja;

    /**
     * Utilize este metodo para que internamente as informacoes refentes a um
     * cadastro estrangeiro sejam auto-preenchidas
     *
     * As propriedades serao setadas como abaixo:
     *
     * this.tipo = "X"; this.uf = "EX"; this.municipio = "99999"; this.ddd =
     * "000";
     */
    public void tornaEstrangeiro() {

        this.tipo = "X";
        this.uf = "EX";
        this.municipio = "99999";
        this.ddd = "000";
    }

    /**
     * Caso haja um erro este metodo verifica as mensagems de erro para sabermos
     * se estao preenchidas e entao tratarmos
     *
     * @return se temos ou nao um erro relatado
     */
    public boolean obteveSucesso() {
        return StringUtils.isBlank(this.errorMessage) && this.errorCode == 0;
    }

    /**
     * Metodo para chegar se codigo e loja do cliente foram informados
     *
     * @return true se foi e false se nao
     */
    public boolean temCodigoELoja() {
        return StringUtils.isNotBlank(this.codigo) && StringUtils.isNotBlank(this.loja);
    }

    /**
     * 
     * @param simplesNacional 
     */
    public void setSimplesNacional(String simplesNacional) {
        if (simplesNacional.equalsIgnoreCase("S")) {
            this.simplesNacional = "1";
        } else {
            this.simplesNacional = "2";
        }
    }

    /**
     * 
     * @param recolhimentoPIS 
     */
    public void setRecolhimentoPIS(String recolhimentoPIS) {
        if (recolhimentoPIS.equalsIgnoreCase("S")) {
            this.recolhimentoPIS = "1";
        } else {
            this.recolhimentoPIS = "2";
        }
    }
    
    /**
     * 
     * @param recolhimentoCOFINS 
     */
    public void setRecolhimentoCOFINS(String recolhimentoCOFINS) {
        if (recolhimentoCOFINS.equalsIgnoreCase("S")) {
            this.recolhimentoCOFINS = "1";
        } else {
            this.recolhimentoCOFINS = "2";
        }
    }

    /**
     * 
     * @param recolhimentoCSLL 
     */
    public void setRecolhimentoCSLL(String recolhimentoCSLL) {
        if (recolhimentoCSLL.equalsIgnoreCase("S")) {
            this.recolhimentoCSLL = "1";
        } else {
            this.recolhimentoCSLL = "2";
        }
    }

    /**
     * 
     * @param calculaIRRF 
     */
    public void setCalculaIRRF(String calculaIRRF) {
        if (calculaIRRF.equalsIgnoreCase("S")) {
            this.calculaIRRF = "1";
        } else {
            this.calculaIRRF = "2";
        }
    }

}
