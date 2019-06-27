import modelo.Livro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CalculaMediaDosPrecosDosLivros {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("criteria");
        final EntityManager manager = factory.createEntityManager();
        final CriteriaBuilder cb = manager.getCriteriaBuilder();
        final CriteriaQuery<Double> c = cb.createQuery(Double.class);
        final Root<Livro> l = c.from(Livro.class);
        c.select(cb.avg(l.<Double>get("preco")));
        final TypedQuery<Double> query = manager.createQuery(c);
        final Double media = query.getSingleResult();
        System.out.println("Média: " + media);
        manager.close();
        factory.close();
    }
}
