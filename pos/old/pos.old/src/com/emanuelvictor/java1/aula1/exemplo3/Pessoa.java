package com.emanuelvictor.java1.aula1.exemplo3;

class Pessoa implements ComputId, ComputNome {

    private int id;
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void calcNumero() {
        System.out.println("\n Método calcNumero() da interface ComputId");
    }

    @Override
    public void calcString() {
        System.out.println("\n Método calcString() da interface ComputNome");
    }
}
