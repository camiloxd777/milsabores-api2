package com.milsabores.milsabores_api.user;

public final class UserMapper {

    private UserMapper() {}

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nombre(user.getNombre())
                .username(user.getUsername())
                .fechaNacimiento(user.getFechaNacimiento())
                .codigoPromo(user.getCodigoPromo())
                .rol(user.getRol())
                .build();
    }

    public static RemoteUserDto toRemoteDto(User user) {
        return RemoteUserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nombre(user.getNombre())
                .username(user.getUsername())
                .fechaNacimiento(user.getFechaNacimiento())
                .rol(user.getRol())
                .build();
    }
}
