package padrao.composer.element;

import java.util.ArrayList;
import java.util.List;

public class Session extends Element {
    List<Element> elements = new ArrayList<>();

    public Session(String name) {
        this.name = name;
    }

    @Override
    public void exibir() {
        System.out.println(this.getName());
        this.elements.forEach(element -> element.exibir());
    }

    public void adicionar(Element element) {
        this.elements.add(element);
    }

    public void remover(Element element) {
        elements.remove(element);
    }
}
