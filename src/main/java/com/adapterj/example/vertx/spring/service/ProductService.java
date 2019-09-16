package com.adapterj.example.vertx.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adapterj.example.vertx.spring.entity.Product;
import com.adapterj.example.vertx.spring.repository.ProductRepository;

/**
 * Simple Spring service bean to expose the results of a trivial database call
 */
@Service
public class ProductService {

  @Autowired
  private ProductRepository repo;

  public List<Product> getAllItems() {
    return repo.findAll();
  }
}
