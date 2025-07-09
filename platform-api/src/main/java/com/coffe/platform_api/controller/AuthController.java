package com.coffe.platform_api.controller;

import com.coffe.platform_api.dto.user.CreatedUserRequest;
import com.coffe.platform_api.dto.user.UserDTO;
import com.coffe.platform_api.dto.user.UserMapper;
import com.coffe.platform_api.entity.User;
import com.coffe.platform_api.repository.UserRepository;
import com.coffe.platform_api.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Registro de usuario
    @PostMapping("/register")
    public UserDTO register(@RequestBody CreatedUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado.");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
    throw new IllegalArgumentException("El nombre de usuario ya está registrado.");
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User(
                request.getUsername(),
                request.getEmail(),
                encodedPassword,
                request.getRole()
        );
        User saved = userRepository.save(user);
        return UserMapper.toDTO(saved);
    }

    // Login de usuario
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> loginRequest) {
        String password = loginRequest.get("password");
        String email = loginRequest.get("email");
        User user = userRepository.findByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Verifica si el correo electrónico y/o contraseña son correctos.");
        }
        Map<String, String> response = new HashMap<>();
        String token = jwtService.generateToken(user.getUsername());
        response.put("token", token);
        return response;
    }
}
