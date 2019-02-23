package com.emanuelvictor.java1.aula3.exemplo1;


// Uma classe que descreve um objeto do mundo real, é uma classe Entidade.
public class Telefone {

    private int preco;// Atributos
    private String cor; // Atributos
    private Pais pais; // Atributos

    public Telefone() {
        preco = 0; // Atributos
        cor = ""; // Atributos
        pais = new Pais();
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(final int preco) {
        this.preco = preco;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(final String cor) {
        this.cor = cor;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public void impDados() {
        System.out.println("\n\t Estou no impDados de TELEFONE");
    }
}
