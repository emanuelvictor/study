package br.org.pti.integrador.domain.entities.dto;

import br.org.pti.integrador.application.formatters.ParametroFormatter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Optional;

/**
 * 
 * @param <T> 
 * 
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 18/08/2017
 */
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParametroDTO<T> implements Serializable {

    @Setter
    @Getter
    @JsonProperty("CONTEUDO")
    private String conteudo;

    @JsonIgnore
    private Optional<ParametroFormatter<T>> formatter;
    
    /**
     * 
     */
    public ParametroDTO() { 
        this.formatter = Optional.empty();
    }

    /**
     * @return 
     */
    public T applyFormat() {
        return this.formatter
                .orElseThrow(() -> 
                        new IllegalStateException("Formatter is not present"))
                .format(this.conteudo);
    }

    /**
     * 
     * @param formatter 
     */
    public void setFormatter(ParametroFormatter<T> formatter) {
        this.formatter = Optional.ofNullable(formatter);
    }
}
