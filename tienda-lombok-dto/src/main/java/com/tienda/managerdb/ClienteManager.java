package com.tienda.managerdb;

import com.tienda.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

public class ClienteManager {

    private EntityManager em;

    public ClienteManager(EntityManager em) {
        this.em = em;
    }

    // Listar todos los clientes
    public List<Cliente> totalClientes() {
        String jpql = "SELECT c FROM Cliente c";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

    // Obtener cliente por ID
    public Cliente itemId(Long id) {
        return em.find(Cliente.class, id);
    }

    // Crear cliente
    public Cliente crearCliente(Cliente cliente) {
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        return cliente;
    }

    // Actualizar cliente
    public Cliente actualizarCliente(Cliente cliente) {
        em.getTransaction().begin();
        Cliente actualizado = em.merge(cliente);
        em.getTransaction().commit();
        return actualizado;
    }

    // Eliminar cliente
    public int eliminarCliente(Long id) {
        em.getTransaction().begin();
        String jpql = "DELETE FROM Cliente c WHERE c.id = :id";
        Query query = em.createQuery(jpql);
        query.setParameter("id", id);
        int resultado = query.executeUpdate();
        em.getTransaction().commit();
        return resultado;
    }

}
