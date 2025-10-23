package com.tienda.service;

import com.google.gson.Gson;
import com.tienda.managerdb.PedidoManager;
import com.tienda.model.Pedido;
import com.tienda.dto.PedidoDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoService {

    private PedidoManager manager;
    private Gson gson = new Gson();

    public PedidoService(PedidoManager manager) {
        this.manager = manager;
    }

    public List<Pedido> obtenerTodosPedidos() {
        try {
            return manager.totalPedidos();
        } catch (Exception e) {
            System.err.println("Error al obtener pedidos: " + e.getMessage());
            return null;
        }
    }

    public Pedido obtenerPedidoPorId(Long id) {
        try {
            return manager.itemId(id);
        } catch (Exception e) {
            System.err.println("Error al obtener pedido por ID: " + e.getMessage());
            return null;
        }
    }

    public Pedido crearPedido(Pedido pedido) {
        try {
            return manager.crearPedido(pedido);
        } catch (Exception e) {
            System.err.println("Error al crear pedido: " + e.getMessage());
            return null;
        }
    }

    public Pedido actualizarPedido(Pedido pedido) {
        try {
            return manager.actualizarPedido(pedido);
        } catch (Exception e) {
            System.err.println("Error al actualizar pedido: " + e.getMessage());
            return null;
        }
    }

    public boolean eliminarPedido(Long id) {
        try {
            return manager.eliminarPedido(id) > 0;
        } catch (Exception e) {
            System.err.println("Error al eliminar pedido: " + e.getMessage());
            return false;
        }
    }

    public List<Pedido> obtenerPedidosPorCliente(Long clienteId) {
        try {
            return manager.pedidoPorCliente(clienteId);
        } catch (Exception e) {
            System.err.println("Error al obtener pedidos por cliente: " + e.getMessage());
            return null;
        }
    }

    public Pedido obtenerDetallePedido(Long id) {
        try {
            return manager.detallePedido(id);
        } catch (Exception e) {
            System.err.println("Error al obtener detalle del pedido: " + e.getMessage());
            return null;
        }
    }


    public String obtenerPedidosComoJson() {
        try {
            List<Pedido> pedidos = obtenerTodosPedidos();
            if (pedidos == null) {
                return "{\"error\":\"Error al obtener pedidos\"}";
            }
            List<PedidoDTO> pedidosDTO = pedidos.stream()
                    .map(PedidoDTO::new)
                    .collect(Collectors.toList());
            return gson.toJson(pedidosDTO);
        } catch (Exception e) {
            return "{\"error\":\"Error inesperado al obtener pedidos\"}";
        }
    }

    public String obtenerPedidoPorIdComoJson(Long id) {
        try {
            Pedido pedido = obtenerDetallePedido(id);
            if (pedido == null) {
                return "{\"error\":\"Pedido no encontrado\"}";
            }
            PedidoDTO dto = new PedidoDTO(pedido);
            return gson.toJson(dto);
        } catch (NumberFormatException e) {
            return "{\"error\":\"ID inválido\"}";
        } catch (Exception e) {
            return "{\"error\":\"Error inesperado al obtener el pedido\"}";
        }
    }
}
