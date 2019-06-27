import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TesteConsultaLivroPrecoMedio {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpql");
        final EntityManager manager = factory.createEntityManager();
        final TypedQuery<Double> query = manager.createQuery("select avg(livro.preco) from Livro livro", Double.class);
        final Double precoMedio = query.getSingleResult();
        System.out.println("Preço médio: " + precoMedio);
        manager.close();
        factory.close();
    }
}
