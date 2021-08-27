import entities.Departamento;
import entities.Endereco;
import entities.Funcionario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AdicionaDepartamentoFuncionario {
    public static void main(String[] args) {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("mapeamento");
        final EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        final Endereco e = new Endereco();
        e.setEstado("São Paulo");
        e.setCidade("São Paulo");
        e.setLogradouro("Av. Bigadeiro Faria Lima");
        e.setNumero(1571);
        final Funcionario f = new Funcionario();
        f.setNome("Rafael Cosentino");
        f.setEndereco(e);
        final Departamento d = new Departamento();
        d.setNome("Financeiro");
        d.getFuncionarios().add(f);
        manager.persist(f);
        manager.persist(d);
        manager.getTransaction().commit();
        manager.close();
        factory.close();
    }
}
