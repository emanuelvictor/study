import modelo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PopulaBanco {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("topicos_avancados");
        final EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        for (int i = 0; i < 100; i++) {
            final  Produto p = new Produto();
            p.setNome("produto " + i);
            p.setPreco(i * 10.0);
            manager.persist(p);
        }
        manager.getTransaction().commit();
        manager.close();
        factory.close();
    }
}
