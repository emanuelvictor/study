package algoritmo;

import algoritmo.genetico.AlgoritmoGenetico;
import algoritmo.memetico.AlgoritmoMemetico;

import java.time.LocalDateTime;
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


        long [] brute = new long[10];
        long [] bruteTime = new long[10];
        long [] heu = new long[10];
        long [] heuTime = new long[10];

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        for (int i = 0; i < 10; i++) {
            final AlgoritmoGenetico heuristc = new AlgoritmoGenetico(10, 20, 5, 100);
            final LocalDateTime initialDate = LocalDateTime.now();
            heu[i] = heuristc.execute();
            final LocalDateTime finalDate = LocalDateTime.now();
            System.out.println("EURISTICO " + i);
            System.out.println("Início: " + formatter.format(initialDate) + " Término: " + formatter.format(finalDate));
            heuTime[i] = finalDate.compareTo(initialDate);
        }

        for (int i = 0; i < 10; i++) {
            final AlgoritmoGenetico bruteForce = new AlgoritmoGenetico(10, 20, 100, 0);
            final LocalDateTime initialDate = LocalDateTime.now();
            brute[i] = bruteForce.execute();
            final LocalDateTime finalDate = LocalDateTime.now();
            System.out.println("Força Bruta " + i);
            System.out.println("Início: " + formatter.format(initialDate) + " Término: " + formatter.format(finalDate));
            bruteTime[i] = finalDate.compareTo(initialDate);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("Brute force " + brute[i] + " bruteTime " + bruteTime[i]);
            System.out.println("Heuristc " + heu[i] + " heuTime " + heuTime[i]);
        }
    }
}