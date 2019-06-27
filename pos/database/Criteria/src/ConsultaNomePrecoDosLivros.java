import modelo.Livro;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ConsultaNomePrecoDosLivros {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("criteria");
        final EntityManager manager = factory.createEntityManager();
        final CriteriaBuilder cb = manager.getCriteriaBuilder();
        final CriteriaQuery<Tuple> c = cb.createQuery(Tuple.class);
        final Root<Livro> l = c.from(Livro.class);
        c.multiselect(l.<String>get("nome").alias("livro.nome"), l.<Double>get("preco").alias("livro.preco"));
        final TypedQuery<Tuple> query = manager.createQuery(c);
        final List<Tuple> resultado = query.getResultList();
        for (final Tuple registro : resultado) {
            System.out.println("Livro: " + registro.get("livro.nome"));
            System.out.println("Preço: " + registro.get("livro.preco"));
        }
        manager.close();
        factory.close();
    }
}
