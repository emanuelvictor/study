package algoritmo.genetico.domain.entity;

import algoritmo.genetico.Matrix;

public class Individual {
    int[] gens;
    int fitness;

    public void calculateFitness() {
        fitness = calculateFitness(gens);
    }

    public static int calculateFitness(final int[] gens) {
        int fitness = 0;
        for (int j = 0; j < gens.length; j++) {
            if (j + 1 != gens.length) {
                fitness = fitness + Matrix.getInstance().getMatrix()[gens[j]][gens[j + 1]];
            } else {
                fitness = fitness + Matrix.getInstance().getMatrix()[gens[j]][gens[0]];
            }
        }
        return fitness;
    }
}
