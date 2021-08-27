import modelo.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteFind {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("entity_manager");
        final EntityManager manager = factory.createEntityManager();

        // OBJETO NO ESTADO MANAGED
        final Pessoa p = manager.find(Pessoa.class, 1L);
        System.out.println("Id: " + p.getId());
        System.out.println("Nome: " + p.getNome());
        manager.close();
        factory.close();
    }
}
