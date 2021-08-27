package com.emanuelvictor.java1.aula3.exemplo1;

public class Main {

    public static void main(final String args[]) {
        final Celular celular = new Celular();

        celular.setPreco(25);
        celular.setCor("Amarelo");
        celular.setNumTorres(150);
        celular.getPais().setCodPais(55);
        celular.getPais().setNome("Brasil");

        System.out.println("Torres ..: " + celular.getNumTorres());

        System.out.println("\nPreço ...: " + celular.getPreco());
        System.out.println("Cor .....: " + celular.getCor());

        System.out.println("\nPais ..: " + celular.getPais().getNome());
        System.out.println("Código pais ..: " + celular.getPais().getCodPais());

        celular.impDados();

    }
}
