import entities.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Calendar;

public class AdicionaUsuario {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("mapeamento");
        final EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        final Usuario usuario = new Usuario();
        usuario.setEmail("contato@utfpr.edu.br");
        usuario.setDataDeCadastro(Calendar.getInstance());
        manager.persist(usuario);
        manager.getTransaction().commit();
        manager.close();
        factory.close();
    }
}
