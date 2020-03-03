package br.org.pti.api.functional.portalcompras.domain.entity.configuracao;

import br.com.caelum.stella.format.CNPJFormatter;
import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.org.pti.api.functional.portalcompras.ComprasApplication;
import br.org.pti.api.functional.portalcompras.domain.entity.generic.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.regex.Pattern;

@Data
@Entity
@Audited
@Table(schema = ComprasApplication.CONFIGURACAO)
@JsonIgnoreProperties({"authorities"})
@lombok.EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
@AuditTable(value = "pessoa" + ComprasApplication.AUDIT_SUFFIX, schema = ComprasApplication.CONFIGURACAO + ComprasApplication.AUDIT_SUFFIX)
public class Pessoa extends AbstractEntity implements Serializable {

    /**
     *
     */
    @NotBlank
    @Length(max = 150)
    @Column(nullable = false, length = 150)
    protected String nome;

    /**
     *
     */
    @Column(unique = true, length = 14)
    protected String documento;

    /**
     * Remove '.', '/' e '-'
     *
     * @param documento {String}
     * @return {String}
     */
    private static String prepareDocumento(final String documento) {
        if (documento == null) {
            return null;
        }
        if (documento.contains("@")) {
            return documento;
        }

        return documento.replaceAll(Pattern.quote("."), "").replaceAll(Pattern.quote("/"), "").replaceAll(Pattern.quote("-"), "");
    }

    /**
     * @param documento {String}
     * @return {String}
     */
    public static String validateDocumento(final String documento) {
        if (documento == null || documento.trim().length() == 0) return null;
        if (!Pessoa.prePreparedDocumento(documento))
            return documento;
        else if (Pessoa.prePreparedDocumento(documento) == null)
            return null;

        final String doc = prepareDocumento(documento);

        final CNPJValidator cnpjValidator = new CNPJValidator();
        final CPFValidator cpfValidator = new CPFValidator();

        if (cpfValidator.isEligible(doc)) {
            try {
                cpfValidator.assertValid(doc);
            } catch (Exception e) {
                throw new Usuario.CpfException();
            }
        } else if (cnpjValidator.isEligible(doc)) {
            try {
                cnpjValidator.assertValid(doc);
            } catch (Exception e) {
                throw new Usuario.CnpjException();
            }
        } else {
            throw new CpfCnpjException();
        }
        return doc;
    }

    /**
     * @param documento {String}
     * @return {Boolean}
     */
    private static Boolean prePreparedDocumento(final String documento) {
        if (documento == null || documento.length() < 1) {
            return null;
        }
        return !documento.contains("@");
    }

    /**
     * Devolve os pontos e barra ao documento
     *
     * @param documento {String}
     * @return {String}
     */
    public static String formatDocumento(final String documento) {
        if (!Pessoa.prePreparedDocumento(documento))
            return documento;
        else if (Pessoa.prePreparedDocumento(documento) == null)
            return null;

        final String doc = prepareDocumento(documento);

        final CNPJValidator cnpjValidator = new CNPJValidator();
        final CPFValidator cpfValidator = new CPFValidator();


        final CNPJFormatter cnpjFormatter = new CNPJFormatter();
        final CPFFormatter cpfFormatter = new CPFFormatter();

        if (cpfValidator.isEligible(doc)) {
            try {
                return cpfFormatter.format(doc);
            } catch (Exception e) {
                throw new Usuario.CpfException();
            }
        } else if (cnpjValidator.isEligible(doc)) {
            try {
                return cnpjFormatter.format(doc);
            } catch (Exception e) {
                throw new Usuario.CnpjException();
            }
        } else {
            throw new CpfCnpjException();
        }
    }

    /**
     *
     * @return
     */
    @Transient
    @JsonIgnore
    public String getDocumentoFormatado(){
        return formatDocumento(this.documento);
    }

    /**
     * @param login {String}
     * @return {String}
     */
    public static boolean loginIsADocumento(final String login) {
        if (login == null || login.length() < 1) {
            return false;
        }
        if (login.contains("@")) {
            return false;
        }

        final String doc = prepareDocumento(login);

        final CNPJValidator cnpjValidator = new CNPJValidator();
        final CPFValidator cpfValidator = new CPFValidator();

        return cpfValidator.isEligible(doc) || cnpjValidator.isEligible(doc);
    }

    /**
     * Valida o documento
     */
    public void validateDocumento() {
        this.documento = validateDocumento(this.documento);
    }

    /**
     *
     */
    public static class CpfException extends RuntimeException {
        /**
         *
         */
        private static final long serialVersionUID = -5267258386816809448L;

        CpfException() {
            super("CPF inválido!");
        }
    }

    /**
     *
     */
    public static class CnpjException extends RuntimeException {
        /**
         *
         */
        private static final long serialVersionUID = -4954516819219451502L;

        CnpjException() {
            super("CNPJ inválido!");
        }
    }

    /**
     *
     */
    public static class CpfCnpjException extends RuntimeException {
        /**
         *
         */
        private static final long serialVersionUID = -4954516329219451502L;

        CpfCnpjException() {
            super("CPF ou CNPJ inválido!");
        }
    }


}
