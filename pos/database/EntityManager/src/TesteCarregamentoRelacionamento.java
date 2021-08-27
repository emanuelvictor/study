import modelo.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteCarregamentoRelacionamento {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("entity_manager");
        final EntityManager manager = factory.createEntityManager();
        final Estado estado = manager.find(Estado.class, 1L);
    }
}
