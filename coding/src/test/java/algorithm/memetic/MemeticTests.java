package algorithm.memetic;

import algorithm.Matrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 *
 */
public class MemeticTests {

    private static final int[][] CONVERGE_MATRIX = new int[][]
            {
                    {0, 4, 19, 17, 11, 19, 12, 18, 13, 3},
                    {4, 0, 2, 16, 18, 11, 12, 11, 16, 11},
                    {19, 2, 0, 5, 14, 11, 14, 16, 16, 16},
                    {17, 16, 5, 0, 3, 13, 19, 13, 12, 14},
                    {11, 18, 14, 3, 0, 7, 19, 16, 14, 14},
                    {19, 11, 11, 13, 7, 0, 4, 14, 13, 18},
                    {12, 12, 14, 19, 19, 4, 0, 2, 16, 14},
                    {18, 11, 16, 13, 16, 14, 2, 0, 9, 16},
                    {13, 16, 16, 12, 14, 13, 16, 9, 0, 9},
                    {3, 11, 16, 14, 14, 18, 14, 16, 9, 0}
            };

    private static final int[][] CONVERGE_MATRIX_WITH_5_CITIES = new int[][]
            {
                    {0, 4, 6, 7, 4},
                    {4, 0, 3, 6, 9},
                    {6, 3, 0, 1, 8},
                    {7, 6, 1, 0, 4},
                    {4, 9, 8, 4, 0},
            };

    /**
     *
     */
    @Test
    public void convergeTest() {

        // Prepare
        Matrix.getInstance().setMatrix(CONVERGE_MATRIX);
        boolean converged = false;

        int[] melhor = new int[]{1, 3, 4, 5, 2, 6, 7, 8, 9, 0};
        int[] individuo = new int[]{1, 8, 9, 0, 3, 4, 5, 2, 6, 7};

        // Run
        for (int m = 0; m < melhor.length; m++) {
            for (int i = 0; i < individuo.length; i++) {
                if (Arrays.equals(Memetic.rotate(melhor), Memetic.rotate(individuo))) {
                    System.out.println("CONVERGE");
                    converged = true;
                    break;
                }
                System.out.println(m + " - " + i);
                Memetic.learn(individuo, i, melhor, m);

                Memetic.print(melhor);
                Memetic.print(individuo);
            }
        }

        // Assertions
        Assertions.assertTrue(converged);
    }

    @Test
    public void convergeWithoutRotateTest() {

        // Prepare
        Matrix.getInstance().setMatrix(CONVERGE_MATRIX);
        boolean converged = false;

        int[] melhor = new int[]{1, 3, 4, 5, 2, 6, 7, 8, 9, 0};
        int[] individuo = new int[]{1, 8, 9, 0, 3, 4, 5, 2, 6, 7};

        // Run
        for (int m = 0; m < melhor.length - 1; m++) {
            for (int i = 0; i < individuo.length - 1; i++) {
                if (Arrays.equals(Memetic.rotate(melhor), Memetic.rotate(individuo))) {
                    System.out.println("CONVERGE");
                    converged = true;
                    break;
                }
                System.out.println(m + " - " + i);
                Memetic.learn(individuo, i, melhor, m);

                Memetic.print(melhor);
                Memetic.print(individuo);
            }
        }

        // Assertions
        Assertions.assertFalse(converged);
    }

    @Test
    public void asdfadfa() {

        // Prepare
        Matrix.getInstance().setMatrix(CONVERGE_MATRIX_WITH_5_CITIES);
        boolean converged = false;

        int[] melhor = new int[]{3, 4, 0, 1, 2};
        int[] individuo = new int[]{2, 4, 0, 1, 3};

        // Run
        for (int im = 0; im < melhor.length; im++) {
            for (int ii = 0; ii < individuo.length; ii++) {
                if(melhor[im] == individuo[ii]){
                    Memetic.learn(individuo, ii, melhor, im);
                    break;
                }
            }

            if (Arrays.equals(Memetic.rotate(melhor), Memetic.rotate(individuo))) {
                System.out.println("CONVERGE");
                converged = true;
                break;
            }
        }

        // Assertions
        Assertions.assertTrue(converged);
    }

    /**
     *
     */
    @Test
    public void getWorstIdeaFromIndividualTest() {

        // Prepare
        Matrix.getInstance().setMatrix(CONVERGE_MATRIX);

        final int[] firstIndividual = new int[]{1, 3, 4, 5, 2, 6, 7, 8, 9, 0};
        final int[] secondIndividual = new int[]{1, 8, 9, 0, 3, 4, 5, 2, 6, 7};

        // Run
        final int indexOfFirstIndividual = Memetic.getWorstIdea(firstIndividual);
        final int indexOfSecondIndividual = Memetic.getWorstIdea(secondIndividual);

        // Assertions
        Assertions.assertEquals(0, indexOfFirstIndividual);
        Assertions.assertEquals(3, indexOfSecondIndividual);
    }

    /**
     *
     */
    @Test
    public void getWorstIdeaFromIndividualWithExcludesTest() {

        // Prepare
        Matrix.getInstance().setMatrix(CONVERGE_MATRIX);

        final int[] individual = new int[]{1, 3, 4, 5, 2, 6, 7, 8, 9, 0};
        int[] indexes = new int[]{};

        // Run
        for (int i = 0; i < individual.length; i++) {
            final int[] auxIndexes = new int[indexes.length + 1];
            System.arraycopy(indexes, 0, auxIndexes, 0, auxIndexes.length - 1);
            auxIndexes[i] = Memetic.getWorstIdea(individual, indexes);
            indexes = auxIndexes;
        }

        // Assertions
        final int[] expected = new int[]{0, 4, 3, 6, 7, 2, 9, 1, 8, 5};
        for (int i = 0; i < indexes.length; i++) {
            Assertions.assertEquals(indexes[i], expected[i]);
        }
    }
}
