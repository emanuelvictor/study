package modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@NamedQuery(name="Autor.findAll", query="select a from Autor a")
public class Autor {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    @ManyToMany
    private Collection<Livro> livros = new ArrayList<Livro>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Collection<Livro> getLivros() {
        return livros;
    }

    public void setLivros(Collection<Livro> livros) {
        this.livros = livros;
    }
}
