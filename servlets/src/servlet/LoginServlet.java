package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private PrintWriter out = null;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        this.out = response.getWriter();

        header();

        html("<h2>Efetuar login</h2>");

        html("<form method='POST' action='login' style='width: 700px!important'>");

        html("<div class='field'>");
        html("<label class='label'>Nome de Usuário</label>");
        html("<input type='text' name='usuario' class='input' />");
        html("</div>");

        html("<div class='field'>");
        html("<label class='label'>Senha</label>");
        html("<input type='password' name='senha' class='input'/>");
        html("</div>");

        html("<input type='hidden' name='redirect' class='input' value=\"" + request.getQueryString() + "\"/>");

        html("<button class='button is-large is-primary'>Salvar</button>");

        html("</form>");

        html("<hr />");

        footer();

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String usuario = request.getParameter("usuario");
        final String senha = request.getParameter("senha");

        final String redirect = request.getParameter("redirect");

        if (usuario != null && senha != null && usuario.equals("admin") && senha.equals("admin")) {

            request.getSession().setAttribute("logado", true);
            request.getSession().setAttribute("usuario", "admin");

            if (redirect != null && !redirect.equals("null"))
                response.sendRedirect(redirect);
            else
                response.sendRedirect("\"#");

        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().println("Login ou senha incorretos");
        }
    }
}
