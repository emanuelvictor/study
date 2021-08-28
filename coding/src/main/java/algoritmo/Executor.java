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


//        final AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(
//                Integer.parseInt(Leitura.inDados("Insira a quantidade de cidades: ")),
//                Integer.parseInt(Leitura.inDados("Defina o tamanho da população: ")),
//                Float.parseFloat(Leitura.inDados("Taxa de mutação: "))
//        );


        int [] brute = new int[10];
        int [] heu = new int[10];
        for (int i = 0; i < 10; i++) {
            final AlgoritmoGenetico heuristc = new AlgoritmoGenetico(10, 20, 5, 100);
            heu[i] = heuristc.execute();
        }
//        for (int i = 0; i < 10; i++) {
//            final AlgoritmoGenetico bruteForce = new AlgoritmoGenetico(10, 20, 100, 0);
//            brute[i] = bruteForce.execute();
//        }


//        for (int i = 0; i < 10; i++) {
//            System.out.println("Brute force " + brute[i] + " heu " + heu[i]);
//        }

    }

}