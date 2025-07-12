package com.coffe.platform_api.repository;

import com.coffe.platform_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository; 

// Esta clase define un repositorio de JPA para la entidad Product
// que permite realizar operaciones de acceso a datos relacionadas con los productos.

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Aquí puedes definir métodos personalizados si es necesario
    // Por ejemplo, buscar productos por nombre, categoría, etc.
    Product findByName(String name);
    boolean existsByName(String name);
}
