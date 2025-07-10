package com.coffe.platform_api.controller;

import com.coffe.platform_api.dto.user.*;
import com.coffe.platform_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Ver perfil propio
    @GetMapping("/profile")
    public UserDTO getProfile() {
        return userService.getCurrentUser();
    }

    // Editar perfil propio
    @PutMapping("/profile")
    public UserDTO updateProfile(@RequestBody UpdateUserRequest request) {
        return userService.updateCurrentUser(request);
    }
}