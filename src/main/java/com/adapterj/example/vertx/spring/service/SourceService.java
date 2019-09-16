package com.adapterj.example.vertx.spring.service;

import java.lang.Long;
import java.lang.Iterable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adapterj.example.vertx.spring.entity.Source;
import com.adapterj.example.vertx.spring.repository.SourceRepository;

/**
 * Simple Spring service bean to expose the results of a trivial database call
 */
@Service
public class SourceService {

  @Autowired
  private SourceRepository repo;
  
  public List<Source> getAllItems() {
    return repo.findAll();
  }
  
  public List<Source> getItemsById(List<Long> idList) {
    return repo.findAllById(idList);
  }
  
  public Source getItemById(Long id) {
    return repo.getItemById(id);
  }
}
