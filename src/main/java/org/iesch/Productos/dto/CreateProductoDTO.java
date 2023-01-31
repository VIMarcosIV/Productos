package org.iesch.Productos.dto;

import lombok.Data;

@Data
public class CreateProductoDTO {
    private String nombre;
    private float precio;
    private Long categoriaId;
}
