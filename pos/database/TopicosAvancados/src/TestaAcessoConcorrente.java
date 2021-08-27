import modelo.Conta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestaAcessoConcorrente {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("topicos_avancados");
        final EntityManager manager1 = factory.createEntityManager();
        final EntityManager manager2 = factory.createEntityManager();
        manager1.getTransaction().begin();
        manager2.getTransaction().begin();
        final Conta conta1 = manager1.find(Conta.class, 1L);
        conta1.setSaldo(conta1.getSaldo() + 500);
        final Conta conta2 = manager2.find(Conta.class, 1L);
        conta2.setSaldo(conta2.getSaldo() - 500);
        manager1.getTransaction().commit();
        manager2.getTransaction().commit();
        manager1.close();
        factory.close();
    }
}
