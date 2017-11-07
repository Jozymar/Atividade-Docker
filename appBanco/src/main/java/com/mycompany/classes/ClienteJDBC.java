package com.mycompany.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteJDBC {

    private Connection connection;

    public Iterator<Cliente> todosOsClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            iniciaConexao();
            try (Statement createStatement = this.connection.createStatement()) {
                ResultSet executeQuery = createStatement.executeQuery("Select * from cliente;");
                clientes.addAll(listaClientesDoResultSet(executeQuery));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            finalizaConexao();
        }
        return clientes.iterator();
    }

    private List<Cliente> listaClientesDoResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            String cpf = resultSet.getString("cpf");
            clientes.add(Cliente.of(id, cpf, nome));
        }
        return clientes;
    }

    private void iniciaConexao() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://host-banco:5432/dac-cliente",
                    "postgres", "12345");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void finalizaConexao() {
        if (this.connection == null) {
            return;
        }
        try {
            this.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
