package main.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cidade {
    @Id
    @GeneratedValue
    private long id;

    private String nome;

    public Cidade() {
    }

    public Cidade(final String nome) {
        this.nome = nome;
    }

    public Cidade(final long id, final String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String conteudo) {
        this.nome = conteudo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
