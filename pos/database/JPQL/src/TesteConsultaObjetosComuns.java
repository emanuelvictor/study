import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class TesteConsultaObjetosComuns {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpql");
        final EntityManager manager = factory.createEntityManager();
        final TypedQuery<String> query = manager.createQuery("select livro.nome from Livro livro", String.class);
        final List<String> nomes = query.getResultList();
        for (final String nome : nomes) {
            System.out.println(nome);
        }
        manager.close();
        factory.close();
    }
}
