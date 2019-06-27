import modelo.Conta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AdicionaConta {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("topicos_avancados");
        final EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        final Conta c = new Conta();
        c.setSaldo(2000);
        manager.persist(c);
        manager.getTransaction().commit();
        manager.close();
        factory.close();
    }
}
