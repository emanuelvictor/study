package com.emanuelvictor.java1.aula2.exemplo2;

public class Main {

    public static void main(final String args[]) {
        final Telefone telefone = new Telefone();

        telefone.setPreco(25);
        telefone.setCor("Amarelo");
        telefone.getPais().setCodPais(55);
        telefone.getPais().setNome("Brasil");

        System.out.println("\nPreço ...: " + telefone.getPreco());
        System.out.println("Cor .....: " + telefone.getCor());

        System.out.println("\nCódigo do pais ...: " + telefone.getPais().getCodPais());
        System.out.println("Nome do pais .....: " + telefone.getPais().getNome());
    }
}
