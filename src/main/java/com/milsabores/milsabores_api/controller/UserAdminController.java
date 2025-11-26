package com.milsabores.milsabores_api.controller;

import com.milsabores.milsabores_api.request.UpdateUserRequest;
import com.milsabores.milsabores_api.service.UserService;
import com.milsabores.milsabores_api.user.RemoteUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserAdminController {

    private final UserService userService;

    @GetMapping
    public List<RemoteUserDto> getALL() {
        return userService.getAll();
    }

    @PutMapping("/{id}")
    public RemoteUserDto update(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest req
    ) {
        return userService.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}