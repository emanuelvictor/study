import entities.Autor;
import entities.Livro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AdicionaLivroAutor {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("mapeamento");
        final EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        final Autor a = new Autor();
        a.setNome("Rafael Cosentino");
        final Livro l = new Livro();
        l.setNome("JPA2");
        l.getAutores().add(a);
        manager.persist(a);
        manager.persist(l);
        manager.getTransaction().commit();
        manager.close();
        factory.close();
    }
}
