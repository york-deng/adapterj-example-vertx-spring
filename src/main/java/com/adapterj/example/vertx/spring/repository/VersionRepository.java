package com.adapterj.example.vertx.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adapterj.example.vertx.spring.entity.Version;

/**
 * Spring Data JPA repository to connect our service bean to data
 */
public interface VersionRepository extends JpaRepository<Version, Long> {
    
    // do nothing 
    
}
