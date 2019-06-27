import modelo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class TesteConsultaNativas {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("topicos_avancados");
        final EntityManager manager = factory.createEntityManager();
        final String sql = "SELECT * FROM Produto";
        final Query nativeQuery = manager.createNativeQuery(sql, Produto.class);
        final List<Produto> produtos = nativeQuery.getResultList();
        for (final Produto p : produtos) {
            System.out.println(p.getNome());
        }
    }
}
