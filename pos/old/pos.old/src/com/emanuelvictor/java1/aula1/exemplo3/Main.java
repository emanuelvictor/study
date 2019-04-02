package com.emanuelvictor.java1.aula1.exemplo3;

import com.emanuelvictor.java1.aula1.exemplo2.Leitura;

public class Main {

    public static void main(String[] args) {
        // Exemplo 2
        final Pessoa pessoa = new Pessoa();

        pessoa.setId(Integer.parseInt(Leitura.inDados("Informe o id: ")));

        pessoa.setNome(Leitura.inDados("Informe o nome: "));

        System.out.println("\n Id .....:" + pessoa.getId());
        System.out.println("\n Nome ...:" + pessoa.getNome());

        pessoa.calcString();
        pessoa.calcNumero();
    }
}
