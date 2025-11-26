package com.milsabores.milsabores_api.service;

import com.milsabores.milsabores_api.repository.UserRepository;
import com.milsabores.milsabores_api.request.LoginRequest;
import com.milsabores.milsabores_api.request.RegisterRequest;
import com.milsabores.milsabores_api.request.UpdateUserRequest;
import com.milsabores.milsabores_api.response.LoginResponse; // si ya no usas LoginResponse aquí puedes borrar este import
import com.milsabores.milsabores_api.user.RemoteUserDto;
import com.milsabores.milsabores_api.user.User;
import com.milsabores.milsabores_api.user.UserDto;
import com.milsabores.milsabores_api.user.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // REGISTRO
    public UserDto register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El correo ya está registrado");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        User user = User.builder()
                .email(request.getEmail())
                .nombre(request.getNombre())
                .username(request.getUsername())
                .fechaNacimiento(request.getFechaNacimiento())
                .password(passwordEncoder.encode(request.getPassword())) // 👈 encriptada
                .codigoPromo(request.getCodigoPromo())
                .rol("USER")
                .build();

        return UserMapper.toDto(userRepository.save(user));
    }

    public User loginUserEntity(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return user;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @Transactional
    public List<RemoteUserDto> getAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::toRemoteDto)
                .toList();
    }

    @Transactional
    public RemoteUserDto update(Long id, UpdateUserRequest req) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuario con id " + id + " no encontrado"));

        if (req.getNombre() != null) user.setNombre(req.getNombre());
        if (req.getUsername() != null) user.setUsername(req.getUsername());
        if (req.getEmail() != null) user.setEmail(req.getEmail());
        if (req.getPassword() != null) {
            // 👇 si cambian la password desde el panel, también la encriptamos
            user.setPassword(passwordEncoder.encode(req.getPassword()));
        }
        if (req.getRol() != null) user.setRol(req.getRol());

        return UserMapper.toRemoteDto(userRepository.save(user));
    }

    @Transactional
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario con id " + id + " no existe");
        }
        userRepository.deleteById(id);
    }
}