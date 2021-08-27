package main;

import main.domain.Cidade;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/cidades", "/"})
public class ListaCidades extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        EntityManager em = Persistence.createEntityManagerFactory("jpa01").createEntityManager();
        List <Cidade> lista = em.createQuery("SELECT c FROM Cidade c ORDER BY c.nome")
                                .getResultList();
        writer.println("<!doctype html>");
        writer.println("<html>");
        writer.println("    <head>");
        writer.println("        <title>PrimeiraServlet</title>");
        writer.println("    </head>");
        writer.println("    <body>");
        writer.println("        <h1>Servlets com JPA</h1>");
        writer.println("            <a href=\"menu\">Menu</a>");
        writer.println("        <ul>");
        for (Cidade c : lista) {
            writer.println("                <li>" + c.getNome() + "</li>");
        }
        em.close();
        writer.println("        </ul>");
        writer.println("    </body>");
        writer.println("</html>");
    }
}
