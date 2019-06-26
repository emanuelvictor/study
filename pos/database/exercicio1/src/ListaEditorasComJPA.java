import entities.Editora;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ListaEditorasComJPA {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("livraria");
        final EntityManager manager = factory.createEntityManager();
        final Query query = manager.createQuery("SELECT e FROM Editora e");
        final List<Editora> editoras = query.getResultList();
        for (Editora e : editoras) {
            System.out.println("EDITORA: " + e.getNome() + " - " + e.getEmail());
        }
    }
}
