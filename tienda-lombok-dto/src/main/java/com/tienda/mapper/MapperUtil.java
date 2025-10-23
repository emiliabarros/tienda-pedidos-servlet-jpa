package com.tienda.mapper;

import com.tienda.dto.PedidoDTO;
import com.tienda.dto.ProductoDTO;
import com.tienda.model.Pedido;
import com.tienda.model.Producto;
import com.tienda.util.FuncionApp;

import java.util.stream.Collectors;

public class MapperUtil {
    public static ProductoDTO toProductoDTO(Producto producto) {
        return ProductoDTO.builder()
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .build();
    }
    public static PedidoDTO toPedidoDTO(Pedido pedido) {
        return PedidoDTO.builder()
                .clienteNombre(pedido.getCliente().getNombre())
                .productos(pedido.getProductos().stream()
                        .map(MapperUtil::toProductoDTO)
                        .collect(Collectors.toList()))
                .total(pedido.getTotal())
                .fechaPedido(FuncionApp.getFechaString(pedido.getFecha()))
                .build();
    }
}
