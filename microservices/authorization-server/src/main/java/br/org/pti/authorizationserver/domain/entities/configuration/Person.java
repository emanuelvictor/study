package br.org.pti.authorizationserver.domain.entities.configuration;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.org.pti.authorizationserver.domain.entities.PersistentEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.regex.Pattern;


/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@Entity
@Audited
@JsonIgnoreProperties({"authorities"})
@lombok.EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
public class Person extends PersistentEntity {

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
    private static String prepareDocumento(String documento) {
        if (documento == null) {
            return null;
        }
        if (documento.contains("@")) {
            return documento;
        }

        documento = documento.replaceAll(Pattern.quote("."), "");
        documento = documento.replaceAll(Pattern.quote("/"), "");
        return documento.replaceAll(Pattern.quote("-"), "");
    }

    /**
     * @param documento {String}
     * @return {String}
     */
    public static String validateDocumento(final String documento) {
        if (documento == null || documento.length() < 1) {
            return null;
        }
        if (documento.contains("@")) {
            return documento;
        }

        final String doc = Person.prepareDocumento(documento);

        final CNPJValidator cnpjValidator = new CNPJValidator();
        final CPFValidator cpfValidator = new CPFValidator();


        if (cpfValidator.isEligible(doc)) {
            try {
                cpfValidator.assertValid(doc);
            } catch (Exception e) {
                throw new User.CpfException();
            }
        } else if (cnpjValidator.isEligible(doc)) {
            try {
                cnpjValidator.assertValid(doc);
            } catch (Exception e) {
                throw new User.CnpjException();
            }
        } else {
            throw new CpfCnpjException();
        }
        return doc;
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

        CpfException() {
            super("CPF inválido!");
        }
    }

    /**
     *
     */
    public static class CnpjException extends RuntimeException {

        CnpjException() {
            super("CNPJ inválido!");
        }
    }

    /**
     *
     */
    public static class CpfCnpjException extends RuntimeException {

        CpfCnpjException() {
            super("CPF ou CNPJ inválido!");
        }
    }


}
