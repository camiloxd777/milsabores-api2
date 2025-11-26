package com.milsabores.milsabores_api.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private String nombre;
    private String username;
    private String email;
    private String password;
    private String rol;
}
