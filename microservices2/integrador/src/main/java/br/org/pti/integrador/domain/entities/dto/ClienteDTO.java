package br.org.pti.integrador.domain.entities.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 1.0.0, 04/10/2017
 */
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteDTO implements Serializable {

    @Getter
    @Setter
    @NotBlank(message = "cliente-dto.nome-nulo")
    private String nome;
    @Getter
    @Setter
    @NotBlank(message = "cliente-dto.documento-nulo")
    private String documento;
    @Getter
    @Setter
    @NotBlank(message = "cliente-dto.tipo-nulo")
    private String tipo;
    @Getter
    @Setter
    @NotBlank(message = "cliente-dto.email-nulo")
    private String email;
    @Getter
    @Setter
    @NotBlank(message = "cliente-dto.municipio-nulo")
    private String municipio;
    @Getter
    @Setter
    @NotBlank(message = "cliente-dto.pais-nulo")
    private String pais;
    @Getter
    @Setter
    @NotBlank(message = "cliente-dto.uf-nulo")
    private String uf;
    @Getter
    @Setter
    @NotBlank(message = "cliente-dto.rua-nulo")
    private String rua;
    @Getter
    @Setter
    @NotBlank(message = "cliente-dto.numero-nulo")
    private String numero;
    @Getter
    @Setter
    @NotBlank(message = "cliente-dto.bairro-nulo")
    private String bairro;
    @Getter
    @Setter
    private String cep;
    @Getter
    @Setter
    private String ddd;
    @Getter
    @Setter
    private String telefone;

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
     * processo de atualizacao do cliente
     */
    @Getter
    @Setter
    private String codigo;
    @Getter
    @Setter
    private String loja;

    /**
     * Utilize este metodo para que internamente as informacoes refentes a um
     * cadastro estrangeiro sejam auto-preenchidas
     * <p>
     * As propriedades serao setadas como abaixo:
     * <p>
     * this.tipo = "X";
     * this.uf = "EX";
     * this.municipio = "99999";
     * this.ddd = "000";
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
}
