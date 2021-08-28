package algoritmo.genetico;

import java.util.Random;

public final class Matrix {

    private static Matrix instance;

    public int[][] matrix;

    public int[][] getMatrix() {
        return matrix;
    }

    private Matrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public static Matrix getInstance() {
        instance = new Matrix(generateMatrix(10)); //TODO tam por parâmetros
        return instance;
    }


    public static int[][] generateMatrix(int TAM) {

        int number;
        int[][] matrix = new int[TAM][TAM];
        Random random = new Random();

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix.length; j++) {
                number = random.nextInt(100) + 200;
                matrix[i][j] = number;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (j == i) {
                    matrix[j][i] = 0;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix.length; j++) {
                if (j == i) {
                    matrix[j - 1][i] = i;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix.length; j++) {
                matrix[j][i] = matrix[i][j];
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int[] aMatriz : matrix) {
                if (aMatriz[i] == 0)
                    System.out.print("000 ");
                else if (aMatriz[i] < 100 && aMatriz[i] >= 10)
                    System.out.print("0" + aMatriz[i] + " ");
                else if (aMatriz[i] < 10)
                    System.out.print("00" + aMatriz[i] + " ");
                else
                    System.out.print(aMatriz[i] + " ");
            }
            System.out.println();
        }
        return matrix;
    }

    public static int getFitness(int TAM) {

        int cont = 0;
        for (int i = 0; i < TAM; i++) {
            cont = cont + i;
        }

        return cont;
    }
}