package com.emanuelvictor.java1.aula2.exemplo1.meu;

import com.emanuelvictor.java1.aula1.exemplo2.Leitura;
import com.emanuelvictor.java1.aula2.exemplo1.Telefone;

// Meu exemplo, lendo os atrituso do teclado.
// Utiliza a classe "Leitura", criada no exemplo2 da primeira aula
public class Main {
    public static void main(String arg[]) {

        final Telefone telefone = new Telefone();

        telefone.entDados(Integer.parseInt(Leitura.inDados("Informe o preço: ")), Leitura.inDados("Informe o cor: "));

    }
}
