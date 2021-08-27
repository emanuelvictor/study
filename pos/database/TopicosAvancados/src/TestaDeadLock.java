import modelo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;

public class TestaDeadLock {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("K21_topicos_avancados");
        final EntityManager manager1 = factory.createEntityManager();
        final EntityManager manager2 = factory.createEntityManager();
        manager1.getTransaction().begin();
        manager2.getTransaction().begin();
        manager1.find(Produto.class, 100L, LockModeType.PESSIMISTIC_WRITE);
        manager2.find(Produto.class, 100L, LockModeType.PESSIMISTIC_WRITE);
        manager1.getTransaction().commit();
        manager2.getTransaction().commit();
        manager1.close();
        manager2.close();
        factory.close();
    }
}
