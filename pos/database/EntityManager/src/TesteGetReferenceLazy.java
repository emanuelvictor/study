import modelo.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteGetReferenceLazy {
    public static void main(String[] args) {
        final EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("entity_manager");
        final EntityManager manager = factory.createEntityManager();
        System.out.println("-----------CHAMANDO O GETREFERENCE----");
        final Pessoa p = manager.getReference(Pessoa.class, 1L);
        System.out.println("-----------NAO FEZ O SELECT-----------");
        manager.close();
        factory.close();
    }
}
