import entities.Cliente;
import entities.Pedido;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Calendar;

public class AdicionaPedidoCliente {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("mapeamento");
        final EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        final Cliente c = new Cliente();
        c.setNome("Rafael Cosentino");
        final Pedido p = new Pedido();
        p.setData(Calendar.getInstance());
        p.setCliente(c);
        manager.persist(c);
        manager.persist(p);
        manager.getTransaction().commit();
        manager.close();
        factory.close();
    }
}
