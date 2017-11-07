package com.mycompany.classes;

import java.util.ArrayList;
import java.util.Iterator;

public class ClienteEmMemoria {

    public Iterator<Cliente> todosOsClientes() {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(Cliente.of(1, "444.444.444-04", "Marcos"));
        clientes.add(Cliente.of(2, "555.555.555-05", "Fátima"));
        return clientes.iterator();
    }
}
