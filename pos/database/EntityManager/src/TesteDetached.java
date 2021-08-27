import modelo.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteDetached {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("entity_manager");
        final EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        // OBJETO NO ESTADO MANAGED
        final Pessoa p = manager.find(Pessoa.class, 1L);
        // OBJETO NO ESTADO DETACHED
        manager.detach(p);
        // ALTERANDO O CONTEUDO DO OBJETO
        p.setNome("Marcelo Martins");
        // SINCRONIZANDO E CONFIRMANDO A TRANSACAO
        manager.getTransaction().commit();
        manager.close();
        factory.close();
    }
}
