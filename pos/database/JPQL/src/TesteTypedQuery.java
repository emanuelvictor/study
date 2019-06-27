import modelo.Autor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class TesteTypedQuery {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpql");
        final EntityManager manager = factory.createEntityManager();
        final TypedQuery<Autor> query = manager.createNamedQuery("Autor.findAll", Autor.class);
        final List<Autor> autores = query.getResultList();
        for (final Autor autor : autores) {
            System.out.println("Autor: " + autor.getNome());
        }
        manager.close();
        factory.close();
    }
}
