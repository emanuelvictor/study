

package algoritmo.genetico;


import algoritmo.Matrix;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Random;

/**
 * Algoritmo Genético implementado por Emanuel Victor e Haroldo Ramirez em 29/09/14.
 * Análise da influencia da mutação nos crossovers OX e PMX.
 * Algoritmo
 */
public class AlgoritmoGenetico {

    private final int[][] matrix;
    private final int sizePopulation;
    private final float txMutation;
    private final float txCrossover;
    private final float txElitism;
    private final int fitnessToFind;
    private boolean withRoulette = true;
    private final Crossover crossover;

    private long generations = 1;

    public AlgoritmoGenetico(int sizeMatrix, int sizePopulation, float txMutation, int txCrossover, final float txElitism, final boolean withRoulette, final Crossover crossover) {
        this.matrix = Matrix.getInstance().getMatrix();
        this.fitnessToFind = Matrix.getInstance().getFitness();
        this.sizePopulation = sizePopulation;
        this.txMutation = txMutation;
        this.txCrossover = txCrossover;
        this.withRoulette = withRoulette;
        this.crossover = crossover;
        this.txElitism = txElitism;
    }

    public AlgoritmoGenetico(int sizeMatrix, int sizePopulation, float txMutation, int txCrossover, final float txElitism, final Crossover crossover) {
        this.matrix = Matrix.getInstance().getMatrix();
        this.fitnessToFind = Matrix.getInstance().getFitness();
        this.sizePopulation = sizePopulation;
        this.txMutation = txMutation;
        this.txCrossover = txCrossover;
        this.crossover = crossover;
        this.txElitism = txElitism;
    }

    public AlgoritmoGenetico() {
        this(10, 20, 5, 100, 0, true, Crossover.OX);
    }

    public long execute() {

        int[][] population = generateRandomPopulation(sizePopulation, matrix);

        int bestFitness = 0;

        //Vetor que guarda os fitness de fitness (são atributos privados por não são necessários para o acesso externo)
        int[] fitness = calculateFitness(population, matrix);

        for (long generation = 0; generation < generations; generation++) {

            // Sort the fitness
//            Arrays.sort(fitness);
            sort(population, fitness);

            Assert.isTrue(calculateFitness(population, matrix)[0] == fitness[0]);

            // Aplica o crossover do indivíduo selecionado pela roulette
            population = crossover(txElitism, txCrossover, withRoulette ? roulette(fitness) : 0, population, matrix, this.crossover);

            // Aplica mutaçao
            population = mutation(population, txMutation, matrix);

            // Calcula o fitness
            fitness = calculateFitness(population, matrix);

            // Print
            final int bestFitnessOnThisGeneration = fitness[0];
            if (bestFitnessOnThisGeneration != bestFitness) {
                bestFitness = bestFitnessOnThisGeneration;
                final StringBuilder genesOfchromosome = new StringBuilder();
                final StringBuilder genesSumsOfchromosome = new StringBuilder();
                for (int i = 0; i < population[0].length; i++) {

                    genesOfchromosome.append(population[0][i]);
                    if (i != population[0].length - 1)
                        genesOfchromosome.append(" ");

                    if (i != population[0].length - 1)
                        genesSumsOfchromosome.append(population[0][i]).append(" + ").append(population[0][i + 1]).append(" = ").append(this.matrix[population[0][i]][population[0][i + 1]]).append("  ");
                }

                final String toShowing = "Fitness a ser encontrado: " + format(5, fitnessToFind) + " Fitness encontrado: ";
                System.out.println(toShowing + format(5, bestFitnessOnThisGeneration) + " " + genesOfchromosome);
                if (bestFitnessOnThisGeneration <= fitnessToFind) {
                    System.out.println("Encontrou " + fitness[0] + " na geração " + generation);
                    System.out.println(genesSumsOfchromosome);
                    return generation;
                }
            }

            generations++;

        }

        throw new RuntimeException();
    }

    /**
     * @param txElitism   int
     * @param roulette    int
     * @param population  int[][]
     * @param txCrossover int
     * @param matrix      int[][]
     * @param crossover   Crossover
     * @return int[][]
     */
    private int[][] crossover(final float txElitism, final float txCrossover, final int roulette, final int[][] population, final int[][] matrix, final Crossover crossover) {
        if (crossover.equals(Crossover.PMX))
            return crossoverPMX(txElitism, txCrossover, roulette, population, matrix);
        else
            return crossoverOX(txElitism, txCrossover, roulette, population, matrix);
    }

    private int[][] crossoverOX(final float txElitism, final float txCrossover, final int roulette, final int[][] population, final int[][] matrix) {

        if (new Random().nextInt(99) < txCrossover - 1) {

            // Select the dad
            final int[] dad = population[roulette];

            // Create new population
            final int[][] newPopulation = Arrays.copyOf(population, population.length);

            for (int c = 0; c < population.length; c++) {

                if (c != roulette) { // To not do crossover himself.

                    final int[] mom = population[c];

                    if (Arrays.equals(dad, mom))
                        continue;

                    // son with first half of the genes of the dad
                    final int[] firstSon = crossoverOX(firstHalfFrom(dad), mom);
                    // son with second half of the genes of the dad
                    final int[] secondSon = crossoverOX(secondHalfFrom(dad), mom);
                    // son with first half of the genes of the mom
                    final int[] thirdSon = crossoverOX(firstHalfFrom(mom), dad);
                    // son with second half of the genes of the mom
                    final int[] fourthSon = crossoverOX(secondHalfFrom(mom), dad);

                    // Select the winner from fight between sons
                    final int[] winner = fight(matrix, firstSon, secondSon, thirdSon, fourthSon);

                    // Apply the 'steady-state' elitism conform the txElitism.
                    // If the txElitism is 100%, the algorithm always  be 'steady-state';
                    if (new Random().nextFloat() * 100 <= txElitism) {
                        // If the winner is strongest then the mom
                        int fitnessWinner = calculateFitness(winner, matrix);
                        int fitnessMom = calculateFitness(mom, matrix);

                        if (fitnessWinner < fitnessMom)
                            newPopulation[c] = winner;
                    } else // If the elitism is 0%, the algorithm always  be 'generational' type
                        newPopulation[c] = winner;

                }
            }

            return sort(newPopulation, calculateFitness(newPopulation, matrix));
        }

        return population;
    }

    public int[][] crossoverPMX(final float txElitism, final float txCrossover, final int roulette, final int[][] population, final int[][] matrix) {

        if (new Random().nextInt(99) < txCrossover - 1) {

            int[] dad = population[roulette]; // Select the father (the strongest chromosome)

            // Create new population
            final int[][] newPopulation = Arrays.copyOf(population, population.length);

            for (int c = 0; c < population.length; c++) { // Runs through the population, except the chromosomes out of the elitism range.

                if (c != roulette) { // Ignore the father selected, to not be crossover with same father

                    int[] mom = population[c];

                    if (Arrays.equals(dad, mom))
                        continue;

                    int[] firstPartyOfTheSonOfTheDad = new int[cut(dad.length, 3)];
                    int[] firstPartyOfTheSonOfTheMom = new int[cut(mom.length, 3)];

                    // preenche as primeiras partes
                    for (int i = 0; i < cut(dad.length, 3); i++) {
                        firstPartyOfTheSonOfTheDad[i] = dad[i];
                        firstPartyOfTheSonOfTheMom[i] = mom[i];
                    }

                    int[] restSonsOfTheDad = new int[dad.length - (cut(dad.length, 3))];
                    int[] restSonsOfTheMom = new int[mom.length - (cut(mom.length, 3))];

                    int cont = 0;
                    for (int j : dad) {
                        if (isContained(firstPartyOfTheSonOfTheMom, j)) {
                            restSonsOfTheMom[cont] = j;
                            cont++;
                        }
                    }

                    cont = 0;
                    for (int j : mom) {
                        if (isContained(firstPartyOfTheSonOfTheDad, j)) {
                            restSonsOfTheDad[cont] = j;
                            cont++;
                        }
                    }

                    // Populate sons
                    final int[] firstSonOfDad = new int[dad.length];
                    final int[] secondSonOfDad = new int[dad.length];
                    final int[] firstSonOfMom = new int[mom.length];
                    final int[] secondSonOfMom = new int[mom.length];

                    for (int i = 0; i < firstSonOfDad.length; i++)
                        if (i < firstPartyOfTheSonOfTheDad.length)
                            firstSonOfDad[i] = firstPartyOfTheSonOfTheDad[i];
                        else
                            firstSonOfDad[i] = restSonsOfTheDad[i - firstPartyOfTheSonOfTheDad.length];

                    for (int i = 0; i < secondSonOfDad.length; i++)
                        if (i >= restSonsOfTheDad.length)
                            secondSonOfDad[i] = firstPartyOfTheSonOfTheDad[i - restSonsOfTheDad.length];
                        else
                            secondSonOfDad[i] = restSonsOfTheDad[i];

                    for (int i = 0; i < firstSonOfMom.length; i++)
                        if (i < firstPartyOfTheSonOfTheMom.length)
                            firstSonOfMom[i] = firstPartyOfTheSonOfTheMom[i];
                        else
                            firstSonOfMom[i] = restSonsOfTheMom[i - firstPartyOfTheSonOfTheMom.length];

                    for (int i = 0; i < secondSonOfMom.length; i++)
                        if (i >= restSonsOfTheMom.length)
                            secondSonOfMom[i] = firstPartyOfTheSonOfTheMom[i - restSonsOfTheMom.length];
                        else
                            secondSonOfMom[i] = restSonsOfTheMom[i];

                    // Select the winner from fight between sons
                    final int[] winner = fight(matrix, firstSonOfMom, secondSonOfMom, firstSonOfDad, secondSonOfDad);

                    // Apply the 'steady-state' elitism conform the txElitism.
                    // If the txElitism is 100%, the algorithm always  be 'steady-state';
                    if (new Random().nextFloat() * 100 <= txElitism) {
                        // If the winner is strongest then the mom
                        int fitnessWinner = calculateFitness(winner, matrix);
                        int fitnessMom = calculateFitness(mom, matrix);

                        if (fitnessWinner < fitnessMom)
                            newPopulation[c] = winner;
                    } else // If the elitism is 0%, the algorithm always  be 'generational' type
                        newPopulation[c] = winner;
                }
            }

            return sort(newPopulation, calculateFitness(newPopulation, matrix));
        }

        return population;
    }

    public static int roulette(final int[] fitness) {

        // Inverting array
        int[] fitnessAux = new int[fitness.length];
        int cont = 0;
        for (int i = fitness.length - 1; i >= 0; i--) {
            fitnessAux[i] = fitness[cont];
            cont++;
        }

        //Making the roulette
        int summation = 0;

        for (int j = 0; j < fitness.length; j++) {
            summation = summation + fitnessAux[j];
        }

        summation = new Random().nextInt(summation);
        int positionChosen = -1;
        do {
            positionChosen++;
            summation = summation - fitnessAux[positionChosen];
        } while (summation > 0);

        return positionChosen;
    }

    public static int[][] generateRandomPopulation(final int sizePopulation, final int[][] matrix) {

        int[][] routes = new int[sizePopulation][matrix.length];

        //Inicializando população inicial
        for (int k = 0; k < sizePopulation; k++) {

            int[] auxRoute = new int[matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                auxRoute[i] = i;
            }

            int[] route = new int[auxRoute.length];

            shuffle(route.length, route, auxRoute);

            if (!isContained(routes, route)) {
                routes[k] = route;
            } else {
                k--;
            }
        }

        return routes;
    }

    private static void shuffle(final int size, final int[] route, int[] auxRoute) {
        // Shuffling
        for (int i = 0; i < size; i++) {
            int r;
            do {
                r = new Random().nextInt(size - i);
            } while (r == i);

            route[i] = auxRoute[r];
            auxRoute = preencheVetorSemOR(auxRoute, r);
        }
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

    public static int[] calculateFitness(final int[][] population, final int[][] matrix) {
        int[] fitness = new int[population.length];
        for (int i = 0; i < population.length; i++) {
            fitness[i] = calculateFitness(population[i], matrix);
        }
        return fitness;
    }

    public static int calculateFitness(int[] chromosome, int[][] matrix) {
        int fitness = 0;
        for (int j = 0; j < chromosome.length - 1; j++) {
            if (j + 1 != chromosome.length)
                fitness = fitness + matrix[chromosome[j]][chromosome[j + 1]];
            else
                fitness = fitness + chromosome[j];
        }
        return fitness;
    }

    // TODO change to quick sort
    private static int[][] sort(int[][] population, int[] fitness) {
        // Ordering
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

    private static int[] crossoverOX(int[] dad, int[] mae) {
        int[] restOfTheMOm = new int[cut(mae.length, 2)];

        //preenche segunda metade do cromossomo (ou indivíduo) com os genes (na sequência) da mae que não estão no pai
        int cont = 0;
        for (int i : mae) {
            if (isContained(dad, i)) {
                restOfTheMOm[cont] = i;
                cont++;
            }
        }

        // Copy the genes to son
        int[] son = new int[mae.length];
        for (int i = 0; i < son.length; i++) {
            if (i < dad.length)
                son[i] = dad[i];
            else
                son[i] = restOfTheMOm[i - dad.length];
        }

        return son;
    }

    private static int[] firstHalfFrom(int[] vet) {
        final int sizeOfHalf = cut(vet.length, 2);
        final int[] firstHalf = new int[sizeOfHalf];
        //preenche primeira metade do cromossomo (ou indivíduo) com os genes do pai
        System.arraycopy(vet, 0, firstHalf, 0, sizeOfHalf);
        return firstHalf;
    }

    private static int[] secondHalfFrom(int[] vet) {
        final int sizeOfHalf = cut(vet.length, 2);
        final int[] secondHalf = new int[sizeOfHalf];
        //preenche primeira metade do cromossomo (ou indivíduo) com os genes do pai
        System.arraycopy(vet, secondHalf.length, secondHalf, 0, sizeOfHalf);
        return secondHalf;
    }

    /**
     * Fight between sons
     *
     * @param matrix      int[][]
     * @param chromosomes int[]...
     * @return int[]
     */
    public int[] fight(final int[][] matrix /*TODO tem q eu ser um singleton*/, final int[]... chromosomes) {

        final int[] fitness = new int[chromosomes.length];

        for (int i = 0; i < chromosomes.length; i++)
            fitness[i] = calculateFitness(chromosomes[i], matrix);

        sort(chromosomes, fitness);

//        return this.withRoulette ? chromosomes[roulette(fitness)] : chromosomes[0];
        return chromosomes[0];
    }

    /**
     * Make the cut point
     *
     * @param size    int Size of vector
     * @param divisor int
     * @return int
     */
    private static int cut(final int size, final int divisor) {
        return ((size % 2) == 1) ? (size / divisor) + 1 : size / divisor;
    }

    public static int[][] mutation(final int[][] population, final float txMutation, final int[][] matrix) {

        for (int k = 1; k < population.length; k++) {
            final float r = new Random().nextFloat() * 100;
            if (r <= txMutation) {
                int[] auxChromosome;
                do {
                    auxChromosome = mutation(population.length, matrix);
                } while (isContained(population, auxChromosome));
                population[population.length - 1] = auxChromosome;
            }
        }
        return sort(population, calculateFitness(population, matrix));
    }

    // O tamanho da população da mutação deve ser inversamente proporcional ao tamanho da população vezes a taxa de mutação
    private static int[] mutation(final int TAM_POP, final int[][] matrix) {
        final int[][] population = generateRandomPopulation(TAM_POP, matrix);
        sort(population, calculateFitness(population, matrix));
//        int roulette  = roulette (calcularFitness(population, matrix));
        return population[/*TAM_POP - (*/0 /*+ 1)*/]; // Selecione sempre o mais forte
    }

    private static boolean isContained(final int[] vet, final int i) {
        for (final int k : vet) {
            if (k == i)
                return false;
        }
        return true;
    }

    private static boolean isContained(final int[][] population, final int[] chromosome) {
        for (final int[] ints : population) {
            if (Arrays.equals(ints, chromosome)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param digits int
     * @param input  Integer
     * @return String
     */
    protected static String format(@NotNull final int digits, final @NotBlank Integer input) {
        return String.format("%0" + digits + "d", input);
    }

}