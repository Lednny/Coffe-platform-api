package com.coffe.platform_api.entity;


import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "category", schema = "coffee_schema")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    // Constructor vacío requerido por JPA
    public Category() {}

    // Relación uno a muchos con Product
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    // Getters y setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }
}