import modelo.Livro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ListaNomesDosLivros {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("criteria");
        final EntityManager manager = factory.createEntityManager();
        final CriteriaBuilder cb = manager.getCriteriaBuilder();
        final CriteriaQuery<String> c = cb.createQuery(String.class);
        final Root<Livro> livro = c.from(Livro.class);
        c.select(livro.<String>get("nome"));
        final TypedQuery<String> query = manager.createQuery(c);
        final List<String> nomes = query.getResultList();
        for (String nome : nomes) {
            System.out.println("Livro: " + nome);
        }
        manager.close();
        factory.close();
    }
}
