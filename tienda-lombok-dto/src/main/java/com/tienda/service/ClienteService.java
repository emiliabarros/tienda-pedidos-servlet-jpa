package com.tienda.service;

import com.tienda.managerdb.ClienteManager;
import com.tienda.model.Cliente;

import java.util.List;

public class ClienteService {

    private ClienteManager manager;

    public ClienteService(ClienteManager manager) {
        this.manager = manager;
    }

    public List<Cliente> obtenerTodosClientes() {
        try {
            return manager.totalClientes();
        } catch (Exception e) {
            System.err.println("Error al obtener clientes: " + e.getMessage());
            return null;
        }
    }

    public Cliente obtenerClientePorId(Long id) {
        try {
            return manager.itemId(id);
        } catch (Exception e) {
            System.err.println("Error al obtener cliente por ID: " + e.getMessage());
            return null;
        }
    }

    public Cliente crearCliente(Cliente cliente) {
        try {
            return manager.crearCliente(cliente);
        } catch (Exception e) {
            System.err.println("Error al crear cliente: " + e.getMessage());
            return null;
        }
    }

    public Cliente actualizarCliente(Cliente cliente) {
        try {
            return manager.actualizarCliente(cliente);
        } catch (Exception e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return null;
        }
    }

    public boolean eliminarCliente(Long id) {
        try {
            return manager.eliminarCliente(id) > 0;
        } catch (Exception e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }

}
