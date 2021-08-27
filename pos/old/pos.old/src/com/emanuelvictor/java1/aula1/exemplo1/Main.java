package com.emanuelvictor.java1.aula1.exemplo1;


public class Main {

    public static void main(String[] args) {
        // Exemplo 1
        final Pessoa pessoa = new Pessoa();
        pessoa.setId(1);
        pessoa.setNome("Jesus");

        System.out.println("\n Id .....:" + pessoa.getId());
        System.out.println("\n Nome ...:" + pessoa.getNome());
    }
}
