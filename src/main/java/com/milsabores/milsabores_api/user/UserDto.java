package com.milsabores.milsabores_api.user;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String nombre;
    private String username;
    private String fechaNacimiento;
    private String codigoPromo;
    private String rol;
}
