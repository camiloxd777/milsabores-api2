package com.milsabores.milsabores_api.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String email;
    private String nombre;
    private String username;
    private String fechaNacimiento;
    private String password;
    private String codigoPromo;
}