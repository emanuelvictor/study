import modelo.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestePersistenceContext {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("entity_manager");
        final EntityManager manager = factory.createEntityManager();
        System.out.println("------------------PRIMEIRO FIND-----------------");
        Estado estado = manager.find(Estado.class, 1L);
        System.out.println("------------------SEGUNDO FIND------------------");
        estado = manager.find(Estado.class, 1L);
    }
}
