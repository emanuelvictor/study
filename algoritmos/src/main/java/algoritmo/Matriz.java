package algoritmo;

import java.util.Random;

/**
 * Algoritmo Genético implementado por Emanuel Victor e Haroldo Ramirez em 29/09/14.
 * Análise da influencia da mutação nos crossovers OX e PMX.
 * Matrizes analisadas
 */
public class Matriz {

    /**
     * TODO
     * Lista de melhores fitness
     * Para a matriz de 06 cidades a melhor rota possível é = 0  1  4  3  2  5 com fitness = 110
     * Para a matriz de 09 cidades a melhor rota possível é = 1  0  7  8  4  3  2  5  6  com fitness = 120
     * Para a matriz de 12 cidades a melhor rota possível é = 0  1  6  5  2  4  3  11  10  9  8  7   com fitness = 147
     * Para a matriz de 18 cidades a melhor rota possível é = 0  1  6  5  2  4  3  15  17  16  14  13  12  11  10  9  8  7   com fitness = 200
     */


    public static final int[][] MATRIZ_30_CIDADES =
            new int[][]{
                    /**     01  02  03  04  05  06  07  08  09  10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26  27  28  29  30*/
                    /**1**/{00, 1, 43, 87, 78, 32, 87, 78, 65, 88, 52, 56, 48, 45, 43, 32, 35, 31, 33, 49, 78, 32, 95, 91, 49, 82, 42, 99, 97, 51},
                    /**2**/{1, 00, 2, 78, 39, 45, 55, 99, 87, 56, 49, 65, 89, 59, 47, 42, 41, 78, 76, 69, 53, 49, 27, 32, 90, 90, 71, 45, 90, 77},
                    /**3**/{23, 2, 00, 3, 50, 54, 87, 64, 43, 48, 87, 42, 34, 23, 55, 49, 23, 48, 87, 64, 43, 48, 87, 42, 34, 23, 55, 49, 43, 48},
                    /**4**/{87, 87, 3, 00, 4, 34, 43, 87, 55, 43, 49, 67, 32, 90, 90, 69, 45, 30, 87, 55, 43, 49, 27, 51, 91, 88, 87, 45, 43, 47},
                    /**5**/{78, 24, 10, 4, 00, 5, 99, 43, 33, 44, 49, 49, 49, 60, 28, 39, 45, 44, 55, 43, 49, 67, 32, 90, 92, 88, 45, 30, 30, 90},
                    /**6**/{32, 87, 14, 34, 5, 00, 6, 45, 47, 31, 49, 49, 22, 49, 20, 22, 44, 23, 87, 64, 43, 48, 87, 62, 34, 68, 55, 49, 47, 48},
                    /**7**/{87, 8, 87, 43, 99, 6, 00, 7, 87, 41, 87, 77, 54, 70, 65, 49, 55, 67, 42, 49, 79, 39, 67, 49, 45, 88, 98, 32, 43, 32},
                    /**8**/{8, 99, 64, 87, 43, 45, 7, 00, 8, 32, 49, 55, 77, 31, 39, 45, 33, 63, 51, 68, 48, 70, 44, 73, 67, 63, 47, 44, 34, 34},
                    /**9**/{22, 87, 43, 55, 33, 47, 87, 8, 00, 9, 56, 32, 99, 49, 78, 88, 54, 47, 78, 48, 60, 44, 39, 67, 63, 47, 44, 34, 34, 34},
                    /**10*/{87, 56, 48, 43, 44, 31, 21, 32, 9, 00, 10, 43, 56, 49, 81, 98, 51, 44, 78, 48, 70, 44, 98, 67, 63, 47, 44, 34, 34, 34},
                    /**11*/{22, 49, 87, 49, 49, 49, 87, 49, 56, 10, 00, 11, 94, 76, 77, 33, 53, 34, 68, 48, 80, 44, 96, 67, 63, 47, 44, 34, 34, 34},
                    /**12*/{56, 65, 22, 27, 49, 49, 77, 55, 32, 43, 11, 00, 12, 77, 34, 43, 45, 34, 88, 48, 90, 44, 95, 67, 63, 47, 44, 34, 34, 34},
                    /**13*/{48, 23, 34, 32, 49, 22, 54, 77, 99, 56, 94, 12, 00, 13, 43, 32, 34, 34, 83, 48, 50, 44, 94, 67, 63, 47, 44, 34, 34, 34},
                    /**14*/{45, 55, 23, 90, 60, 49, 70, 21, 49, 49, 76, 77, 13, 00, 14, 31, 65, 45, 89, 48, 40, 44, 74, 67, 63, 47, 44, 34, 34, 34},
                    /**15*/{43, 49, 55, 90, 28, 20, 65, 29, 78, 21, 77, 34, 43, 14, 00, 15, 46, 55, 78, 48, 60, 44, 74, 67, 63, 47, 44, 34, 34, 34},
                    /**16*/{32, 42, 49, 7, 39, 22, 49, 45, 88, 98, 23, 43, 32, 31, 15, 00, 16, 50, 79, 48, 70, 44, 64, 67, 63, 47, 44, 34, 34, 34},
                    /**17*/{35, 22, 23, 45, 45, 44, 55, 23, 54, 21, 23, 45, 34, 65, 16, 16, 00, 17, 78, 48, 80, 44, 67, 67, 63, 47, 44, 34, 34, 34},
                    /**18*/{21, 28, 48, 30, 44, 23, 67, 63, 47, 44, 34, 34, 34, 45, 55, 10, 17, 00, 18, 48, 70, 44, 63, 67, 63, 47, 44, 34, 34, 34},
                    /**19*/{21, 28, 48, 30, 44, 23, 67, 63, 47, 44, 34, 34, 34, 45, 55, 10, 17, 18, 00, 19, 90, 44, 56, 67, 63, 47, 44, 34, 34, 34},
                    /**20*/{21, 28, 48, 30, 44, 23, 67, 63, 47, 44, 34, 34, 34, 45, 55, 10, 17, 76, 19, 00, 20, 44, 70, 67, 63, 47, 44, 34, 34, 34},
                    /**21*/{21, 28, 48, 30, 44, 23, 67, 63, 47, 44, 34, 34, 34, 45, 55, 10, 17, 68, 18, 20, 00, 21, 93, 67, 63, 47, 44, 34, 34, 34},
                    /**22*/{21, 28, 48, 30, 44, 23, 67, 63, 47, 44, 34, 34, 34, 45, 55, 10, 17, 87, 18, 10, 21, 00, 22, 67, 63, 47, 44, 34, 34, 34},
                    /**23*/{21, 28, 48, 30, 44, 23, 67, 63, 47, 44, 34, 34, 34, 45, 55, 10, 17, 98, 18, 18, 10, 22, 00, 23, 63, 47, 44, 34, 34, 34},
                    /**24*/{21, 28, 48, 30, 44, 23, 67, 63, 47, 44, 34, 34, 34, 45, 55, 10, 17, 35, 18, 18, 18, 10, 23, 00, 24, 47, 44, 34, 34, 34},
                    /**25*/{21, 28, 48, 30, 44, 23, 67, 63, 47, 44, 34, 34, 34, 45, 55, 10, 17, 45, 18, 18, 18, 18, 10, 24, 00, 25, 47, 44, 34, 34},
                    /**26*/{21, 28, 48, 30, 44, 23, 67, 63, 47, 44, 34, 34, 34, 45, 55, 10, 17, 47, 18, 18, 18, 18, 18, 10, 25, 00, 26, 44, 34, 34},
                    /**27*/{21, 28, 48, 30, 44, 23, 67, 63, 47, 44, 34, 34, 34, 45, 55, 10, 17, 53, 18, 18, 18, 18, 18, 18, 10, 26, 00, 27, 34, 34},
                    /**28*/{21, 28, 48, 30, 44, 23, 67, 63, 47, 44, 34, 34, 34, 45, 55, 10, 17, 34, 18, 18, 18, 18, 18, 18, 18, 10, 27, 00, 28, 34},
                    /**29*/{21, 28, 48, 30, 44, 23, 67, 63, 47, 44, 34, 34, 34, 45, 55, 10, 17, 34, 18, 18, 18, 18, 18, 18, 18, 18, 10, 28, 00, 29},
                    /**30*/{21, 28, 48, 30, 44, 23, 67, 63, 47, 44, 34, 34, 34, 45, 55, 10, 17, 32, 18, 18, 18, 18, 18, 18, 18, 18, 18, 10, 29, 00},

            };


    public static final int FITNESS_MATRIZ_18_CIDADES = 200;

    public static final int[][] MATRIZ_18_CIDADES =
            new int[][]{
                    /**     01  02  03  04  05  06  07  08  09  10  11  12  13  14  15  16  17  18*/
                    /**1**/{00, 7, 23, 87, 78, 32, 87, 8, 22, 87, 22, 56, 48, 45, 43, 32, 35, 21},
                    /**2**/{7, 00, 43, 87, 24, 87, 8, 99, 87, 56, 49, 65, 23, 55, 49, 42, 22, 28},
                    /**3**/{23, 43, 00, 24, 10, 14, 87, 64, 43, 48, 87, 22, 34, 23, 55, 49, 23, 48},
                    /**4**/{87, 87, 24, 00, 9, 34, 43, 87, 55, 43, 49, 27, 32, 90, 90, 7, 45, 30},
                    /**5**/{78, 24, 10, 9, 00, 87, 99, 43, 33, 44, 49, 49, 49, 60, 28, 39, 45, 44},
                    /**6**/{32, 87, 14, 34, 87, 00, 6, 45, 47, 31, 49, 49, 22, 49, 20, 22, 44, 23},
                    /**7**/{87, 8, 87, 43, 99, 06, 00, 54, 87, 21, 87, 77, 54, 70, 65, 49, 55, 67},
                    /**8**/{8, 99, 64, 87, 43, 45, 54, 00, 11, 32, 49, 55, 77, 21, 29, 45, 23, 63},
                    /**9**/{22, 87, 43, 55, 33, 47, 87, 11, 00, 15, 56, 32, 99, 49, 78, 88, 54, 47},
                    /**10*/{87, 56, 48, 43, 44, 31, 21, 32, 15, 00, 17, 43, 56, 49, 21, 98, 21, 44},
                    /**11*/{22, 49, 87, 49, 49, 49, 87, 49, 56, 17, 00, 15, 94, 76, 77, 23, 23, 34},
                    /**12*/{56, 65, 22, 27, 49, 49, 77, 55, 32, 43, 15, 00, 13, 77, 34, 43, 45, 34},
                    /**13*/{48, 23, 34, 32, 49, 22, 54, 77, 99, 56, 94, 13, 00, 12, 43, 32, 34, 34},
                    /**14*/{45, 55, 23, 90, 60, 49, 70, 21, 49, 49, 76, 77, 12, 00, 11, 31, 65, 45},
                    /**15*/{43, 49, 55, 90, 28, 20, 65, 29, 78, 21, 77, 34, 43, 11, 00, 32, 16, 55},
                    /**16*/{32, 42, 49, 7, 39, 22, 49, 45, 88, 98, 23, 43, 32, 31, 32, 00, 44, 10},
                    /**17*/{35, 22, 23, 45, 45, 44, 55, 23, 54, 21, 23, 45, 34, 65, 16, 44, 00, 11},
                    /**18*/{21, 28, 48, 30, 44, 23, 67, 63, 47, 44, 34, 34, 34, 45, 55, 10, 11, 00},
            };


    public static final int FITNESS_MATRIZ_12_CIDADES = 147;

    public static final int[][] MATRIZ_12_CIDADES =
            new int[][]{
                    /**     01  02  03  04  05  06  07  08  09  10  11  12  */
                    /**1**/{00, 7, 23, 87, 78, 32, 87, 8, 22, 87, 22, 56},
                    /**2**/{7, 00, 43, 87, 24, 87, 8, 99, 87, 56, 49, 65},
                    /**3**/{23, 43, 00, 24, 10, 14, 87, 64, 43, 48, 87, 22},
                    /**4**/{87, 87, 24, 00, 9, 34, 43, 87, 55, 43, 49, 27},
                    /**5**/{78, 24, 10, 9, 00, 87, 99, 43, 33, 44, 49, 49},
                    /**6**/{32, 87, 14, 34, 87, 00, 6, 45, 47, 31, 49, 49},
                    /**7**/{87, 8, 87, 43, 99, 06, 00, 54, 87, 21, 87, 77},
                    /**8**/{8, 99, 64, 87, 43, 45, 54, 00, 11, 32, 49, 55},
                    /**9**/{22, 87, 43, 55, 33, 47, 87, 11, 00, 15, 56, 32},
                    /**10*/{87, 56, 48, 43, 44, 31, 21, 32, 15, 00, 17, 43},
                    /**11*/{22, 49, 87, 49, 49, 49, 87, 49, 56, 17, 00, 15},
                    /**12*/{56, 65, 22, 27, 49, 49, 77, 55, 32, 43, 15, 00}
            };


    public static final int FITNESS_MATRIZ_09_CIDADES = 120;

    public static final int[][] MATRIZ_09_CIDADES =
            new int[][]{
                    /**     01  02  03  04  05  06  07  08  09 */
                    /**1**/{00, 7, 23, 87, 78, 32, 87, 8, 22},
                    /**2**/{7, 00, 43, 87, 24, 87, 8, 99, 87},
                    /**3**/{23, 43, 00, 24, 10, 14, 87, 64, 43},
                    /**4**/{87, 87, 24, 00, 9, 34, 43, 87, 55},
                    /**5**/{78, 24, 10, 9, 00, 87, 99, 43, 33},
                    /**6**/{32, 87, 14, 34, 87, 00, 6, 45, 47},
                    /**7**/{87, 8, 87, 43, 99, 06, 00, 54, 87},
                    /**8**/{8, 99, 64, 87, 43, 45, 54, 00, 11},
                    /**9**/{22, 87, 43, 55, 33, 47, 87, 11, 00}
            };


    public static final int FITNESS_MATRIZ_06_CIDADES = 110;

    public static final int[][] MATRIZ_06_CIDADES =
            new int[][]{
                    /**     01  02  03  04  05  06  */
                    /**1**/{00, 7, 23, 87, 78, 32},
                    /**2**/{7, 00, 43, 87, 24, 87},
                    /**3**/{23, 43, 00, 24, 10, 14},
                    /**4**/{87, 87, 24, 00, 9, 34},
                    /**5**/{78, 24, 10, 9, 00, 87},
                    /**6**/{32, 87, 14, 34, 87, 00}
            };


    public static final int FITNESS_MATRIZ_06_CIDADES_TESTE = 21;

    public static final int[][] MATRIZ_06_CIDADES_TESTE =
            new int[][]{
                    /**     01  02  03  04  05  06  */
                    /**1**/{00, 1, 23, 87, 78, 32, 06},
                    /**2**/{1, 00, 2, 87, 24, 87, 12},
                    /**3**/{23, 2, 00, 3, 10, 14, 23},
                    /**4**/{87, 87, 3, 00, 4, 34, 87},
                    /**5**/{78, 24, 10, 4, 00, 5, 78},
                    /**6**/{32, 87, 14, 34, 5, 00, 32},
                    /**6**/{06, 12, 23, 87, 78, 32, 00},
            };


    public static int[][] getMatriz(int TAM) {

        int numero;
        int[][] matriz = new int[TAM][TAM];
        Random aleatorio = new Random();

        for (int i = 0; i < matriz.length; i++) {

            for (int j = 0; j < matriz.length; j++) {
                numero = aleatorio.nextInt(100) + 200;
                matriz[i][j] = numero;
            }
        }

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (j == i) {
                    matriz[j][i] = 0;
                }
            }
        }

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 1; j < matriz.length; j++) {
                if (j == i) {
                    matriz[j - 1][i] = i;
                }
            }
        }

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 1; j < matriz.length; j++) {
                matriz[j][i] = matriz[i][j];
            }
        }

        for (int i = 0; i < matriz.length; i++) {
            for (int[] aMatriz : matriz) {
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
        return matriz;
    }

    public static int getFitness(int TAM) {

        int cont = 0;
        for (int i = 0; i < TAM; i++) {
            cont = cont + i;
        }

        return cont;
    }

}