package patterns.composer.constraint.impl;

import patterns.composer.constraint.Constraint;

/**
 * Restrição de obrigatoriedade
 */
public class Required extends Constraint {
    boolean required;

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
