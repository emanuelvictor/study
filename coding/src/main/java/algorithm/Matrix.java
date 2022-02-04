package algorithm;

import java.util.Random;

/**
 * Algoritmo Genético implementado por Emanuel Victor e Haroldo Ramirez em 29/09/14.
 * Análise da influencia da mutação nos crossovers OX e PMX.
 * Matrixes analisadas
 */
public final class Matrix {

    private static boolean PRINT_MATRIX = false;

    private static Matrix instance;

    private int fitness;

    private int[][] matrix;

    public int getFitness() {
        return fitness;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    private Matrix() {
    }

    public static Matrix getInstance() {
        if (instance == null)
            instance = new Matrix();
        return instance;
    }

    public void generateMatrix(final int TAM) {
        generateMatrix(TAM, false);
    }


    public int[] generateOrderingRoute(final int TAM) {

        final int[] array = new int[TAM];

        for (int i = 0; i < TAM; i++) {
            array[i] = i;
        }

        return array;
    }

    public int[] generateRandomRoute(final int TAM) {

        final int[] array = generateOrderingRoute(TAM);

        shuffle(array);

        return array;
    }

    /**
     * Code from method java.util.Collections.shuffle();
     */
    public static void shuffle(final int[] array) {
        final Random random = new Random();
        final int count = array.length;
        for (int i = count; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i));
        }
    }

    private static void swap(final int[] array, final int i, final int j) {
        final int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void generateMatrix(final int TAM, final boolean linear) {

        final int[][] matrix = new int[TAM][TAM];
        final Random random = new Random();

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++) {
                final int randomNumber = random.nextInt(matrix.length) + matrix.length;
                matrix[i][j] = randomNumber == matrix.length ? randomNumber + 1 : randomNumber;
            }

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                if (j == i)
                    matrix[j][i] = 0;

        for (int i = 0; i < matrix.length; i++)
            for (int j = 1; j < matrix.length; j++)
                if (j == i)
                    if (linear)
                        matrix[j - 1][i] = i;
                    else
                        matrix[j - 1][i] = random.nextInt(matrix.length);

        // Back to home
        matrix[0][matrix.length - 1] = linear ? matrix.length : random.nextInt(matrix.length);

        for (int i = 0; i < matrix.length; i++)
            for (int j = 1; j < matrix.length; j++)
                matrix[j][i] = matrix[i][j];

        if (PRINT_MATRIX)
            for (int i = 0; i < matrix.length; i++) {
                for (int[] aMatrix : matrix) {
                    if (aMatrix[i] == 0)
                        System.out.print("000 ");
                    else if (aMatrix[i] < 100 && aMatrix[i] >= 10)
                        System.out.print("0" + aMatrix[i] + " ");
                    else if (aMatrix[i] < 10)
                        System.out.print("00" + aMatrix[i] + " ");
                    else
                        System.out.print(aMatrix[i] + " ");
                }
                System.out.println();
            }

        this.matrix = matrix;

        this.fitness = calculateFitness();

//        return matrix;
    }

    private int calculateFitness() {

        this.fitness = this.matrix[this.matrix.length - 1][this.matrix.length - 1];

        for (int i = 0; i < this.matrix.length; i++) {
            for (int i1 = 0; i1 < this.matrix.length; i1++) {
                if (i == i1)
                    fitness = fitness + this.matrix[i == this.matrix.length - 1 ? 0 : i + 1][i1];
            }
        }

        return fitness; // return to home
//        return cont;
    }

}