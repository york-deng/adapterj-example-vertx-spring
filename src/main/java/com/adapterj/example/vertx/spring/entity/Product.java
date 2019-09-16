package com.adapterj.example.vertx.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_product")
public class Product {

  @Id
  @Column(name = "id")
  private Long productId;

  @Column
  private String description;

  public Long getProductId() {
    return productId;
  }

  public String getDescription() {
    return description;
  }
}
