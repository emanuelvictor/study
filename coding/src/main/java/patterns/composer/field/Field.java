package patterns.composer.field;

import patterns.composer.element.Element;
import patterns.composer.tipo.Type;

import java.util.List;

public class Field extends Element {

    /**
     * Armazena as alternativas do campo, caso ele seja do tipo  múltipla escolha
     */
    List<Field> alternatives;

    /**
     * Armazena o campo superior, caso o mesmo seja do tipo múltipla escolha
     */
    Field field;

    /**
     *
     */
    Type type;

    public Field(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public void exibir() {
        System.out.println(this.getName());
    }
}
