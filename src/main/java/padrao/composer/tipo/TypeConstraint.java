package padrao.composer.tipo;

import padrao.composer.constraint.Constraint;

/**
 * Classe associativa entre tipos (Type) e restrições (Constraint)
 */
public class TypeConstraint {

    Constraint constraint;

    Type type;

    public Constraint getConstraint() {
        return constraint;
    }

    public void setConstraint(Constraint constraint) {
        this.constraint = constraint;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
