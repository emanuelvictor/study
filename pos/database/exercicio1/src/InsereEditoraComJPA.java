import entities.Editora;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class InsereEditoraComJPA {

    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("livraria");
        final EntityManager manager = factory.createEntityManager();
        final Editora novaEditora = new Editora();
        final Scanner entrada = new Scanner(System.in);
        System.out.println("Digite o nome da editora: ");
        novaEditora.setNome(entrada.nextLine());
        System.out.println("Digite o email da editora: ");
        novaEditora.setEmail(entrada.nextLine());
        manager.persist(novaEditora);
        manager.getTransaction().begin();
        manager.getTransaction().commit();
        factory.close();
    }

}
