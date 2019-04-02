package com.emanuelvictor.java1.aula2.exemplo1;

public class Telefone {

    private int preco = 0;
    private String cor = "";

    public void entDados(int preco, String cor) {
        // Regra de negócio, o preço não pode ser menor que 10.
        // Se o preço for menor que dez avisa o usuário
        if (preco >= 10)
            this.preco = preco;
        else
            throw new RuntimeException("\n O valor deve ser maior que 10");
        this.cor = cor;
    }

    // Sobrecarga de métdodos
    void entDados() {
        entDados(10, "Preto");
    }

    public void saiDados() {
        System.out.println("\n Preço ...: " + preco);
        System.out.println("\n Cor .....: " + cor);
    }

}
