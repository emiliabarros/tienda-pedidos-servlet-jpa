package com.tienda.dto;

import com.tienda.model.Pedido;
import com.tienda.util.FuncionApp;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PedidoDTO {
    private Long id;
    private String clienteNombre;
    private List<ProductoDTO> productos;
    private String fechaPedido;
    private Double total;

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.fechaPedido = FuncionApp.getFechaString(pedido.getFecha());
        this.clienteNombre = pedido.getCliente().getNombre();

        // Mapear productos a ProductoDTO si existen
        if (pedido.getProductos() != null) {
            this.productos = pedido.getProductos().stream()
                    .map(ProductoDTO::new) // se asume que ProductoDTO tiene un constructor similar
                    .toList();
        }

        // Calcular total
        if (pedido.getProductos() != null) {
            this.total = pedido.getProductos().stream()
                    .mapToDouble(p -> p.getPrecio()) // ajustar si hay cantidad
                    .sum();
        } else {
            this.total = 0.0;
        }
    }
}
