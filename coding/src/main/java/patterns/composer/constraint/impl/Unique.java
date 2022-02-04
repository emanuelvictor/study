package patterns.composer.constraint.impl;

import patterns.composer.constraint.Constraint;

/**
 * Restrição de singularidade
 */
public class Unique extends Constraint {
    boolean unique;

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }
}
