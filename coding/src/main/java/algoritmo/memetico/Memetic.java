package algoritmo.memetico;


import algoritmo.Matrix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Memetic {

    private int[][] matrix = new int[][]{{}};

    private int fitnessToFind;

    // Tamanho da população. O tamanho da população também define a aleatóriedade
    private int TAM_POP = 5;

    private int[] melhor;

    // Quantidade de cidades
    private int TAM_MATRIZ = 80;

    public Memetic() {
        this.matrix = Matrix.getInstance().getMatrix();
        this.fitnessToFind = Matrix.getInstance().getFitness();
    }

    public Memetic(int[][] matrix, int fitnessToFind) {
        this.matrix = matrix;
        this.fitnessToFind = fitnessToFind;
    }

    public void execute() {
        int[][] populacao = gerarPopulacaoAleatoria(TAM_POP, matrix);

        melhor = ordenar(populacao, calcularFitness(populacao, matrix))[0];

        //Variável auxiliar para guardar o melhor da população anterior anterior.
        int melhorAnterior = calcularFitness(melhor, matrix);

        while (!melhorGlobal(populacao, matrix, fitnessToFind)) {

            populacao = saltar(populacao, matrix);

            populacao = buscaLocal(populacao, matrix);

            //Variável auxiliar para não duplicar o processo de cálculo do fitness
            int melhorAtual = calcularFitness(melhor, matrix);

            //TODO gambiarrona para elitizar o melhor e o algoritmo não desaprender
            if (calcularFitness(ordenar(populacao, calcularFitness(populacao, matrix))[0], matrix) <= melhorAtual) {
                populacao = ordenar(populacao, calcularFitness(populacao, matrix));
                for (int i = 0; i < populacao[0].length; i++) {
                    melhor[i] = populacao[0][i];
                }
            } else {
                populacao = ordenar(populacao, calcularFitness(populacao, matrix));
                for (int i = 0; i < populacao[0].length; i++) {
                    populacao[0][i] = melhor[i];
                }
            }
            //Se o fitness do melhor indivíduo encontrado for melhor que o anterior, imprime-o
            if (melhorAtual < melhorAnterior) {
                melhorAnterior = calcularFitness(melhor, matrix);
                imprimir(ordenar(populacao, calcularFitness(populacao, matrix))[0], matrix);
//                System.out.println(" = " + calcularFitness(ordenar(populacao, calcularFitness(populacao, MATRIZ_ADJACENTE))[0], MATRIZ_ADJACENTE));
            }

        }

        imprimir(ordenar(populacao, calcularFitness(populacao, matrix))[0], matrix);

    }

    private static int[][] saltar(int[][] populacao, int[][] MATRIZ_ADJACENTE) {
        int[] melhor = ordenar(populacao, calcularFitness(populacao, MATRIZ_ADJACENTE))[0];
        populacao = ordenar(gerarPopulacaoAleatoria(populacao.length, MATRIZ_ADJACENTE), calcularFitness(populacao, MATRIZ_ADJACENTE));
        populacao[populacao.length - 1] = melhor;
//        melhor = rotate(melhor);
        return ordenar(populacao, calcularFitness(populacao, MATRIZ_ADJACENTE));
    }

    private static int[] rotate(final int[] chromosome) {
        for (int i = 0; i < chromosome.length - 1; i++) {
            final int first = chromosome[0];
            System.arraycopy(chromosome, 1, chromosome, 0, chromosome.length - 1);
            chromosome[chromosome.length - 1] = first;
        }
        return chromosome;
    }

    //TODO
    private static int[][] buscaLocal(int[][] population, int[][] matrix) {
//        do {
        // Select the dad
//            final int[] dad = population[0];
        int p = 0;
        /*for (int p = 0; p < population.length; p++)*/
        {

            //Percorrendo toda a população, TODO começa do 1 pq o zero é o dad
            for (int m = 1; m < population.length; m++) {
                //Percorrendo as ideias do pai
                for (int ip = 0; ip < population[p].length; ip++) {

                    //Percorrendo as ideias da mãe
                    for (int im = 0; im < population[m].length; im++) {

                        final int indexOfBestIdeaOfDad = rankIdeas(im, population[p], matrix);
                        final int indexOfBestIdeaOfMom = rankIdeas(im, population[m], matrix);

                        int fitnessOfBestIdeaOfDad = calcularFitness(population[p][indexOfBestIdeaOfDad], population[p][population[p].length - 1 == indexOfBestIdeaOfDad ? 0 : indexOfBestIdeaOfDad + 1], matrix); // Calculate the fitness of the mom
                        int fitnessOfBestIdeaOfMom = calcularFitness(population[m][indexOfBestIdeaOfMom], population[m][population[m].length - 1 == indexOfBestIdeaOfMom ? 0 : indexOfBestIdeaOfMom + 1], matrix); // Calculate the fitness of the dad
                        if (fitnessOfBestIdeaOfDad < fitnessOfBestIdeaOfMom) {
                            /*if (*/
                            trocarIdeia(population[m], population[p], indexOfBestIdeaOfDad);/*)*/
//                                    im--;
//                                    System.out.println(Arrays.toString(population[m]));

//                                    for (int k = 0; k < population[m].length; k++) {
//                                        if (population[m][k] == population[p][population[p].length - 1 == bestIdeaOfDad ? 0 : bestIdeaOfDad + 1]) {
//                                            population[m][k] = population[m][population[m].length - 1 == bestIdeaOfMom ? 0 : bestIdeaOfMom + 1];
////                                            break;
//                                        }
//                                    }
//                                    population[m][population[m].length - 1 == bestIdeaOfMom ? 0 : bestIdeaOfMom + 1] = population[p][population[p].length - 1 == bestIdeaOfDad ? 0 : bestIdeaOfDad + 1];
                        } else if (fitnessOfBestIdeaOfDad > fitnessOfBestIdeaOfMom) {
                            /*if (*/
                            trocarIdeia(population[p], population[m], indexOfBestIdeaOfMom);/*)*/
//                                    im--;
//                                    System.out.println(Arrays.toString(population[p]));
//                                    for (int k = 0; k < population[p].length; k++) {
//                                        if (population[p][k] == population[m][population[m].length - 1 == indexOfBestIdeaOfMom ? 0 : indexOfBestIdeaOfMom + 1]) {
//                                            population[p][k] = population[p][population[p].length - 1 == indexOfBestIdeaOfDad ? 0 : indexOfBestIdeaOfDad + 1];
////                                            break;
//                                        }
//                                    }
//                                    population[p][population[p].length - 1 == indexOfBestIdeaOfDad ? 0 : indexOfBestIdeaOfDad + 1] = population[m][population[m].length - 1 == indexOfBestIdeaOfMom ? 0 : indexOfBestIdeaOfMom + 1];

                        } else {
//                                System.out.println(Arrays.toString(population[p]));
//                                System.out.println(Arrays.toString(population[m]));
//                                System.out.println("ideias iguais "+ im);
                            continue;
                        }
                    }
                }
            }
        }
//        } while (!converge(population, matrix));
        return ordenar(population, calcularFitness(population, matrix));
    }

    public static boolean trocarIdeia(final int[] mom, final int[] dad, final int indexOfBestIdeaOfDad) {
        for (int k = 0; k < mom.length; k++) {

            if (mom[k] == dad[indexOfBestIdeaOfDad]) {

                // A troca de ideias deve fazer diferença no cromossomo
                if (mom[k == mom.length - 1 ? 0 : k + 1] == dad[dad.length - 1 == indexOfBestIdeaOfDad ? 0 : indexOfBestIdeaOfDad + 1])
                    return false;

                for (int i = 0; true; i++)
                    if (mom[i] == dad[dad.length - 1 == indexOfBestIdeaOfDad ? 0 : indexOfBestIdeaOfDad + 1]) {
                        mom[i] = mom[k == mom.length - 1 ? 0 : k + 1];
                        break;
                    }

                mom[k == mom.length - 1 ? 0 : k + 1] = dad[dad.length - 1 == indexOfBestIdeaOfDad ? 0 : indexOfBestIdeaOfDad + 1];

                break;
            }
        }
        return true;
    }

    public static boolean converge(int[][] populacao, int[][] matrix) {
        int[] fitness = calcularFitness(populacao, matrix);
        for (int i = 1; i < fitness.length; i++) {
            if (fitness[i] != fitness[i - 1]) return false;
        }
        return true;
    }

    private static boolean converge(final int[][] population) {
//        for (int i = 0; i < populacao.length; i++) {
//            int count = 0;
//            for (int j = 0; j < populacao.length; j++) {
//                if (i != j) {
//                    if (Arrays.equals(populacao[i], populacao[j]))
//                        count++;
//                    if (count >= (populacao.length * convergenceRate) / 100)
//                        return true;
//                }
//            }
//        }
//        return false;
        for (final int[] outer : population) {
            for (final int[] inner : population) {
                if (!Arrays.equals(outer, inner))
                    return false;
            }
        }
        return true;
    }

    /**
     * @param position   int position of the rank
     * @param chromosome int[] individual to rank
     * @param matrix     int[][] matrix
     * @return int Return the position of the ranked meme
     */
    private static int rankIdeas(final int position, final int[] chromosome, final int[][] matrix) {

        final HashMap<Integer, Integer> hashMap = new HashMap<>();
        final int[] fitness = new int[chromosome.length];

        for (int i = 0; i < chromosome.length; i++) {
            fitness[i] = calcularFitness(chromosome[i], chromosome[i == chromosome.length - 1 ? 0 : i + 1], matrix);
            hashMap.put(fitness[i], i);
        }

        Arrays.sort(fitness);

        return hashMap.get(fitness[position]);
    }

    private static boolean melhorGlobal(int[][] populacao, int[][] MATRIZ_ADJACENTE, int FITNESS) {
        return calcularFitness(populacao, MATRIZ_ADJACENTE)[0] <= FITNESS;
    }

    private static int[][] gerarPopulacaoAleatoria(int TAM_POP, int[][] MATRIZ_ADJACENTE) {

        int[][] rotas = new int[TAM_POP][MATRIZ_ADJACENTE.length];

        //Inicializando população inicial
        for (int k = 0; k < TAM_POP; k++) {

            int[] rotaAux = new int[MATRIZ_ADJACENTE.length];
            for (int i = 0; i < MATRIZ_ADJACENTE.length; i++) {
                rotaAux[i] = i;
            }

            int[] rota = new int[rotaAux.length];

            rota = embaralha(rota.length, rota, rotaAux);

            if (!estaContido(rotas, rota)) {
                rotas[k] = rota;
            } else {
                k--;
            }
        }

        return ordenar(rotas, calcularFitness(rotas, MATRIZ_ADJACENTE));
    }

    private static int[] embaralha(int tam, int[] rota, int[] rotaAux) {
        // Embaralha
        for (int i = 0; i < tam; i++) {
            int r;
            do {
                r = new Random().nextInt(tam - i);
            } while (r == i);

            rota[i] = rotaAux[r];
            rotaAux = preencheVetorSemOR(rotaAux, r);
        }
        return rota;
    }

    private static int[] preencheVetorSemOR(final int[] vetorDeIndices, final int r) {
        int[] vetAux = new int[vetorDeIndices.length - 1];
        int cont = 0;
        for (int j = 0; j < vetorDeIndices.length; j++) {
            if (j == r) {
                continue;
            }
            vetAux[cont] = vetorDeIndices[j];
            cont++;
        }
        return vetAux;
    }

    private static int[] calcularFitness(int[][] populacao, int[][] MATRIZ_ADJACENTE) {
        int[] fitness = new int[populacao.length];
        for (int i = 0; i < populacao.length; i++) {
            fitness[i] = calcularFitness(populacao[i], MATRIZ_ADJACENTE);
        }
        return fitness;
    }

    private static int calcularFitness(int[] individuo, int[][] MATRIZ_ADJACENTE) {
        int fitness = 0;
        for (int j = 0; j < individuo.length; j++) {
            fitness = fitness + MATRIZ_ADJACENTE[individuo[j]][individuo[j == individuo.length - 1 ? 0 : j + 1]];
        }
        return fitness;
    }

    private static int calcularFitness(int cidade1, int cidade2, int[][] MATRIZ_ADJACENTE) {
        return MATRIZ_ADJACENTE[cidade1][cidade2];
    }

    private static int[][] ordenar(int[][] populacao, int[] fitness) {
        // ordenando
        int i, i2;
        for (i = 0; i < populacao.length; i++) {
            for (i2 = i; i2 < populacao.length; i2++) {
                if (fitness[i] > fitness[i2]) {
                    int vTmp = fitness[i];
                    fitness[i] = fitness[i2];
                    fitness[i2] = vTmp;

                    int[] vvTmp = populacao[i];
                    populacao[i] = populacao[i2];
                    populacao[i2] = vvTmp;
                }
            }
        }
        return populacao;
    }

    private static boolean estaContido(int[][] populacao, int[] individuo) {
        for (int[] auxiliar : populacao) {
            if (Arrays.equals(auxiliar, individuo)) {
                return true;
            }
        }
        return false;
    }

//    private static void imprimir(int[][] populacao, int[] fitness, int[][] MATRIZ_ADJACENTE) {
//        for (int j = 0; j < populacao.length; j++) {
//            System.out.print("Rota " + j + " = ");
//            for (int i = 0; i < MATRIZ_ADJACENTE.length; i++) {
//                System.out.print(" " + populacao[j][i] + " ");
//            }
//            System.out.println(" fitness = " + fitness[j]);
//        }
//    }

    private void imprimir(int[] rota, int[][] matrix) {
        for (int j : rota) {
            System.out.print(" " + j);
        }
        System.out.println(" = " + calcularFitness(rota, matrix) + " fitness a ser encontrado = " + fitnessToFind);
    }
}