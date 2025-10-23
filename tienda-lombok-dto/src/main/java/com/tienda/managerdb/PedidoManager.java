package com.tienda.managerdb;

import com.tienda.model.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

public class PedidoManager {

    private EntityManager em;

    public PedidoManager(EntityManager em) {
        this.em = em;
    }

    // Listar todos los pedidos
    public List<Pedido> totalPedidos() {
        String jpql = "SELECT p FROM Pedido p";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

    // Obtener pedido por ID
    public Pedido itemId(Long id) {
        return em.find(Pedido.class, id);
    }

    // Crear pedido
    public Pedido crearPedido(Pedido pedido) {
        em.getTransaction().begin();
        em.persist(pedido);
        em.getTransaction().commit();
        return pedido;
    }

    // Actualizar pedido
    public Pedido actualizarPedido(Pedido pedido) {
        em.getTransaction().begin();
        Pedido actualizado = em.merge(pedido);
        em.getTransaction().commit();
        return actualizado;
    }

    // Eliminar pedido
    public int eliminarPedido(Long id) {
        em.getTransaction().begin();
        String jpql = "DELETE FROM Pedido p WHERE p.id = :id";
        Query query = em.createQuery(jpql);
        query.setParameter("id", id);
        int resultado = query.executeUpdate();
        em.getTransaction().commit();
        return resultado;
    }

    // Buscar pedidos por cliente
    public List<Pedido> pedidoPorCliente(Long clienteId) {
        String jpql = "SELECT p FROM Pedido p WHERE p.cliente.id = :clienteId";
        Query query = em.createQuery(jpql);
        query.setParameter("clienteId", clienteId);
        return query.getResultList();
    }

    // Obtener detalle de un pedido (incluyendo productos)
    public Pedido detallePedido(Long id) {
        String jpql = "SELECT p FROM Pedido p LEFT JOIN FETCH p.productos WHERE p.id = :id";
        Query query = em.createQuery(jpql);
        query.setParameter("id", id);
        return (Pedido) query.getSingleResult();
    }

}
