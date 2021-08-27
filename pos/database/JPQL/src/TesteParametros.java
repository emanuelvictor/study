import modelo.Livro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class TesteParametros {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpql");
        final EntityManager manager = factory.createEntityManager();
        final Query query = manager.createNamedQuery("Livro.findByPrecoMinimo");
        query.setParameter("preco", 20.0);
        final List<Livro> livros = query.getResultList();
        for (final Livro livro : livros) {
            System.out.println("Nome: " + livro.getNome());
            System.out.println("Preço: " + livro.getPreco());
        }
        manager.close();
        factory.close();
    }
}
