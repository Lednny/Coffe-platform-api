package com.coffe.platform_api.repository;

import com.coffe.platform_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    // Buscar usuario por username
User findByUsername(String username);

// Buscar usuario por email
User findByEmail(String email);

// Verificar si existe un usuario por email
boolean existsByEmail(String email);

// Verificar si existe un usuario por username
boolean existsByUsername(String username);


}
