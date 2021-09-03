package algoritmo;

import algoritmo.memetico.Memetic;
import algoritmo.memetico.OldMemetic;

/**
 * Algoritmo Genético implementado por Emanuel Victor e Haroldo Ramirez em 29/09/14.
 * Análise da influencia da mutação nos crossovers OX e PMX.
 * Classe que executa os testes
 */
public class Executor {

    public static void main(String[] args) {
        final Memetic memetic = new Memetic(10);
        memetic.execute();

//        final OldMemetic oldMemetic = new OldMemetic(40);
//        oldMemetic.execute();


        System.out.println(" ----------------------------------------------------------------------------------------------------- ");

        //        final AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(
//                Integer.parseInt(Leitura.inDados("Insira a quantidade de cidades: ")),
//                Integer.parseInt(Leitura.inDados("Defina o tamanho da população: ")),
//                Float.parseFloat(Leitura.inDados("Taxa de mutação: "))
//        );

//        final AlgoritmoGenetico genetic = new AlgoritmoGenetico(20, 50, 25, 100, 85, false, Crossover.PMX);
//        genetic.execute();

//        // Brute force
//        final AlgoritmoGenetico genetic = new AlgoritmoGenetico(10, 20, 100, 0, 85, false, Crossover.PMX);
//        genetic.execute();

    }
}