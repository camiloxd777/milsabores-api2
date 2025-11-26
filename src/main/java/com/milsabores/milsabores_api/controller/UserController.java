package com.milsabores.milsabores_api.controller;

import com.milsabores.milsabores_api.request.LoginRequest;
import com.milsabores.milsabores_api.response.LoginResponse;
import com.milsabores.milsabores_api.request.RegisterRequest;
import com.milsabores.milsabores_api.security.jwt.JwtService;
import com.milsabores.milsabores_api.user.User;
import com.milsabores.milsabores_api.user.UserDto;
import com.milsabores.milsabores_api.service.UserService;
import com.milsabores.milsabores_api.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "*") // permite llamadas desde Android
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;


    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            User user = userService.loginUserEntity(request);

            // usamos el email como "subject" del token
            String token = jwtService.generateToken(user.getEmail());

            LoginResponse response = new LoginResponse(
                    token,
                    UserMapper.toDto(user)
            );

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/users/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest request) {
        try {
            UserDto user = userService.register(request);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.getAllUsers();
    }
}