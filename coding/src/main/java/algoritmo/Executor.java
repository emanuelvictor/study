package algoritmo;

import algoritmo.genetico.AlgoritmoGenetico;
import algoritmo.genetico.Crossover;

import java.time.format.DateTimeFormatter;

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

        int num = 1000;

        long[] withRoulettePMX = new long[num];
        long[] withoutRoulettePMX = new long[num];

        long[] withRouletteOX = new long[num];
        long[] withoutRouletteOX = new long[num];


        for (int i = 0; i < num; i++) {
            final AlgoritmoGenetico pmx = new AlgoritmoGenetico(10, 20, 5, 100, false, Crossover.PMX);
            withoutRoulettePMX[i] = pmx.execute();

            final AlgoritmoGenetico ox = new AlgoritmoGenetico(10, 20, 5, 100, false, Crossover.PMX);
            withoutRouletteOX[i] = ox.execute();
        }

        for (int i = 0; i < num; i++) {
            final AlgoritmoGenetico pmx = new AlgoritmoGenetico(10, 20, 5, 100, true, Crossover.OX);
            withRoulettePMX[i] = pmx.execute();

            final AlgoritmoGenetico ox = new AlgoritmoGenetico(10, 20, 5, 100, true, Crossover.OX);
            withRouletteOX[i] = ox.execute();
        }

//        for (int i = 0; i < 10; i++) {
//            final AlgoritmoGenetico bruteForce = new AlgoritmoGenetico(10, 20, 100, 0);
//            final LocalDateTime initialDate = LocalDateTime.now();
//            brute[i] = bruteForce.execute();
//            final LocalDateTime finalDate = LocalDateTime.now();
//            System.out.println("Força Bruta " + i);
//            System.out.println("Início: " + formatter.format(initialDate) + " Término: " + formatter.format(finalDate));
//            bruteTime[i] = finalDate.compareTo(initialDate);
//        }

        for (int i = 0; i < num; i++) {
//            System.out.println("Brute force " + brute[i] + " bruteTime " + bruteTime[i]);
            System.out.println(withoutRoulettePMX[i] + ";" + withRoulettePMX[i] +";" + withoutRouletteOX[i] + ";" + withRouletteOX[i]);
        }

    }
}