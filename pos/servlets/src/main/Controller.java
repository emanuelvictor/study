package main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ControllerServlet", urlPatterns = {"/"})
public class Controller extends HttpServlet {

    public Controller() {
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {

        if (request.getParameter("filtro") == null) {
            request.setAttribute("piadas", PiadasRepository.getInstance().getTodasAsPiadas());
            request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response);
        } else {
            request.setAttribute("piadas", PiadasRepository.getInstance().getTodasAsPiadas(request.getParameter("filtro")));
            request.setAttribute("filtro", request.getParameter("filtro"));
            request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response);
        }

    }
}
