package padrao.composer.constraint.impl;

import padrao.composer.constraint.Constraint;

/**
 * Restrição de tamanho, quantidade de alternativas, ou comprimento mínimo
 */
public class Min extends Constraint {
    short min;

    public short getMin() {
        return min;
    }

    public void setMin(short min) {
        this.min = min;
    }
}
