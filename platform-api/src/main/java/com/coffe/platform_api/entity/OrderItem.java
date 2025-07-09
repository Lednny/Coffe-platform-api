package com.coffe.platform_api.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_item",schema = "coffee_schema")

public class OrderItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
}