package com.tienda.dto;

import com.tienda.model.Producto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductoDTO {

    private String nombre;
    private Double precio;

    public ProductoDTO(Producto producto) {

        this.nombre = producto.getNombre();

        this.precio = producto.getPrecio();
    }
}
