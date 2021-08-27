package br.com.emanuelvictor.books.domain.entity;

import br.com.emanuelvictor.books.domain.entity.generic.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Audited
@EqualsAndHashCode(callSuper = true)
public class Editora extends AbstractEntity implements Serializable {

    /**
     *
     */
    @NotEmpty
    @NotNull
    @Length(max = 100)
    @Column(length = 100, nullable = false, unique = true)
    private String nome;
}
