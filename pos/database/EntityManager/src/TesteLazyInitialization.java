import modelo.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteLazyInitialization {
    public static void main(String[] args) {
        final EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("entity_manager");
        final EntityManager manager = factory.createEntityManager();
        // OBJETO CARREGADO EM MODO LAZY
        final Pessoa p = manager.getReference(Pessoa.class, 1L);
        manager.close();
        factory.close();
        System.out.println(p.getNome());
    }
}
