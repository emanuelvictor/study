package main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/menu"})
public class Menu extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.println("<!doctype html>");
        writer.println("<html>");
        writer.println("    <head>");
        writer.println("        <title>Menu</title>");
        writer.println("    </head>");
        writer.println("    <body>");
        writer.println("        <h1>Menu</h1>");
        writer.println("        <ul>");
        writer.println("            <li><a href=\"http://www.correios.com.br/para-voce\">Correios</a></li>");
        writer.println("            <li><a href=\"cidades\">Lista de cidades</a></li>");
        writer.println("        </ul>");
        writer.println("    </body>");
        writer.println("</html>");
    }
}
