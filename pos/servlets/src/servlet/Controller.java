package servlet;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "ControllerServlet", urlPatterns = {"/"})
public class Controller extends HttpServlet {

    public Controller() {
        super();
        try {
            EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jpa01");
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {


//        EntityManager em = emFactory.createEntityManager();
//        List<Piada> lista = em.createQuery("SELECT p FROM Piada p WHERE p.conteudo like :filtro")
//                .setParameter("filtro", "filtro" + "%")
//                .getResultList();
//        em.close();

        if (request.getParameter("filtro") == null) {
            request.setAttribute("piadas", PiadasRepository.getInstance().getTodasAsPiadas());
            request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response);
        } else {
            request.setAttribute("piadas", PiadasRepository.getInstance().getTodasAsPiadas().stream().filter(s -> s.getConteudo().contains(request.getParameter("filtro"))).collect(Collectors.toList()));
            request.setAttribute("filtro", request.getParameter("filtro"));
            request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response);
        }

    }
}
