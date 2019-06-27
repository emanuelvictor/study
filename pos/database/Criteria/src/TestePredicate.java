import modelo.Livro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class TestePredicate {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("criteria");
        final EntityManager manager = factory.createEntityManager();
        final CriteriaBuilder cb = manager.getCriteriaBuilder();
        final CriteriaQuery<Livro> c = cb.createQuery(Livro.class);
        final Root<Livro> l = c.from(Livro.class);
        c.select(l);
        final Predicate predicate = cb.equal(l.get("nome"), "The Battle for Your Mind");
        c.where(predicate);
        final TypedQuery<Livro> query = manager.createQuery(c);
        final List<Livro> livros = query.getResultList();
        for (Livro livro : livros) {
            System.out.println(livro.getId());
            System.out.println(livro.getNome());
            System.out.println(livro.getPreco());
        }
    }
}
