package algorithm.memetic;

import algorithm.Matrix;
import algorithm.memetic.domain.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

import static algorithm.genetico.AlgoritmoGenetico.roulette;

public class Memetic {

    private int firstBest = 0;
    private LocalDateTime init;

    private LocalDateTime end;

//    private final int[][] matrix;

//    private final int fitnessToFind;

//
//    public Memetic() {
//        this.matrix = Matrix.getInstance().getMatrix();
//        this.fitnessToFind = Matrix.getInstance().getFitness();
//    }

    public long execute(final String nameInstance) {
        final int[][] population = generateRandomPopulation(2, Matrix.getInstance().getMatrix());
        return execute(nameInstance, population, 0);
    }

    public long execute(final String nameInstance, final int[][] population) {
        return execute(nameInstance, population, 0);
    }

    public long execute(final String nameInstance, final int[][] population, final int bestSelection) {
        init = LocalDateTime.now();
        // Tamanho da população. O tamanho da população também define a aleatóriedade

        execution(population, bestSelection);
        end = LocalDateTime.now();
        return print(nameInstance, sort(population, calculateFitness(population))[0], init, end);
    }

    private void execution(int[][] population, final int bestSelection) {
        int[] best = sort(population, calculateFitness(population))[0];

        //Variável auxiliar para guardar o melhor da população anterior anterior.
        int melhorAnterior = calculateFitness(best, Matrix.getInstance().getMatrix());

        firstBest = melhorAnterior;

        while (!overallBest(population, Matrix.getInstance().getMatrix(), Matrix.getInstance().getFitness()) && Matrix.getInstance().getCount() < 7) {

            population = jump(population, Matrix.getInstance().getMatrix());

            population = learn(population, bestSelection);

            //Variável auxiliar para não duplicar o processo de cálculo do fitness
            final int currentBest = calculateFitness(best, Matrix.getInstance().getMatrix());

            //TODO gambiarrona para elitizar o melhor e o algorithm não desaprender
            if (calculateFitness(sort(population, calculateFitness(population))[0], Matrix.getInstance().getMatrix()) <= currentBest) {
                System.arraycopy(population[0], 0, best, 0, population[0].length);
            } else {
                System.arraycopy(best, 0, population[0], 0, population[0].length);
            }
            //Se o fitness do melhor indivíduo encontrado for melhor que o anterior, imprime-o
            if (currentBest < melhorAnterior) {
                melhorAnterior = calculateFitness(best, Matrix.getInstance().getMatrix());
                final int[] theBest = sort(population, calculateFitness(population))[0];
                Matrix.getInstance().addMemeticResult(new Result(firstBest, calculateFitness(theBest, Matrix.getInstance().getMatrix()), Matrix.getInstance().getFitness(), "memetic"));
//                System.out.println("  PRIMEIRO MELHOR = " +firstBest+ "   Memético= " + calculateFitness(theBest, matrix) + " => " + Matrix.getInstance().getFitness() /*+ " | " + Arrays.toString(theBest)*/) ;
//                System.out.println(" = " + calcularFitness(ordenar(population, calcularFitness(population, matrix))[0], matrix));
            }
        }
    }

    private static int[][] jump(final int[][] population, final int[][] matrix) {
        int[] bestChromosome = sort(population, calculateFitness(population, matrix))[0];
        final int[][] newPopulation = generateRandomPopulation(population.length, matrix);
        newPopulation[newPopulation.length - 1] = bestChromosome;
        return sort(newPopulation, calculateFitness(newPopulation, matrix));
    }

    public static int[][] learn(final int[][] population, final int bestSelection) {

        final int[] melhor;
        if (0 == bestSelection) {
            // Pega a primeira ideia aleatória
            melhor = population[0];

        } else if (1 == bestSelection)
            // Rotaciona o melhor indivíduo para iniciar pela sua pior ideia (5)
            melhor = rotate(population[0], population[0][getWorstIdea(population[0])]);

        else if (2 == bestSelection) {
            // Pega um indivíduo aleatório tendendo para a melhor (4.5)
            final int[] fitness = calculateFitness(population);
            melhor = population[roulette(fitness)];

        } else {
            // Pega um indivíduo aleatório tendendo para a melhor, e o rotaciona para pegar sua pior idéia (5)
            final int[] fitness = calculateFitness(population);
            final int individualFromRoulette = roulette(fitness);
            melhor = rotate(population[individualFromRoulette], population[individualFromRoulette][getWorstIdea(population[individualFromRoulette])]);
        }

        print(population);
//        for (int n = 0; n < population.length; n++) {
        // Percorrendo toda a população
        for (int i = 1; i < population.length; i++) {
            // Percorrendo ideias do melhor
            for (int im = 0; im < melhor.length; im++) {
                //Percorrendo o indivíduo da população
                for (int ii = 0; ii < population[i].length; ii++) {
                    // Se o ponto do melhor é igual ao do indivíduo
                    if (population[i][ii] == melhor[im]) {
                        Memetic.learn(population[i], ii, melhor, im);
                        break;
                    }
                }
            }
        }
//        }

        if(!Arrays.equals(rotate(melhor), rotate(population[1])))
            System.out.println("NÃO CONVERGE");

        return sort(population, calculateFitness(population));
    }

    public static int getWorstIdea(final int[] individual, final int... excludes) {
        int worst = 0;
        int index = 0;
        for (int i = 0; i < individual.length; i++) {
            final int nextIdea = i == individual.length - 1 ? 0 : i + 1;
            final int coast = calculateFitness(individual[i], individual[nextIdea]);
            if (excludes.length != 0) {
                for (int j = 0; j < excludes.length; j++) {
                    if (!isContained(i, excludes)) {
                        if (coast > worst) {
                            worst = coast;
                            index = i;
                        }
                    }
                }
            } else {
                if (coast > worst) {
                    worst = coast;
                    index = i;
                }
            }
        }
        return index;
    }

    static boolean isContained(final int i, final int[] array) {
        for (final int k : array) {
            if (k == i)
                return true;
        }
        return false;
    }

    static void learn(final int[] individuo, final int indexIdeiaDoIndividuo, final int[] melhor, final int indexIdeiaDoMelhor) {

        // Calcula os custos entre o ponto atual e o próximo

        final int proximaIdeiaDoMelhor = indexIdeiaDoMelhor == melhor.length - 1 ? 0 : indexIdeiaDoMelhor + 1;
        final int custoIdeiaDoMelhor = calculateFitness(melhor[indexIdeiaDoMelhor], melhor[proximaIdeiaDoMelhor]);
        final int proximaIdeiaDoIndividuo = indexIdeiaDoIndividuo == individuo.length - 1 ? 0 : indexIdeiaDoIndividuo + 1;
        final int custoIdeiaDoIndividuo = calculateFitness(individuo[indexIdeiaDoIndividuo], individuo[proximaIdeiaDoIndividuo]);

        // Se o vértice, rota, idéia do melhor for melhor que á idiea do indivíduo
        if (custoIdeiaDoMelhor < custoIdeiaDoIndividuo) {

            for (int k = 0; k < individuo.length; k++) {
                if (individuo[k] == melhor[proximaIdeiaDoMelhor]) {
                    individuo[k] = individuo[proximaIdeiaDoIndividuo];
                    break;
                }
            }
            individuo[proximaIdeiaDoIndividuo] = melhor[proximaIdeiaDoMelhor];

            // Se o vértice, rota, idéia do indivíduo for melhor que á idiea do melhor
        } else if (custoIdeiaDoIndividuo < custoIdeiaDoMelhor) {

            for (int k = 0; k < melhor.length; k++) {
                if (melhor[k] == individuo[proximaIdeiaDoIndividuo]) {
                    melhor[k] = melhor[proximaIdeiaDoMelhor];
                    break;
                }
            }
            melhor[proximaIdeiaDoMelhor] = individuo[proximaIdeiaDoIndividuo];
        }
    }

    public static void print(final int[][] matrix) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final int[] ints : matrix) {
            final String data = "\n" + Arrays.toString(ints).replaceAll("\\[", "").replaceAll("\\]", "") + ": " + calculateFitness(ints, Matrix.getInstance().getMatrix()) + "(fitness a ser encontrado: " + Matrix.getInstance().getFitness();
            stringBuilder.append(data);
        }
        System.out.print(stringBuilder);
    }

    static void print(final int[] line) {
        final String data = "\r" + Arrays.toString(line).replaceAll("\\[", "").replaceAll("\\]", "") + ": " + calculateFitness(line, Matrix.getInstance().getMatrix()) + "(fitness a ser encontrado: " + Matrix.getInstance().getFitness();
        System.out.println(data);
    }

    private static void write(final int[][] matrix) {
        Arrays.stream(matrix).forEach(Memetic::write);
    }

    private static void write(final int[] line) {
        try {
            final String data = "\r" + Arrays.toString(line).replaceAll("\\[", "").replaceAll("\\]", "") + ": " + calculateFitness(line, Matrix.getInstance().getMatrix()) + " fitness a ser encontrado: " + Matrix.getInstance().getFitness();
            System.out.write(data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int[][] rotate(int[][] population) {
        for (int i = 0; i < population.length; i++) {
            population[i] = rotate(population[i]);
        }
        return population;
    }

    /**
     * @param individual int[]
     * @return int[]
     */
    static int[] rotate(final int[] individual) {
        return rotate(individual, 0);
    }

    /**
     * @param individual int[]
     * @param index      int
     * @return int[]
     */
    static int[] rotate(final int[] individual, final int index) {
        int[] individualReturn = new int[individual.length];
        for (int i = 0; i < individual.length; i++) {
            if (individual[i] == index) {
                for (int j = 0; j < individual.length; j++) {
                    final int i1 = individual.length - (individual.length - i - j);
                    if (i1 < individual.length)
                        individualReturn[j] = individual[i1];
                    else
                        individualReturn[j] = individual[(individual.length - j - i) * -1];
                }
                break;
            }
        }
        return individualReturn;
    }

    private static boolean overallBest(final int[][] population, final int[][] matrix, final int fitness) {
        return calculateFitness(population, matrix)[0] <= fitness;
    }

    public static int[][] generateRandomPopulation(final int sizeOfPopulation, final int[][] matrix) {

        int[][] routes = new int[sizeOfPopulation][matrix.length];

        //Inicializando população inicial
        for (int k = 0; k < sizeOfPopulation; k++) {

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

        return sort(routes, calculateFitness(routes, matrix));
    }

    private static void shuffle(final int tam, final int[] route, int[] auxRoute) {
        // Shuffling
        for (int i = 0; i < tam; i++) {
            int random;
            do {
                random = new Random().nextInt(tam - i);
            } while (random == i);

            route[i] = auxRoute[random];
            auxRoute = fillWithoutRandom(auxRoute, random);
        }
    }

    private static int[] fillWithoutRandom(final int[] arrayOfIndex, final int random) {
        int[] auxArray = new int[arrayOfIndex.length - 1];
        int cont = 0;
        for (int j = 0; j < arrayOfIndex.length; j++) {
            if (j == random) {
                continue;
            }
            auxArray[cont] = arrayOfIndex[j];
            cont++;
        }
        return auxArray;
    }

    private static int[] calculateFitness(final int[][] population) {
        int[] fitness = new int[population.length];
        for (int i = 0; i < population.length; i++) {
            fitness[i] = calculateFitness(population[i], Matrix.getInstance().getMatrix());
        }
        return fitness;
    }

    private static int[] calculateFitness(final int[][] population, final int[][] matrix) {
        int[] fitness = new int[population.length];
        for (int i = 0; i < population.length; i++) {
            fitness[i] = calculateFitness(population[i], matrix);
        }
        return fitness;
    }

    private static int calculateFitness(final int[] chromosome, final int[][] matrix) {
        int fitness = 0;
        for (int j = 0; j < chromosome.length; j++) {
            fitness = fitness + matrix[chromosome[j]][chromosome[j == chromosome.length - 1 ? 0 : j + 1]];
        }
        return fitness;
    }

    private static int calculateFitness(int cidade1, int cidade2) {
        return calculateFitness(cidade1, cidade2, Matrix.getInstance().getMatrix());
    }

    private static int calculateFitness(int cidade1, int cidade2, int[][] matrix) {
        return matrix[cidade1][cidade2];
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

    private static boolean isContained(int[][] population, int[] individuo) {
        for (int[] auxiliar : population) {
            if (Arrays.equals(auxiliar, individuo)) {
                return true;
            }
        }
        return false;
    }

//    private static void imprimir(int[][] population, int[] fitness, int[][] matrix) {
//        for (int j = 0; j < population.length; j++) {
//            System.out.print("Rota " + j + " = ");
//            for (int i = 0; i < matrix.length; i++) {
//                System.out.print(" " + population[j][i] + " ");
//            }
//            System.out.println(" fitness = " + fitness[j]);
//        }
//    }

    private long print(final String concluded, int[] rota, final LocalDateTime init, final LocalDateTime end) {
        final StringBuilder stringBuilder = new StringBuilder();
        if (concluded != null) stringBuilder.append(concluded);
        for (final int j : rota) {
            stringBuilder.append(" ").append(j);
        }
        stringBuilder.append(" = ").append(calculateFitness(rota, Matrix.getInstance().getMatrix())).append(" fitness a ser encontrado = ").append(Matrix.getInstance().getFitness());

        final long time = ChronoUnit.MILLIS.between(init, end);
        stringBuilder.append("  | demorou: ").append(time);

        System.out.println(stringBuilder);
        return time;
    }
}