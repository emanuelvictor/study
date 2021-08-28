

package algoritmo.genetico;


import algoritmo.Matriz;
//TODO arrumar crossover TMX
//TODO configurar elitismo crossover TMX
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

    private int[][] MATRIZ_ADJACENTE = new int[][]{{}};
    // Tamanho da população
    private int sizePopulation;
    //    COM TAXA DE MUTAÇÃO 100% E CROSSOVER 0% O ALGORÍTMO ESTA CONFIGURADO PARA FORÇA BRUTA
    private final float txMutation;
    private final int txCrossover;
    private Crossover CROSSOVER;
    private int fitnessToFind;
    private int elitism = 0;
    // Número de geraçoẽs (são atributos privados por não são necessários para o acesso externo)
    // SE FOR SETADO COMO NULL, AS GERAÇẼOS SÃO CONSIDERADAS COMO INFINITAS. OU SEJA SÓ VAI PARAR QUANDO ENCONTRAR O FITNESS
    private Integer generations = 1;


    public AlgoritmoGenetico(int sizeMatrix, int sizePopulation, float txMutation, int txCrossover) {
        this.MATRIZ_ADJACENTE = Matriz.getMatriz(sizeMatrix);
        this.fitnessToFind = Matriz.getFitness(sizeMatrix);
        this.sizePopulation = sizePopulation;
        this.txMutation = txMutation;
        this.txCrossover = txCrossover;
    }

    public AlgoritmoGenetico() {
        this(10, 20, 5, 100);
    }


    public int execute() {

        int[][] population = generateRandomPopulation(sizePopulation, MATRIZ_ADJACENTE);

        int bestFitness = 0;

        //Vetor que guarda os fitness de fitness (são atributos privados por não são necessários para o acesso externo)
        int[] fitness = calcularFitness(population, MATRIZ_ADJACENTE);

        for (int generation = 0; generation < generations; generation++) { // todo alterar para do while

//            //Reordena a populaçao pelo fitness
//            population = sort(population, fitness);

            // Aplica o crossover do indivíduo selecionado pela roleta
            population = crossoverOX(roleta(fitness), population, txCrossover, MATRIZ_ADJACENTE);

            // Aplica mutaçao
            population = mutation(population, txMutation, MATRIZ_ADJACENTE);

            // Calcula o fitness
            fitness = calcularFitness(population, MATRIZ_ADJACENTE);

//            /*populacao = */
//            population = sort(population, fitness);

            final int bestFitnessOnThisGeneration = calcularFitness(population, MATRIZ_ADJACENTE)[0];
            if (bestFitnessOnThisGeneration != bestFitness) {
                bestFitness = bestFitnessOnThisGeneration;
                final StringBuilder genesOfIndividual = new StringBuilder();
                final StringBuilder genesSumsOfIndividual = new StringBuilder();
                for (int i = 0; i < population[0].length; i++) {

                    genesOfIndividual.append(population[0][i]);
                    if (i != population[0].length - 1)
                        genesOfIndividual.append(" ");

                    if (i != population[0].length - 1)
                        genesSumsOfIndividual.append(population[0][i]).append(" + ").append(population[0][i + 1]).append(" = ").append(this.MATRIZ_ADJACENTE[population[0][i]][population[0][i + 1]]).append("  ");
                }

                final String toShowing = "Fitness a ser encontrado: " + format(3, fitnessToFind) + " Fitness encontrado: ";
                System.out.println(toShowing + format(3, bestFitnessOnThisGeneration) + " " + genesOfIndividual);
                if (bestFitnessOnThisGeneration <= fitnessToFind) {
                    System.out.println("Encontrou " + fitness[0] + " na geração " + generation);
                    System.out.println(genesSumsOfIndividual);
                    return generation;
                }
            }

            generations++;

        }
        throw new RuntimeException();

    }


    private static int[][] crossover(final int elitismo, final int roleta, final int[][] population, final int TAXA_CROSSOVER, final int[][] MATRIZ_ADJACENTE, final Crossover CROSSOVER) {
        if (CROSSOVER == Crossover.PMX)
            return crossoverPMX(elitismo, roleta, population, TAXA_CROSSOVER, MATRIZ_ADJACENTE);
        else
            return crossoverOX(roleta, population, TAXA_CROSSOVER, MATRIZ_ADJACENTE);
    }

    private static int[][] crossoverOX(int roleta, int[][] population, int txCrossover, int[][] MATRIZ_ADJACENTE) {

        if (new Random().nextInt(99) < txCrossover - 1) {

            // Select the dad
            final int[] dad = population[roleta];

            // Create new population
            final int[][] newPopulation = new int[population.length][];

            // Preserve the father in the new population
            newPopulation[roleta] = dad;

            for (int c = 0; c < population.length; c++) {

                if (c != roleta) { // To not do crossover himself.

                    final int[] mom = population[c];

                    // son with first half of the genes of the dad
                    final int[] firstSon = crossoverOX(firstHalfFrom(dad), mom);
                    // son with second half of the genes of the dad
                    final int[] secondSon = crossoverOX(secondHalfFrom(dad), mom);
                    // son with first half of the genes of the mom
                    final int[] thirdSon = crossoverOX(firstHalfFrom(mom), dad);
                    // son with second half of the genes of the mom
                    final int[] fourthSon = crossoverOX(secondHalfFrom(mom), dad);

                    // Select the winner from fight between sons
                    final int[] winner = fight(MATRIZ_ADJACENTE, firstSon, secondSon, thirdSon, fourthSon);

                    // If the winner is strongest then the mom, and weaker then the dad
                    int fitnessWinner = calcularFitness(winner, MATRIZ_ADJACENTE);
                    int fitnessMom = calcularFitness(mom, MATRIZ_ADJACENTE);

                    if (fitnessWinner < fitnessMom)
                        newPopulation[c] = winner;
                    else
                        newPopulation[c] = mom;

                }
            }

            return sort(newPopulation, calcularFitness(newPopulation, MATRIZ_ADJACENTE));
        }

        return population;

    }

    private static int roleta(int[] fitness) {

        // INVERTENDO VETOR **GAMBIA**
        int[] fitnessAux = new int[fitness.length];

        int cont = 0;
        for (int i = fitness.length - 1; i >= 0; i--) {
            fitnessAux[i] = fitness[cont];
            cont++;
        }

        //fazendo a roleta
        int somatoria = 0;

        for (int j = 0; j < fitness.length; j++) {
            somatoria = somatoria + fitnessAux[j];//this.getMochila(j).getFitness();
        }

        somatoria = new Random().nextInt(somatoria);
        int posicaoEscolhida = -1;
        do {
            posicaoEscolhida++;
            somatoria = somatoria - fitnessAux[posicaoEscolhida];//this.getMochila(posicaoEscolhida).getFitness();
        } while (somatoria > 0);

        return posicaoEscolhida;
    }

    public static int[][] generateRandomPopulation(int TAM_POP, int[][] MATRIZ_ADJACENTE) {


        int[][] rotas = new int[TAM_POP][MATRIZ_ADJACENTE.length];

        //Inicializando população inicial
        for (int k = 0; k < TAM_POP; k++) {

            int[] rotaAux = new int[MATRIZ_ADJACENTE.length];
            for (int i = 0; i < MATRIZ_ADJACENTE.length; i++) {
                rotaAux[i] = i;
            }

            int[] rota = new int[rotaAux.length];

            rota = embaralha(rota.length, rota, rotaAux);

            if (!isContained(rotas, rota)) {
                rotas[k] = rota;
            } else {
                k--;
            }
        }

        return rotas;
    }

    private static int[] embaralha(int tam, int rota[], int rotaAux[]) {
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

    public static int[] calcularFitness(int[][] populacao, int[][] MATRIZ_ADJACENTE) {
        int[] fitness = new int[populacao.length];
        for (int i = 0; i < populacao.length; i++) {
            fitness[i] = calcularFitness(populacao[i], MATRIZ_ADJACENTE);
        }
        return fitness;
    }

    public static int calcularFitness(int[] individuo, int[][] MATRIZ_ADJACENTE) {
        int fitness = 0;
        for (int j = 0; j < individuo.length - 1; j++) {
            if (j + 1 != individuo.length)
                fitness = fitness + MATRIZ_ADJACENTE[individuo[j]][individuo[j + 1]];
            else
                fitness = fitness + individuo[j];
        }
        return fitness;
    }

    // TODO MUDAR PARA O QUICK_SORT
    private static int[][] sort(int[][] populacao, int[] fitness) {
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

    private static int[] crossoverOX(int[] pai, int[] mae) {
        int[] restoDaMae = new int[cut(mae.length, 2)];

        //preenche segunda metade do cromossomo (ou indivíduo) com os genes (na sequência) da mae que não estão no pai
        int cont = 0;
        for (int i : mae) {
            if (isContained(pai, i)) {
                restoDaMae[cont] = i;
                cont++;
            }
        }

        // Copy the genes to son
        int[] son = new int[mae.length];
        for (int i = 0; i < son.length; i++) {
            if (i < pai.length)
                son[i] = pai[i];
            else
                son[i] = restoDaMae[i - pai.length];
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
     * @param MATRIZ_ADJACENTE
     * @param individuals
     * @return
     */
    public static int[] fight(int[][] MATRIZ_ADJACENTE /*TODO tem q eu ser um singleton*/, final int[]... individuals) {

        final int[] fitness = new int[individuals.length];

        for (int i = 0; i < individuals.length; i++) {
            fitness[i] = calcularFitness(individuals[i], MATRIZ_ADJACENTE);
        }

// sorting array
        Arrays.sort(fitness);

        return individuals[roleta(fitness)];
    }

    public static int[][] crossoverPMX(int elitismo, int roleta, final int[][] population, int TAXA_CROSSOVER, int[][] MATRIZ_ADJACENTE) {

        for (int c = 0; c < population.length - elitismo; c++) { // Runs through the population, except the individuals out of the elitism range.

            int[] dad = population[roleta]; // Select the father (the strongest individual)

            if (c != roleta) { // Ignore the father selected, to not be crossover with same father
                if (new Random().nextInt(99) < TAXA_CROSSOVER - 1) {

                    int[] mom = population[c];


                    int[] primeiraParteFilhoDoPai = new int[cut(dad.length, 3)];
                    int[] primeiraParteFilhoDaMae = new int[cut(mom.length, 3)];

                    // preenche as primeiras partes
                    for (int i = 0; i < cut(dad.length, 3); i++) {
                        primeiraParteFilhoDoPai[i] = dad[i];
                        primeiraParteFilhoDaMae[i] = mom[i];
                    }

                    int[] restoFilhoDoPai = new int[dad.length - (cut(dad.length, 3))];
                    int[] restoFilhoDaMae = new int[mom.length - (cut(mom.length, 3))];

                    int cont = 0;
                    for (int j : dad) {
                        if (isContained(primeiraParteFilhoDaMae, j)) {
                            restoFilhoDaMae[cont] = j;
                            cont++;
                        }
                    }

                    cont = 0;
                    for (int j : mom) {
                        if (isContained(primeiraParteFilhoDoPai, j)) {
                            restoFilhoDoPai[cont] = j;
                            cont++;
                        }
                    }

                    // Populate sons
                    final int[] filhoDoPai = new int[dad.length];
                    final int[] filhoDaMae = new int[mom.length];

                    for (int i = 0; i < filhoDoPai.length; i++)
                        if (i < primeiraParteFilhoDoPai.length)
                            filhoDoPai[i] = primeiraParteFilhoDoPai[i];
                        else
                            filhoDoPai[i] = restoFilhoDoPai[i - primeiraParteFilhoDoPai.length];

                    for (int i = 0; i < filhoDaMae.length; i++)
                        if (i < primeiraParteFilhoDaMae.length)
                            filhoDaMae[i] = primeiraParteFilhoDaMae[i];
                        else
                            filhoDaMae[i] = restoFilhoDaMae[i - primeiraParteFilhoDaMae.length];

                    // Select the winner from fight between sons
                    final int[] winner = fight(MATRIZ_ADJACENTE, filhoDaMae, filhoDoPai);

                    // If the winner is strongest then the mom, and weaker then the dad
                    if ((calcularFitness(winner, MATRIZ_ADJACENTE) > calcularFitness(dad, MATRIZ_ADJACENTE))) {
                        if (calcularFitness(winner, MATRIZ_ADJACENTE) < calcularFitness(mom, MATRIZ_ADJACENTE))
                            population[c] = winner;
                    } else
                        population[roleta] = winner;
                }
            }
        }

        return sort(population, calcularFitness(population, MATRIZ_ADJACENTE));
    }

    /**
     * Make the cut point
     *
     * @param size    int Size of vector
     * @param divisor int
     * @return int
     */
    private static int cut(int size, int divisor) {
        return ((size % 2) == 1) ? (size / divisor) + 1 : size / divisor;
    }

    public static int[][] mutation(int[][] population, float TAXA_MUTACAO, int[][] MATRIZ_ADJACENTE) {

        for (int k = 1; k < population.length; k++) {
            float r = new Random().nextFloat();
            r = r * 100;
            if (r <= TAXA_MUTACAO) {
                int[] individuoAux;
                do {
                    individuoAux = mutation(population.length, MATRIZ_ADJACENTE);
                } while (isContained(population, individuoAux));
                population[population.length - 1] = individuoAux;
            }
        }
        return sort(population, calcularFitness(population, MATRIZ_ADJACENTE));
    }

    // O tamanho da população da mutação deve ser inversamente proporcional ao tamanho da população vezes a taxa de mutação
    private static int[] mutation(int TAM_POP, int[][] MATRIZ_ADJACENTE) {
        int[][] population = generateRandomPopulation(TAM_POP, MATRIZ_ADJACENTE);
        sort(population, calcularFitness(population, MATRIZ_ADJACENTE));
//        int roleta = roleta(calcularFitness(populacao, MATRIZ_ADJACENTE));
        return population[/*TAM_POP - (*/0 /*+ 1)*/]; // Selecione sempre o mais forte
    }

    private static boolean isContained(int[] vet, int i) {
        for (int k : vet) {
            if (k == i)
                return false;
        }
        return true;
    }

    private static boolean isContained(int[][] populacao, int[] individuo) {
        for (int[] ints : populacao) {
            if (Arrays.equals(ints, individuo)) {
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