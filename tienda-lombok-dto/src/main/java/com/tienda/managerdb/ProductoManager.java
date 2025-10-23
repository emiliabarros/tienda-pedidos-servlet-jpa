package com.tienda.managerdb;

import com.tienda.model.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

public class ProductoManager {

    private EntityManager em;

    public ProductoManager(EntityManager em) {
        this.em = em;
    }

    // Listar todos los productos
    public List<Producto> totalProductos() {
        String jpql = "SELECT p FROM Producto p";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

    // Obtener producto por ID
    public Producto itemId(Long id) {
        return em.find(Producto.class, id);
    }

    // Crear producto
    public Producto crearProducto(Producto producto) {
        em.getTransaction().begin();
        em.persist(producto);
        em.getTransaction().commit();
        return producto;
    }

    // Actualizar producto
    public Producto actualizarProducto(Producto producto) {
        em.getTransaction().begin();
        Producto actualizado = em.merge(producto);
        em.getTransaction().commit();
        return actualizado;
    }

    // Eliminar producto
    public int eliminarProducto(Long id) {
        em.getTransaction().begin();
        String jpql = "DELETE FROM Producto p WHERE p.id = :id";
        Query query = em.createQuery(jpql);
        query.setParameter("id", id);
        int resultado = query.executeUpdate();
        em.getTransaction().commit();
        return resultado;
    }

}

