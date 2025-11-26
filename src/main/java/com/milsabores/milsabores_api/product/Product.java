package com.milsabores.milsabores_api.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(nullable = false)
    private Integer precio; // en pesos

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private Boolean activo = true; // para ocultar/mostrar
}
