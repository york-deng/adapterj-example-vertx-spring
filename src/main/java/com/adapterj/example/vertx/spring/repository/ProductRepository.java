package com.adapterj.example.vertx.spring.repository;

import com.adapterj.example.vertx.spring.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository to connect our service bean to data
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // do nothing 
    
}
