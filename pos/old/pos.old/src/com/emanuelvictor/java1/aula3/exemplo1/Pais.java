package com.emanuelvictor.java1.aula3.exemplo1;

public class Pais {
    private int codPais; // Atributos
    private String nome; // Atributos

    public Pais() {
        codPais = 0;
        nome = "";
    }

    public int getCodPais() {
        return codPais;
    }

    public void setCodPais(final int codPais) {
        this.codPais = codPais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }
}
