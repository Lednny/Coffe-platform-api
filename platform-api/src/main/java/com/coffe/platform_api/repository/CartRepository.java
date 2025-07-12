package com.coffe.platform_api.repository;

import com.coffe.platform_api.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

// La clase CartRepository es un repositorio de JPA para la entidad Cart
// que permite realizar operaciones de acceso a datos relacionadas con los carritos de compra.
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(java.util.UUID userId);
}
