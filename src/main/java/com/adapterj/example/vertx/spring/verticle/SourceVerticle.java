package com.adapterj.example.vertx.spring.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;

import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.adapterj.logging.Debugger;
import com.adapterj.logging.Log;

import com.adapterj.example.vertx.spring.entity.Source;
import com.adapterj.example.vertx.spring.service.SourceService;

/**
 * Simple verticle to wrap a Spring service bean - note we wrap the service call
 * in executeBlocking, because we know it's going to be a JDBC call which blocks.
 * As a general principle with Spring beans, the default assumption should be that it will block unless you
 * know for sure otherwise (in other words use executeBlocking unless you know for sure your service call will be
 * extremely quick to respond)
 */
public class SourceVerticle extends AbstractVerticle {

  private static final boolean DEBUG = Debugger.DEBUG;
  private static final String TAG = SourceVerticle.class.getName();

  public static final String ALL_SOURCES_ADDRESS = "example.all.sources";
  public static final String FIRST_SOURCE_ADDRESS = "example.first.source";
  
  // Reuse the Vert.x Mapper, alternatively you can use your own.
  private final ObjectMapper mapper = Json.mapper;

  private final SourceService service;

  public SourceVerticle(final ApplicationContext context) {
    service = (SourceService) context.getBean("sourceService");
  }

  private Handler<Message<String>> allItemsHandler(SourceService service) {
    // It is important to use an executeBlocking construct here
    // as the service calls are blocking (dealing with a database)
    return msg -> vertx.<String>executeBlocking(
        future -> {
          try {
            future.complete(mapper.writeValueAsString(service.getAllItems()));
          } catch (JsonProcessingException e) {
            System.out.println("Failed to serialize result");
            future.fail(e);
          }
        },
        result -> {
          if (result.succeeded()) {
            msg.reply(result.result());
          } else {
            msg.reply(result.cause().toString());
          }
        });
  }

  private Handler<Message<Long>> firstItemHandler(SourceService service) {
    // It is important to use an executeBlocking construct here
    // as the service calls are blocking (dealing with a database)
    return msg -> vertx.<String>executeBlocking(
        future -> {
          try {
            final Long id = msg.body();
            Log.i(TAG, "id is: " + id);
            
            final Source item = service.getItemById(id);
            Log.i(TAG, "item is: " + item.toJSONString());
            
            future.complete(mapper.writeValueAsString(item));
          } catch (JsonProcessingException e) {
            System.out.println("Failed to serialize result");
            //Log.e(TAG, Log.getStackTraceString(e));
            future.fail(e);
          }
        },
        result -> {
          if (result.succeeded()) {
            msg.reply(result.result());
          } else {
            msg.reply(result.cause().toString());
          }
        });
  }

  @Override
  public void start() throws Exception {
    super.start();
    vertx.eventBus().<String>consumer(ALL_SOURCES_ADDRESS).handler(allItemsHandler(service));
    vertx.eventBus().<Long>consumer(FIRST_SOURCE_ADDRESS).handler(firstItemHandler(service));
  }
}
