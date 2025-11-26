package com.milsabores.milsabores_api.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String fechaNacimiento;

    @Column(nullable = false)
    private String password;

    private String codigoPromo;

    @Column(nullable = false)
    private String rol = "ADMIN"; // o "USER"
}
