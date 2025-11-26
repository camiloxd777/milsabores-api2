package com.milsabores.milsabores_api.response;

import com.milsabores.milsabores_api.user.UserDto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private UserDto user;
}
