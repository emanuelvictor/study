package algoritmo;

import algoritmo.memetico.AlgoritmoMemetico;

/**
 * Algoritmo Genético implementado por Emanuel Victor e Haroldo Ramirez em 29/09/14.
 * Análise da influencia da mutação nos crossovers OX e PMX.
 * Classe que executa os testes
 */

public class Executor {

    public static void main(String[] args) {
        AlgoritmoMemetico algoritmoMemetico = new AlgoritmoMemetico();
        algoritmoMemetico.execute();
    }

}