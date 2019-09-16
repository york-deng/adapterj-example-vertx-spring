package com.adapterj.example.vertx.spring;

import io.vertx.core.Vertx;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.adapterj.example.vertx.spring.context.SpringConfiguration;
import com.adapterj.example.vertx.spring.verticle.ProductVerticle;
import com.adapterj.example.vertx.spring.verticle.SourceVerticle;
import com.adapterj.example.vertx.spring.verticle.ServerVerticle;

/**
 * Runner for the vertx-spring example
 *
 */
public class Runner {

  public static void main( String[] args ) {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    
    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new ProductVerticle(context));
    vertx.deployVerticle(new SourceVerticle(context));
    vertx.deployVerticle(new ServerVerticle());
    
    System.out.println("Deployment Done!");
  }
}
