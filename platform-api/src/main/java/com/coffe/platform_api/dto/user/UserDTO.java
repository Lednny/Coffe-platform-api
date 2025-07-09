package com.coffe.platform_api.dto.user;
import java.util.UUID;

// Para exponer datos de usuario en la API, puedes crear un DTO (Data Transfer Object).
public class UserDTO {
    private UUID id;
    private String username;
    private String email;
    private String role;

    public UserDTO() {}

    public UserDTO(UUID id, String username, String email, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
