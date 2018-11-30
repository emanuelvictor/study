package algoritmo;

import algoritmo.genetico.AlgoritmoGenetico;
import algoritmo.memetico.AlgoritmoMemetico;

/**
 * Algoritmo Genético implementado por Emanuel Victor e Haroldo Ramirez em 29/09/14.
 * Análise da influencia da mutação nos crossovers OX e PMX.
 * Classe que executa os testes
 */

public class Executor {

    public static void main(String[] args) {
//        final AlgoritmoMemetico algoritmoMemetico = new AlgoritmoMemetico(Integer.parseInt(Leitura.inDados("Insira a quantidade de cidades: ")));
//        algoritmoMemetico.execute();


        final AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(Integer.parseInt(Leitura.inDados("Insira a quantidade de cidades: ")), Integer.parseInt(Leitura.inDados("Defina o tamanho da população: ")));
        algoritmoGenetico.execute();
    }

}