package algorithm;

import algorithm.memetico.Memetic;
import algorithm.memetico.OldMemetic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Algoritmo Genético implementado por Emanuel Victor e Haroldo Ramirez em 29/09/14.
 * Análise da influencia da mutação nos crossovers OX e PMX.
 * Classe que executa os testes
 */
public class Executor {

    public static void main(String[] args) {

        Matrix.getInstance().generateMatrix(20, true);

        final OldMemetic oldMemetic = new OldMemetic();
        final Memetic memetic = new Memetic();

        final ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(memetic::execute);
        executor.execute(oldMemetic::execute);

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