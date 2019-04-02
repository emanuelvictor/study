package com.emanuelvictor.java1.exercicios.exercicios5.infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Emanuel Victor De Oliveira Fonseca
 */
public class Leitura {

    private static final InputStreamReader teclado = new InputStreamReader(System.in);
    private static final BufferedReader mem = new BufferedReader(teclado);

    public static String inDados(final String rotulo) {

        final String entrada;

        System.out.println(rotulo);
        try {
            entrada = mem.readLine();
        } catch (IOException e) {
            System.err.println("\n Erro no sistema");
            return "";
        }

        clearConsole();

        return entrada;
    }

    public static int trataInDadosNumericos(final String entrada) {
        if (Objects.equals(entrada, "") || entrada == null || (!entrada.equals("1") && !entrada.equals("2") && !entrada.equals("3") && !entrada.equals("4") && !entrada.equals("5") && !entrada.equals("6") && !entrada.equals("7") && !entrada.equals("8") && !entrada.equals("9")))
            return 0;
        else
            return Integer.parseInt(entrada);
    }

    private static void clearConsole() {
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ignored) {
        }
    }
}

