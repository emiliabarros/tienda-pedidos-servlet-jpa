package com.tienda.service;

import com.tienda.managerdb.ProductoManager;
import com.tienda.model.Producto;

import java.util.List;

public class ProductoService {

    private ProductoManager manager;

    public ProductoService(ProductoManager manager) {
        this.manager = manager;
    }

    public List<Producto> obtenerTodosProductos() {
        try {
            return manager.totalProductos();
        } catch (Exception e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
            return null;
        }
    }

    public Producto obtenerProductoPorId(Long id) {
        try {
            return manager.itemId(id);
        } catch (Exception e) {
            System.err.println("Error al obtener producto por ID: " + e.getMessage());
            return null;
        }
    }

    public Producto crearProducto(Producto producto) {
        try {
            return manager.crearProducto(producto);
        } catch (Exception e) {
            System.err.println("Error al crear producto: " + e.getMessage());
            return null;
        }
    }

    public Producto actualizarProducto(Producto producto) {
        try {
            return manager.actualizarProducto(producto);
        } catch (Exception e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
            return null;
        }
    }

    public boolean eliminarProducto(Long id) {
        try {
            return manager.eliminarProducto(id) > 0;
        } catch (Exception e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }

}
