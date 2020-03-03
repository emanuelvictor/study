package br.org.pti.api.functional.portalcompras.domain.entity.endereco;

import br.org.pti.api.functional.portalcompras.ComprasApplication;
import br.org.pti.api.functional.portalcompras.domain.entity.generic.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 *
 */
@Data
@Entity
@Audited
@Table(schema = ComprasApplication.ENDERECO)
@EqualsAndHashCode(callSuper = false)
@AuditTable(value = "endereco" + ComprasApplication.AUDIT_SUFFIX, schema = ComprasApplication.ENDERECO + ComprasApplication.AUDIT_SUFFIX)
public class Endereco extends AbstractEntity {

    @Length(max = 40)
    @Column(length = 40)
    private String logradouro;

    @Length(max = 50)
    @Column(length = 50)
    private String complemento;

    @Length(max = 20)
    @Column(length = 20)
    private String bairro;

    @Column
    @Length(max = 9)
    private String cep;

    @Length(max = 20)
    @Column(length = 20)
    private String numero;

    @Length(max = 200)
    @Column(length = 200)
    private String caixaPostal;

    /**
     * {@link Cidade }
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Cidade cidade;

    public String getLogradouro() {
        return logradouro != null ? logradouro.toUpperCase() : null;
    }

    public void setLogradouro(final String logradouro) {
        this.logradouro = logradouro != null ? logradouro.toUpperCase() : null;
    }

    public String getComplemento() {
        return complemento != null ? complemento.toUpperCase() : "              ";
    }

    public void setComplemento(final String complemento) {
        this.complemento = complemento != null ? complemento.toUpperCase() : null;
    }

    public String getBairro() {
        return bairro != null ? bairro.toUpperCase() : null;
    }

    public void setBairro(final String bairro) {
        this.bairro = bairro != null ? bairro.toUpperCase() : null;
    }

    public String getCep() {
        return cep != null ? cep.toUpperCase() : null;
    }

    public void setCep(final String cep) {
        this.cep = cep != null ? cep.toUpperCase() : null;
    }

    public String getNumero() {
        return numero != null ? numero.toUpperCase() : null;
    }

    public void setNumero(final String numero) {
        this.numero = numero != null ? numero.toUpperCase() : null;
    }

    public String getCaixaPostal() {
        return caixaPostal != null ? caixaPostal.toUpperCase() : null;
    }

    public void setCaixaPostal(final String caixaPostal) {
        this.caixaPostal = caixaPostal != null ? caixaPostal.toUpperCase() : null;
    }
}
