package com.milsabores.milsabores_api.product;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer precio;
    private String categoria;
    private Boolean activo;
}