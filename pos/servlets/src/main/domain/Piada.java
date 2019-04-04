package main.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Piada {
    @Id
    @GeneratedValue
    private long id;

    private String conteudo;

    public Piada() {
    }

    public Piada(final String conteudo) {
        this.conteudo = conteudo;
    }

    public Piada(final long id, final String conteudo) {
        this.id = id;
        this.conteudo    = conteudo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
