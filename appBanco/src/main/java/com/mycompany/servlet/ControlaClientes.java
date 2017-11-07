package com.mycompany.servlet;

import com.mycompany.classes.Cliente;
import com.mycompany.classes.ClienteJDBC;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.Consumer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControlaClientes", urlPatterns = {"/clientes"})
public class ControlaClientes extends HttpServlet {

    private final ClienteJDBC clientes = new ClienteJDBC();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControlaClientes</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Lista de Clientes</h1>");
            imprimeTodosOsClientes(out);
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    private void imprimeTodosOsClientes(final PrintWriter out) {
        clientes.todosOsClientes().forEachRemaining(
                new Consumer<Cliente>() {
            @Override
            public void accept(Cliente c) {
                out.println(c.getNome());
            }
        }
        );
    }

}
