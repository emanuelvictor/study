package algoritmo;

import algoritmo.genetico.AlgoritmoGenetico;
import algoritmo.genetico.Crossover;

/**
 * Algoritmo Genético implementado por Emanuel Victor e Haroldo Ramirez em 29/09/14.
 * Análise da influencia da mutação nos crossovers OX e PMX.
 * Classe que executa os testes
 */

public class Executor {

    public static void main(String[] args) {
//        final AlgoritmoMemetico algoritmoMemetico = new AlgoritmoMemetico(Integer.parseInt(Leitura.inDados("Insira a quantidade de cidades: ")));
//        algoritmoMemetico.execute();


//        final AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(
//                Integer.parseInt(Leitura.inDados("Insira a quantidade de cidades: ")),
//                Integer.parseInt(Leitura.inDados("Defina o tamanho da população: ")),
//                Float.parseFloat(Leitura.inDados("Taxa de mutação: "))
//        );


        final AlgoritmoGenetico genetic = new AlgoritmoGenetico(16, 20, 25, 100, 85, Crossover.PMX);
        genetic.setWithRoulette(false);
        genetic.execute();


    }
}