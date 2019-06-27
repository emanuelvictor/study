import modelo.Livro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class TesteBuscaPaginada {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpql");
        final EntityManager manager = factory.createEntityManager();
        final TypedQuery<Livro> query = manager.createQuery("select livro from Livro livro",Livro.class);
        query.setFirstResult(2);
        query.setMaxResults(3);
        final List<Livro> livros = query.getResultList();
        for (final Livro livro : livros) {
            System.out.println("Livro: " + livro.getNome());
        }
        manager.close();
        factory.close();
    }
}
