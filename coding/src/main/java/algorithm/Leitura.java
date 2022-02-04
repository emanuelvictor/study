package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Leitura {

    private static final InputStreamReader teclado = new InputStreamReader(System.in);
    private static final BufferedReader mem = new BufferedReader(teclado);

    public static String inDados(final String rotulo){


        final String entrada;

        System.out.println(rotulo);
        try {
            entrada = mem.readLine();
        } catch (IOException e) {
            System.err.println("\n Erro no sistema");
            return "";
        }
        return entrada;
    }
}
