package padrao.composer.field;

import padrao.composer.tipo.TypeConstraint;

/**
 * Classe associativa entre Campo (Field) e TypeConstraint
 */
public class FieldTypeConstraint {

    TypeConstraint typeConstraint;

    Field field;

    public TypeConstraint getTypeConstraint() {
        return typeConstraint;
    }

    public void setTypeConstraint(TypeConstraint typeConstraint) {
        this.typeConstraint = typeConstraint;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
