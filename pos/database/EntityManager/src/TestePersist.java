import modelo.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestePersist {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("entity_manager");
        final EntityManager manager = factory.createEntityManager();
        // ABRINDO A TRASACAO
        manager.getTransaction().begin();
        // OBJETO NO ESTADO NEW
        final Pessoa p = new Pessoa();
        p.setNome("Rafael Cosentino");
        // OBJETO NO ESTADO MANAGED
        manager.persist(p);
        // SINCRONIZANDO E CONFIRMANDO A TRANSACAO
        manager.getTransaction().commit();
        System.out.println("Pessoa id: " + p.getId());
        manager.close();
        factory.close();
    }
}
