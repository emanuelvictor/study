package patterns.composer;

import patterns.composer.field.Field;
import patterns.composer.element.Session;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        Session blocoA = new Session("A) Formação Educacional");
        Field field1 = new Field("Qual sua formação?");
        Field field2 = new Field("Pretende fazer alguma pós-graduação?");

        blocoA.adicionar(field1);
        blocoA.adicionar(field2);

        Session blocoB = new Session("B) Habilidades");
        Session blocoB1 = new Session("B1) Habilidades Técnicas");
        Field field3 = new Field("O que é SOLID?");

        blocoB1.adicionar(field3);
        blocoB.adicionar(blocoB1);

        Session blocoRaiz = new Session("Inicio");
        blocoRaiz.adicionar(blocoA);
        blocoRaiz.adicionar(blocoB);

// exibindo toda a estrutura
        blocoRaiz.exibir();
    }

}