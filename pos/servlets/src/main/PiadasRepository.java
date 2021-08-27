package main;

import main.domain.Piada;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class PiadasRepository {


    private PiadasRepository() {
    }

    private static class PiadasDaoHolder {
        static final PiadasRepository SINGLE_INSTANCE;

        static {
            SINGLE_INSTANCE = new PiadasRepository();
        }
    }

    public static PiadasRepository getInstance() {
        return PiadasDaoHolder.SINGLE_INSTANCE;
    }

    public List<Piada> getTodasAsPiadas() {
        final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jpa01");
        final EntityManager em = emFactory.createEntityManager();

        final List<Piada> lista = em.createQuery("SELECT p FROM Piada p").getResultList();
        em.close();

        return lista;
    }

    public List<Piada> getTodasAsPiadas(final String filtro) {
        final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jpa01");
        final EntityManager em = emFactory.createEntityManager();

        final List<Piada> lista = em.createQuery("SELECT p FROM Piada p WHERE lower(p.conteudo) like lower(:filtro)")
                .setParameter("filtro", "%" + filtro + "%")
                .getResultList();
        em.close();

        return lista;
    }
}
