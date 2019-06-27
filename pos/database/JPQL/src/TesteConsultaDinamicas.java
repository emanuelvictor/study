import modelo.Autor;
import modelo.Livro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

public class TesteConsultaDinamicas {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpql");
        final EntityManager manager = factory.createEntityManager();
        final Query query = manager.createQuery("select a from Autor a");
        final List<Autor> autores = query.getResultList();
        for (final Autor autor : autores) {
            System.out.println("Autor: " + autor.getNome());
            final Collection<Livro> livros = autor.getLivros();
            for (final Livro livro : livros) {
                System.out.println("Livro: " + livro.getNome());
                System.out.println("Preço: " + livro.getPreco());
                System.out.println();
            }
            System.out.println();
        }
        manager.close();
        factory.close();
    }
}
