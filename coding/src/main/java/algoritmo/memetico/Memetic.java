package algoritmo.memetico;


import algoritmo.Matrix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Memetic {

    private int[][] matrix = new int[][]{{}};

    private final int fitnessToFind;

    public Memetic() {
        this.matrix = Matrix.getInstance().getMatrix();
        this.fitnessToFind = Matrix.getInstance().getFitness();
    }

    public Memetic(final int[][] matrix, final int fitnessToFind) {
        this.matrix = matrix;
        this.fitnessToFind = fitnessToFind;
    }

    public void execute() {
        // Tamanho da população. O tamanho da população também define a aleatóriedade
        int TAM_POP = 2;
        int[][] population = generateRandomPopulation(TAM_POP, matrix);

        int[] best = sort(population, calculateFitness(population, matrix))[0];

        //Variável auxiliar para guardar o melhor da população anterior anterior.
        int melhorAnterior = calculateFitness(best, matrix);

        while (!melhorGlobal(population, matrix, fitnessToFind)) {

            population = jump(population, matrix);

            population = learn(population, matrix);

            //Variável auxiliar para não duplicar o processo de cálculo do fitness
            int melhorAtual = calculateFitness(best, matrix);

            //TODO gambiarrona para elitizar o melhor e o algoritmo não desaprender
            if (calculateFitness(sort(population, calculateFitness(population, matrix))[0], matrix) <= melhorAtual) {
                population = sort(population, calculateFitness(population, matrix));
                for (int i = 0; i < population[0].length; i++) {
                    best[i] = population[0][i];
                }
            } else {
                population = sort(population, calculateFitness(population, matrix));
                for (int i = 0; i < population[0].length; i++) {
                    population[0][i] = best[i];
                }
            }
            //Se o fitness do melhor indivíduo encontrado for melhor que o anterior, imprime-o
            if (melhorAtual < melhorAnterior) {
                melhorAnterior = calculateFitness(best, matrix);
//                imprimir(sort(population, calculateFitness(population, matrix))[0], matrix);
//                System.out.println(" = " + calcularFitness(ordenar(populacao, calcularFitness(populacao, MATRIZ_ADJACENTE))[0], MATRIZ_ADJACENTE));
            }

        }

//        imprimir(sort(population, calculateFitness(population, matrix))[0], matrix);
        System.out.println("memetic done");
    }

    private static int[][] jump(final int[][] population, final int[][] matrix) {
        int[] bestChromosome = sort(population, calculateFitness(population, matrix))[0];
        final int[][] newPopulation = generateRandomPopulation(population.length, matrix);
        newPopulation[newPopulation.length - 1] = bestChromosome;
        return sort(newPopulation, calculateFitness(newPopulation, matrix));
    }

    public static int[][] learn(int[][] population, int[][] matrix) {

//        int[] fitness = calculateFitness(population, matrix);

//        sort(population, fitness);

        final int m = 0; //roulette(fitness);

        //Percorrendo toda a população
        for (int n = 0; n < population.length; n++) {
            for (int i = 1; i < population.length; i++) {
                //Percorrendo o mellhor
                for (int c = 0; c < population[m].length - 1; c++) {
                    //Percorrendo o indivíduo da população
                    final int custoA = calculateFitness(population[m][c], population[m][c + 1], matrix);
                    for (int j = 0; j < population[i].length - 1; j++) {
                        if (population[i][j] == population[m][c]) {
                            final int custoB = calculateFitness(population[i][j], population[i][j + 1], matrix);
                            if (custoA < custoB) {
                                for (int k = 0; k < population[i].length; k++) {
                                    if (population[i][k] == population[m][c + 1]) {
                                        population[i][k] = population[i][j + 1];
                                        break;
                                    }
                                }
                                population[i][j + 1] = population[m][c + 1];
                            } else if (custoB < custoA) {
                                for (int k = 0; k < population[m].length; k++) {
                                    if (population[m][k] == population[i][j + 1]) {
                                        population[m][k] = population[m][c + 1];
                                        break;
                                    }
                                }
                                population[m][c + 1] = population[i][j + 1];
                            }
                            break;
                        }
                    }
                }
            }
        }
        return sort(population, calculateFitness(population, matrix));
    }

//    private static int[] rotate(final int[] chromosome) {
//        for (int i = 0; i < chromosome.length - 1; i++) {
//            final int first = chromosome[0];
//            System.arraycopy(chromosome, 1, chromosome, 0, chromosome.length - 1);
//            chromosome[chromosome.length - 1] = first;
//        }
//        return chromosome;
//    }
//
//    private static int[][] oldLearn(final int[][] population, final int[][] matrix) {
//
//        int[] fitness = calculateFitness(population, matrix);
//
//        sort(population, fitness);
//
//        final int p = AlgoritmoGenetico.roulette(fitness);
//        //Percorrendo toda a população,
//        for (final int[] moms : population) {
//            //Percorrendo as ideias do pai
//            for (int ip = 0; ip < population[p].length; ip++) {
//
//                //Percorrendo as ideias da mãe
//                for (int im = 0; im < moms.length; im++) {
//
//                    final int indexOfBestIdeaOfDad = rankIdeas(im, population[p], matrix);
//                    final int indexOfBestIdeaOfMom = rankIdeas(im, moms, matrix);
//
//                    int fitnessOfBestIdeaOfDad = calculateFitness(population[p][indexOfBestIdeaOfDad], population[p][population[p].length - 1 == indexOfBestIdeaOfDad ? 0 : indexOfBestIdeaOfDad + 1], matrix); // Calculate the fitness of the mom
//                    int fitnessOfBestIdeaOfMom = calculateFitness(moms[indexOfBestIdeaOfMom], moms[moms.length - 1 == indexOfBestIdeaOfMom ? 0 : indexOfBestIdeaOfMom + 1], matrix); // Calculate the fitness of the dad
//
//                    if (fitnessOfBestIdeaOfDad < fitnessOfBestIdeaOfMom) {
//                        learn(moms, population[p], indexOfBestIdeaOfDad);
//                    } else if (fitnessOfBestIdeaOfDad > fitnessOfBestIdeaOfMom) {
//                        learn(population[p], moms, indexOfBestIdeaOfMom);
//                    }
//
//                }
//            }
//        }
//        return sort(population, calculateFitness(population, matrix));
//    }

//    public static void learn(final int[] mom, final int[] dad, final int indexOfBestIdeaOfDad) {
//        for (int k = 0; k < mom.length; k++) {
//
//            if (mom[k] == dad[indexOfBestIdeaOfDad]) {
//
//                // A troca de ideias deve fazer diferença no cromossomo
//                if (mom[k == mom.length - 1 ? 0 : k + 1] == dad[dad.length - 1 == indexOfBestIdeaOfDad ? 0 : indexOfBestIdeaOfDad + 1])
//                    return;
//
//                for (int i = 0; true; i++)
//                    if (mom[i] == dad[dad.length - 1 == indexOfBestIdeaOfDad ? 0 : indexOfBestIdeaOfDad + 1]) {
//                        mom[i] = mom[k == mom.length - 1 ? 0 : k + 1];
//                        break;
//                    }
//
//                mom[k == mom.length - 1 ? 0 : k + 1] = dad[dad.length - 1 == indexOfBestIdeaOfDad ? 0 : indexOfBestIdeaOfDad + 1];
//
//                break;
//            }
//        }
//    }
//
//    public static boolean converge(int[][] populacao, int[][] matrix) {
//        int[] fitness = calculateFitness(populacao, matrix);
//        for (int i = 1; i < fitness.length; i++) {
//            if (fitness[i] != fitness[i - 1]) return false;
//        }
//        return true;
//    }

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
            fitness[i] = calculateFitness(chromosome[i], chromosome[i == chromosome.length - 1 ? 0 : i + 1], matrix);
            hashMap.put(fitness[i], i);
        }

        Arrays.sort(fitness);

        return hashMap.get(fitness[position]);
    }

    private static boolean melhorGlobal(int[][] populacao, int[][] MATRIZ_ADJACENTE, int FITNESS) {
        return calculateFitness(populacao, MATRIZ_ADJACENTE)[0] <= FITNESS;
    }

    private static int[][] generateRandomPopulation(int TAM_POP, int[][] matrix) {

        int[][] rotas = new int[TAM_POP][matrix.length];

        //Inicializando população inicial
        for (int k = 0; k < TAM_POP; k++) {

            int[] rotaAux = new int[matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                rotaAux[i] = i;
            }

            int[] rota = new int[rotaAux.length];

            embaralha(rota.length, rota, rotaAux);

            if (!estaContido(rotas, rota)) {
                rotas[k] = rota;
            } else {
                k--;
            }
        }

        return sort(rotas, calculateFitness(rotas, matrix));
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

    private static int[] calculateFitness(final int[][] populacao, final int[][] MATRIZ_ADJACENTE) {
        int[] fitness = new int[populacao.length];
        for (int i = 0; i < populacao.length; i++) {
            fitness[i] = calculateFitness(populacao[i], MATRIZ_ADJACENTE);
        }
        return fitness;
    }

    private static int calculateFitness(final int[] chromosome, final int[][] MATRIZ_ADJACENTE) {
        int fitness = 0;
        for (int j = 0; j < chromosome.length; j++) {
            fitness = fitness + MATRIZ_ADJACENTE[chromosome[j]][chromosome[j == chromosome.length - 1 ? 0 : j + 1]];
        }
        return fitness;
    }

    private static int calculateFitness(int cidade1, int cidade2, int[][] MATRIZ_ADJACENTE) {
        return MATRIZ_ADJACENTE[cidade1][cidade2];
    }

    private static int[][] sort(final int[][] population, final int[] fitness) {
        // ordenando
        int i, i2;
        for (i = 0; i < population.length; i++) {
            for (i2 = i; i2 < population.length; i2++) {
                if (fitness[i] > fitness[i2]) {
                    int vTmp = fitness[i];
                    fitness[i] = fitness[i2];
                    fitness[i2] = vTmp;

                    int[] vvTmp = population[i];
                    population[i] = population[i2];
                    population[i2] = vvTmp;
                }
            }
        }
        return population;
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
        System.out.println(" = " + calculateFitness(rota, matrix) + " fitness a ser encontrado = " + fitnessToFind);
    }
}