package algorithm;


import algorithm.memetic.Memetic;
import algorithm.memetic.OldMemetic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import static others.threads.ThreadComponent.pool;

/**
 * Algoritmo Genético implementado por Emanuel Victor e Haroldo Ramirez em 29/09/14.
 * Análise da influência da mutação nos crossovers OX e PMX.
 * Classe que executa os testes
 */
public class Executor {


    public static final int[][] MATRIX = new int[][]
            {
                    {0, 26, 31, 34, 41, 50, 56, 37, 56, 58, 58, 39, 52, 39, 50, 34, 57, 31, 37, 41, 32, 56, 35, 35, 41, 37, 40, 42, 43, 12},
                    {26, 0, 5, 42, 37, 55, 59, 59, 46, 35, 49, 58, 41, 59, 55, 52, 45, 44, 35, 51, 54, 35, 49, 31, 31, 50, 53, 45, 40, 41},
                    {31, 5, 0, 28, 52, 34, 43, 31, 56, 49, 38, 31, 49, 48, 37, 46, 55, 39, 33, 58, 51, 31, 32, 46, 57, 50, 48, 39, 45, 50},
                    {34, 42, 28, 0, 2, 43, 55, 48, 45, 50, 47, 53, 31, 55, 46, 37, 52, 40, 52, 58, 32, 55, 43, 52, 39, 44, 52, 41, 44, 46},
                    {41, 37, 52, 2, 0, 18, 41, 34, 45, 39, 58, 56, 36, 39, 36, 33, 50, 42, 43, 34, 35, 45, 45, 34, 54, 52, 41, 51, 46, 56},
                    {50, 55, 34, 43, 18, 0, 25, 33, 35, 32, 47, 47, 36, 34, 44, 55, 57, 37, 57, 56, 35, 50, 56, 38, 50, 35, 32, 52, 46, 36},
                    {56, 59, 43, 55, 41, 25, 0, 24, 40, 32, 39, 48, 41, 46, 34, 45, 53, 59, 50, 49, 37, 37, 45, 59, 34, 45, 42, 34, 47, 44},
                    {37, 59, 31, 48, 34, 33, 24, 0, 9, 54, 56, 51, 37, 41, 36, 44, 43, 56, 38, 47, 58, 53, 44, 52, 43, 56, 43, 59, 56, 59},
                    {56, 46, 56, 45, 45, 35, 40, 9, 0, 25, 53, 47, 57, 46, 59, 50, 44, 35, 39, 37, 33, 48, 40, 31, 31, 38, 45, 34, 34, 32},
                    {58, 35, 49, 50, 39, 32, 32, 54, 25, 0, 20, 53, 31, 42, 46, 41, 52, 41, 41, 49, 47, 33, 37, 38, 50, 55, 49, 50, 44, 58},
                    {58, 49, 38, 47, 58, 47, 39, 56, 53, 20, 0, 26, 31, 34, 52, 31, 43, 38, 56, 42, 58, 37, 31, 56, 33, 39, 34, 53, 36, 35},
                    {39, 58, 31, 53, 56, 47, 48, 51, 47, 53, 26, 0, 22, 37, 56, 40, 36, 43, 53, 51, 57, 45, 35, 42, 49, 41, 34, 31, 55, 47},
                    {52, 41, 49, 31, 36, 36, 41, 37, 57, 31, 31, 22, 0, 7, 56, 41, 40, 52, 49, 51, 46, 50, 59, 50, 49, 50, 56, 31, 49, 50},
                    {39, 59, 48, 55, 39, 34, 46, 41, 46, 42, 34, 37, 7, 0, 10, 43, 35, 41, 41, 55, 33, 44, 59, 58, 48, 38, 47, 41, 59, 53},
                    {50, 55, 37, 46, 36, 44, 34, 36, 59, 46, 52, 56, 56, 10, 0, 8, 55, 48, 50, 55, 45, 57, 48, 37, 58, 53, 54, 54, 47, 58},
                    {34, 52, 46, 37, 33, 55, 45, 44, 50, 41, 31, 40, 41, 43, 8, 0, 25, 53, 50, 31, 32, 31, 51, 37, 41, 50, 38, 43, 53, 53},
                    {57, 45, 55, 52, 50, 57, 53, 43, 44, 52, 43, 36, 40, 35, 55, 25, 0, 10, 38, 52, 34, 52, 52, 55, 52, 50, 57, 46, 53, 55},
                    {31, 44, 39, 40, 42, 37, 59, 56, 35, 41, 38, 43, 52, 41, 48, 53, 10, 0, 5, 44, 49, 41, 55, 45, 42, 35, 36, 39, 44, 31},
                    {37, 35, 33, 52, 43, 57, 50, 38, 39, 41, 56, 53, 49, 41, 50, 50, 38, 5, 0, 9, 42, 31, 32, 49, 50, 48, 54, 58, 56, 31},
                    {41, 51, 58, 58, 34, 56, 49, 47, 37, 49, 42, 51, 51, 55, 55, 31, 52, 44, 9, 0, 4, 34, 36, 38, 35, 41, 37, 54, 34, 31},
                    {32, 54, 51, 32, 35, 35, 37, 58, 33, 47, 58, 57, 46, 33, 45, 32, 34, 49, 42, 4, 0, 15, 33, 35, 51, 49, 46, 57, 40, 51},
                    {56, 35, 31, 55, 45, 50, 37, 53, 48, 33, 37, 45, 50, 44, 57, 31, 52, 41, 31, 34, 15, 0, 4, 32, 52, 48, 56, 39, 58, 56},
                    {35, 49, 32, 43, 45, 56, 45, 44, 40, 37, 31, 35, 59, 59, 48, 51, 52, 55, 32, 36, 33, 4, 0, 26, 44, 58, 31, 40, 39, 32},
                    {35, 31, 46, 52, 34, 38, 59, 52, 31, 38, 56, 42, 50, 58, 37, 37, 55, 45, 49, 38, 35, 32, 26, 0, 22, 45, 40, 39, 31, 37},
                    {41, 31, 57, 39, 54, 50, 34, 43, 31, 50, 33, 49, 49, 48, 58, 41, 52, 42, 50, 35, 51, 52, 44, 22, 0, 22, 53, 44, 55, 40},
                    {37, 50, 50, 44, 52, 35, 45, 56, 38, 55, 39, 41, 50, 38, 53, 50, 50, 35, 48, 41, 49, 48, 58, 45, 22, 0, 7, 40, 42, 57},
                    {40, 53, 48, 52, 41, 32, 42, 43, 45, 49, 34, 34, 56, 47, 54, 38, 57, 36, 54, 37, 46, 56, 31, 40, 53, 7, 0, 22, 48, 36},
                    {42, 45, 39, 41, 51, 52, 34, 59, 34, 50, 53, 31, 31, 41, 54, 43, 46, 39, 58, 54, 57, 39, 40, 39, 44, 40, 22, 0, 0, 52},
                    {43, 40, 45, 44, 46, 46, 47, 56, 34, 44, 36, 55, 49, 59, 47, 53, 53, 44, 56, 34, 40, 58, 39, 31, 55, 42, 48, 0, 0, 2},
                    {12, 41, 50, 46, 56, 36, 44, 59, 32, 58, 35, 47, 50, 53, 58, 53, 55, 31, 31, 31, 51, 56, 32, 37, 40, 57, 36, 52, 2, 0}
            };

    public static void main(final String[] args) /*throws IOException, InterruptedException */ {

        main();

    }

    public static void main() {
        Matrix.getInstance().generateMatrix(5, false);
//        Matrix.getInstance().setMatrix(MATRIX);
//        Matrix.getInstance().print();

        final AtomicLong sumMemetic = new AtomicLong();
        final AtomicLong sumOldMemetic = new AtomicLong();

        int sizeOfPopulation = 2;

        new Memetic().execute("\n " + 0 + " memetic done", Memetic.generateRandomPopulation(sizeOfPopulation, Matrix.getInstance().getMatrix()), 0);
//        final Runnable runnable0 = () -> sumMemetic.set(sumMemetic.get() + new Memetic().execute("\n " + 0 + " memetic done", Memetic.generateRandomPopulation(sizeOfPopulation, Matrix.getInstance().getMatrix()), 0));
//        final Runnable runnable1 = () -> sumMemetic.set(sumMemetic.get() + new Memetic().execute("\n " + 1 + " memetic done", Memetic.generateRandomPopulation(sizeOfPopulation, Matrix.getInstance().getMatrix()), 1));
//        final Runnable runnable2 = () -> sumMemetic.set(sumMemetic.get() + new Memetic().execute("\n " + 2 + " memetic done", Memetic.generateRandomPopulation(sizeOfPopulation, Matrix.getInstance().getMatrix()), 2));
//        final Runnable runnable3 = () -> sumMemetic.set(sumMemetic.get() + new Memetic().execute("\n " + 3 + " memetic done", Memetic.generateRandomPopulation(sizeOfPopulation, Matrix.getInstance().getMatrix()), 3));
//
//        final Runnable oldMemetic = () -> sumOldMemetic.set(sumOldMemetic.get() + new OldMemetic().execute("\n Old Memétic done", Memetic.generateRandomPopulation(sizeOfPopulation, Matrix.getInstance().getMatrix())));
//
//        pool(8)
//                .execute(/*oldMemetic,*/ runnable0)
//                .block()
//                .then(o0 -> {
////                    Matrix.getInstance().resetCount();
//                });
//
//        pool(8)
//                .execute(oldMemetic, runnable1)
//                .block()
//                .then(o1 -> {
////                    Matrix.getInstance().resetCount();
//                });
//
//        pool(10)
//                .execute(oldMemetic, runnable2)
//                .block()
//                .then(o2 -> {
////                    Matrix.getInstance().resetCount();
//                });
//
//        pool(10)
//                .execute(oldMemetic, runnable3)
//                .block()
//                .then(o3 -> {
////                    Matrix.getInstance().resetCount();
//                });


        System.out.println(sumMemetic.get());
        System.out.println(sumOldMemetic.get());
        System.exit(-1);
    }

    static int[][] copy(int[][] array) {
        final int[][] auxArray = new int[array.length][array.length];
        System.arraycopy(array, 0, auxArray, 0, auxArray.length);
        return auxArray;
    }
}