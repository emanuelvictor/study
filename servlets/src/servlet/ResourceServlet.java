package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ResourceServlet", urlPatterns = {"/servlet/resource"})
public class ResourceServlet extends HttpServlet {

    private PrintWriter out = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String usuario = (String) request.getSession().getAttribute("usuario");
        final Boolean logado = (Boolean) request.getSession().getAttribute("logado");
        if (usuario == null || !logado){
            response.sendRedirect("login?" + request.getServletPath().replace("/servlet/", ""));
            return;
        }
        this.out = response.getWriter();
        header();

        html("<h1>Acesso concedido</h1>");
        html("Recurso protegido");

        footer();
    }


    public void html(String conteudo) {
        this.out.println(conteudo);
    }

    public void header() {
        html("<!DOCTYPE html>");
        html("<html>");
        html("<head>");
        html("<title>Resource APP</title>");
        html("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        html("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.4/css/bulma.min.css\">");
        html("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/trix/1.0.0/trix.css\">");
        html("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/trix/1.0.0/trix.js\"></script>");
        html("</head>");
        html("<body style='margin-top: 50px'>");
        html("<div class='container content' style='width: 700px'>");
    }

    public void footer() {
        html("</div>");
        html("</body>");
        html("</html>");
    }
}
