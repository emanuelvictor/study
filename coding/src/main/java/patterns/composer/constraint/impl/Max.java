package patterns.composer.constraint.impl;

import patterns.composer.constraint.Constraint;

/**
 * Restrição de tamanho, quantidade de alternativas, ou comprimento máximo
 */
public class Max extends Constraint {
    short max;

    public short getMax() {
        return max;
    }

    public void setMax(short max) {
        this.max = max;
    }
}
