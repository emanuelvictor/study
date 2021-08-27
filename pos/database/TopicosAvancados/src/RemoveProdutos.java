
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class RemoveProdutos {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("topicos_avancados");
        final EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        final Query query = manager.createQuery("DELETE Produto p WHERE p.preco < 50");
        query.executeUpdate();
        manager.getTransaction().commit();
        manager.close();
        factory.close();
    }
}
