package com.adapterj.example.vertx.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.adapterj.example.vertx.spring.entity.Source;

/**
 * Spring Data JPA repository to connect our service bean to data
 */
public interface SourceRepository extends JpaRepository<Source, Long> {
    
  @Query(value = "SELECT * FROM t_source WHERE id = :id", nativeQuery = true)
  Source getItemById(@Param("id")Long id);

}
