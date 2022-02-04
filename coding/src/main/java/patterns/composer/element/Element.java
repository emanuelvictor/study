package patterns.composer.element;

public abstract class Element {

    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void exibir();
}
