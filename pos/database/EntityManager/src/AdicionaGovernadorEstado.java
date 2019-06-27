import modelo.Estado;
import modelo.Governador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AdicionaGovernadorEstado {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("entity_manager");
        final EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        final Governador governador = new Governador();
        governador.setNome("Rafael Cosentino");
        final Estado estado = new Estado();
        estado.setNome("São Paulo");
        governador.setEstado(estado);
        estado.setGovernador(governador);
        manager.persist(estado);
        manager.persist(governador);
        manager.getTransaction().commit();
        manager.close();
        factory.close();
    }
}
