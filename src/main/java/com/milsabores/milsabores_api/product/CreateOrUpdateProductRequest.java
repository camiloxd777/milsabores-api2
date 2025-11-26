package com.milsabores.milsabores_api.product;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrUpdateProductRequest {
    private String nombre;
    private String descripcion;
    private Integer precio;
    private String categoria;
    @Builder.Default
    private Boolean activo = true;
}