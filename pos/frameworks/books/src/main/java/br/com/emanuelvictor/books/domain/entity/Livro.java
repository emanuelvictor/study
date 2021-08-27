package br.com.emanuelvictor.books.domain.entity;

import br.com.emanuelvictor.books.domain.entity.generic.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@Entity
@Audited
@EqualsAndHashCode(callSuper = true)
public class Livro extends AbstractEntity implements Serializable {

    /**
     *
     */
    @NotEmpty
    @NotNull
    @Length(max = 100)
    @Column(length = 100, nullable = false, unique = true)
    private String nome;

    /**
     *
     */
    @JoinColumn(name = "editora_id")
    @ManyToOne(optional = false, /*cascade = CascadeType.ALL,*/ fetch = FetchType.EAGER)
    private Editora editora;
}
