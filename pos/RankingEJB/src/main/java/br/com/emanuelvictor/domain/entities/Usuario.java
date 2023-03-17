package br.com.emanuelvictor.domain.entities;

import lombok.Getter;
import lombok.Setter;

public class Usuario {

    @Setter
    @Getter
    private String nome;

    @Getter
    private int pontuacao = 0;

    public Usuario() {
    }

    public Usuario(String nome) {
        this.nome = nome;
    }

    public void incrementarPontuacao() {
        pontuacao++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return nome.equals(usuario.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}
