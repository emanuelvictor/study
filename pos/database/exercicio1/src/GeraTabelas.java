import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GeraTabelas {

    public static void main(final String[] args) {

        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("livraria");
        factory.close();

    }
}
