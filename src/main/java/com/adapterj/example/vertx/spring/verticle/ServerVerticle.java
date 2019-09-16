package com.adapterj.example.vertx.spring.verticle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSON;  
import com.alibaba.fastjson.TypeReference;
    
import com.adapterj.algo.MD5;
import com.adapterj.annotation.POJO;
import com.adapterj.annotation.List;
import com.adapterj.registry.Registry;
import com.adapterj.registry.RegistryException;
import com.adapterj.registry.RegistryFactory;
import com.adapterj.registry.SimpleRegistry;
import com.adapterj.serverside.ServerSideContext;
import com.adapterj.serverside.ServerSideException;
import com.adapterj.widget.Anchor;
import com.adapterj.widget.AnchorGroup;
import com.adapterj.widget.SelectOptions;
import com.adapterj.widget.SimpleHTMLView;
import com.adapterj.widget.SimpleFormAdapter;
import com.adapterj.widget.SimpleListAdapter;
import com.adapterj.widget.SimpleSelectOptions;
import com.adapterj.widget.SimpleViewAdapter;
import com.adapterj.widget.View;
import com.adapterj.web.Htmlable;

import com.adapterj.ext.vertx.InitVerticle;
import com.adapterj.ext.vertx.SimpleHttpParametersResolver;

import com.adapterj.logging.Debugger;
import com.adapterj.logging.Log;

import com.adapterj.example.vertx.spring.entity.Product;
import com.adapterj.example.vertx.spring.entity.Source;
import com.adapterj.example.vertx.spring.web.ErrorPage;

/**
 * Simple web server verticle to expose the results of the Spring service bean call (routed via a verticle - see
 * ServerVerticle)
 */
public class ServerVerticle extends InitVerticle {

  private static final boolean DEBUG = Debugger.DEBUG;
  private static final String TAG = ServerVerticle.class.getName();

  private final String _propertiesFile = "/target/classes/adpj.properties";
  private Registry _registry = null;

  private final String _templateFile1 = "/target/classes/templates/index.html";
  private String _acceleratorClass1 = null;
  private Document _document1 = null;
  private String _md51 = null;

  private final String _templateFile2 = "/target/classes/templates/simplelist.html";
  private String _acceleratorClass2 = null;
  private Document _document2 = null;
  private String _md52 = null;

  private final String _templateFile3 = "/target/classes/templates/simpleform.html";
  private String _acceleratorClass3 = null;
  private Document _document3 = null;
  private String _md53 = null;

  public ServerVerticle() {
    try {
      final String dir = ServerVerticle.class.getClassLoader().getResource("").getPath();
      if (DEBUG) {
        StackTraceElement t = (new Throwable()).getStackTrace()[0];
        String format = "(%s:%d) %s: base dir is %s";
        Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), dir));
      }
      
      final File file = new File(dir + _propertiesFile);
      if (DEBUG) {
        StackTraceElement t = (new Throwable()).getStackTrace()[0];
        String format = "(%s:%d) %s: properties file is %s";
        Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), file.getAbsoluteFile()));
      }
      _registry = RegistryFactory.getRegistry(file); // _propertiesFile
      
      final File file1 = new File(dir + _templateFile1);
      _acceleratorClass1 = _registry.getAcceleratorClassName(_templateFile1);
      _document1 = Jsoup.parse(file1, "utf-8");
      _md51 = MD5.encode(_document1.html(), "utf-8");
      
      final File file2 = new File(dir + _templateFile2);
      _acceleratorClass2 = _registry.getAcceleratorClassName(_templateFile2);
      _document2 = Jsoup.parse(file2, "utf-8");
      _md52 = MD5.encode(_document2.html(), "utf-8");
      
      final File file3 = new File(dir + _templateFile3);
      _acceleratorClass3 = _registry.getAcceleratorClassName(_templateFile3);
      _document3 = Jsoup.parse(file3, "utf-8");
      _md53 = MD5.encode(_document3.html(), "utf-8");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void start() throws Exception {
    final Router router = Router.router(vertx);
    
    router.get("/").handler(this::indexHandler);
    router.get("/hello").handler(this::helloHandler);
    router.get("/products").handler(this::productsHandler);
    router.get("/sources").handler(this::sourcesHandler);
    router.get("/simple/list/html*").handler(this::listHandler);
    router.get("/simple/form/html*").handler(this::formHandler);
    
    router.get().failureHandler(rc -> {
      if (rc.statusCode() == 404) {
        rc.reroute("/notfound");
      } else {
        rc.next();
      }
    });
    
    vertx.createHttpServer().requestHandler(router).listen(8080);
  }
  
  private final void indexHandler(RoutingContext rc) {
      final String html = _document1.html();
      rc.response().putHeader("content-type", "text/html").end(html);
  }

  private final void productsHandler(RoutingContext rc) {
    vertx.eventBus().<String>send(ProductVerticle.ALL_PRODUCTS_ADDRESS, "", result -> {
      if (result.succeeded()) {
        rc.response().setStatusCode(200).end(result.result().body());
      } else {
        rc.response().setStatusCode(500).end(result.cause().toString());
      }
    });
  }
  
  private final void sourcesHandler(RoutingContext rc) {
    vertx.eventBus().<String>send(SourceVerticle.ALL_SOURCES_ADDRESS, "", result -> {
      if (result.succeeded()) {
        rc.response().setStatusCode(200).end(result.result().body());
      } else {
        rc.response().setStatusCode(500).end(result.cause().toString());
      }
    });
  }
  
  private final void listHandler(RoutingContext rc) {
    vertx.eventBus().<String>send(SourceVerticle.ALL_SOURCES_ADDRESS, "", result -> {
      if (result.succeeded()) {
        final Date begin = new Date(); // DEBUG
        
        final String text = result.result().body();
        final java.util.List<Source> list = (java.util.List<Source>) JSON.parseObject(text, new TypeReference<java.util.List<Source>>(){});
        
        /*
         * Build a list adapter 
         */
        final SimpleListAdapter<Source> adapter = new SimpleListAdapter<Source>();
        if (list != null && !list.isEmpty()) {
          adapter.addAllItems(list);
          
          final String baseURI = getBaseURI(rc.request());
          final StringBuffer s = new StringBuffer(baseURI);
          
          final Iterator<Source> iter = list.iterator();
          while(iter.hasNext()) {
            final Source source = iter.next();
            final String id = Long.toString(source.getId());
            
            final AnchorGroup anchors = new AnchorGroup(2);
            s.delete(baseURI.length(), s.length());
            anchors.anchor(0, new Anchor(s.append("simple/disable"  ).append('?').append("source.id").append('=').append(id).toString()));
            s.delete(baseURI.length(), s.length());
            anchors.anchor(1, new Anchor(s.append("simple/form/html").append('?').append("source.id").append('=').append(id).toString()));
            adapter.addAnchorGroup(anchors);
          }
        }
        
        /*
         * Put select-options into adapter 
         */
        final String selectId = ("type"); // See Source class getType method define for more information
        final SelectOptions options = adapter.getSelectOptions(selectId);
        if (options == null) {
          // Build type select-options and put it into the form adapter
          final SelectOptions options_ = new SimpleSelectOptions(selectId, Source.getTypes(), null);
          adapter.putSelectOptions(selectId, options_);
        }
        final String selectId1 = ("type1"); // See Source class getType1 method define for more information
        final SelectOptions options1 = adapter.getSelectOptions(selectId1);
        if (options1 == null) {
          // Build type1 select-options and put it into the form adapter
          final SelectOptions options1_ = new SimpleSelectOptions(selectId1, Source.getSubtypes(), null);
          adapter.putSelectOptions(selectId1, options1_);
          
          // Build type2 select-options and put it into the form adapter
          final String selectId2 = ("type2");
          final SelectOptions options2 = new SimpleSelectOptions(selectId2, Source.getSubtypes(), null);
          adapter.putSelectOptions(selectId2, options2);
          
          // Build type3 select-options and put it into the form adapter
          final String selectId3 = ("type3");
          final SelectOptions options3 = new SimpleSelectOptions(selectId3, Source.getSubtypes(), null);
          adapter.putSelectOptions(selectId3, options3);
          
          // Build type4 select-options and put it into the form adapter
          final String selectId4 = ("type4");
          final SelectOptions options4 = new SimpleSelectOptions(selectId4, Source.getSubtypes(), null);
          adapter.putSelectOptions(selectId4, options4);
          
          // Build type5 select-options and put it into the form adapter
          final String selectId5 = ("type5");
          final SelectOptions options5 = new SimpleSelectOptions(selectId5, Source.getSubtypes(), null);
          adapter.putSelectOptions(selectId5, options5);
          
          // Build type6 select-options and put it into the form adapter
          final String selectId6 = ("type6");
          final SelectOptions options6 = new SimpleSelectOptions(selectId6, Source.getSubtypes(), null);
          adapter.putSelectOptions(selectId6, options6);
          
          // Build type7 select-options and put it into the form adapter
          final String selectId7 = ("type7");
          final SelectOptions options7 = new SimpleSelectOptions(selectId7, Source.getSubtypes(), null);
          adapter.putSelectOptions(selectId7, options7);
          
          // Build type8 select-options and put it into the form adapter
          final String selectId8 = ("type8");
          final SelectOptions options8 = new SimpleSelectOptions(selectId8, Source.getSubtypes(), null);
          adapter.putSelectOptions(selectId8, options8);
          
          // Build type9 select-options and put it into the form adapter
          final String selectId9 = ("type9");
          final SelectOptions options9 = new SimpleSelectOptions(selectId9, Source.getSubtypes(), null);
          adapter.putSelectOptions(selectId9, options9);
        }
        
        /*
         * Put adapter into view
         */
        final List anList = adapter.getClass().getAnnotation(List.class);
        final String classId = anList.classId();
        final String entryId = anList.entryId();
        
        // Such as: "", "[0]", "[1]", ... for multiple lists 
        final String array = "";
        
        // Such as: _list.item, _list[0].item, _list[1].item, ...
        final String id = entryId != null && !entryId.isEmpty() ? new StringBuffer(classId).append(array != null & !array.isEmpty() ? array : "").append('.').append(entryId).toString() : classId; 
        
        String html = null;
        try {
          final View view = new SimpleHTMLView(false, _document2, _md52);
          view.putAdapter(id, adapter);
          view.bindAll(View.SERVER_SIDE_BINDING);
          
          html = view.toHTMLString();
        } catch (ServerSideException e) {
          html = render(e).toHTMLString();
        } catch (Throwable e) {
          html = render(e).toHTMLString();
        }
        
        rc.response().setStatusCode(200).putHeader("content-type", "text/html").end(html);
        
        final Date end = new Date(); // DEBUG
        final long cost = end.getTime() - begin.getTime(); // DEBUG
        if (DEBUG) { // DEBUG
          StackTraceElement t = (new Throwable()).getStackTrace()[0];
          String format = "(%s:%d) %s: listHandler: cost is %d";
          Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), cost));
        } // DEBUG
      } else {
        rc.response().setStatusCode(500).end(result.cause().toString());
      }
    });
  }
  
  private final void formHandler(RoutingContext rc) {
    /*
     * Resolve HTTP request parameters
     */
    final SimpleHttpParametersResolver<Source> resolver = new SimpleHttpParametersResolver<Source>(_registry);
    final Source parameter = resolver.getParameter(rc.request());
    if (DEBUG) {
      StackTraceElement t = (new Throwable()).getStackTrace()[0];
      String format = "(%s:%d) %s: parameter is %s";
      Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), parameter));
    }
    
    final Long id = parameter.getId();
    
    vertx.eventBus().<String>send(SourceVerticle.FIRST_SOURCE_ADDRESS, id, result -> {
      if (result.succeeded()) {
        final Date begin = new Date();
        
        final String text = result.result().body();
        //Log.i(TAG, "--> text is: " + text);
        final Source pojo = (Source) JSON.parseObject(text, new TypeReference<Source>(){});

        /*
         * Define selected variables for select-options 
         */
        Integer type  = null;
        Integer type1 = null, type2 = null, type3 = null, type4 = null, type5 = null;
        Integer type6 = null, type7 = null, type8 = null, type9 = null;
    
        /*
         * Create adapter
         */
        final SimpleFormAdapter<Source> adapter = new SimpleFormAdapter<Source>();
        adapter.setFormAction("../save");
        
        if (parameter != null) {
          //final Long id = parameter.getId();
          final String domain = parameter.getDomain();
          final String www = parameter.getWWW();
          
          if ((id != null && id > 0) || 
            (domain != null && !domain.isEmpty()) || 
            (www != null && !www.isEmpty())) {
            
            // Gets data by parameter
            final Source source = pojo; // list.get(0); // SourceQuery.getInstance().findByMatching(parameter);
            
            if (source != null) {
              // Gets selected value for select-options
              type = source.getType();
              type1 = source.getType1();
              type2 = source.getType2();
              type3 = source.getType3();
              type4 = source.getType4();
              type5 = source.getType5();
              type6 = source.getType6();
              type7 = source.getType7();
              type8 = source.getType8();
              type9 = source.getType9();
              
              // Put data into adapter
              adapter.setData(source);
            }
          }
        }
        
        /*
         * Put select-options into adapter 
         */
        final String selectId = ("type"); // See Source class getType method define for more information
        final SelectOptions options = adapter.getSelectOptions(selectId);
        if (options == null) {
          // Build type select-options and put it into the form adapter
          final SelectOptions options_ = new SimpleSelectOptions(selectId, Source.getTypes(), type);
          adapter.putSelectOptions(selectId, options_);
        }
        final String selectId1 = ("type1"); // See Source class getType1 method define for more information
        final SelectOptions options1 = adapter.getSelectOptions(selectId1);
        if (options1 == null) {
          // Build type1 select-options and put it into the form adapter
          final SelectOptions options1_ = new SimpleSelectOptions(selectId1, Source.getSubtypes(), type1);
          adapter.putSelectOptions(selectId1, options1_);
          
          // Build type2 select-options and put it into the form adapter
          final String selectId2 = ("type2");
          final SelectOptions options2 = new SimpleSelectOptions(selectId2, Source.getSubtypes(), type2);
          adapter.putSelectOptions(selectId2, options2);
          
          // Build type3 select-options and put it into the form adapter
          final String selectId3 = ("type3");
          final SelectOptions options3 = new SimpleSelectOptions(selectId3, Source.getSubtypes(), type3);
          adapter.putSelectOptions(selectId3, options3);
          
          // Build type4 select-options and put it into the form adapter
          final String selectId4 = ("type4");
          final SelectOptions options4 = new SimpleSelectOptions(selectId4, Source.getSubtypes(), type4);
          adapter.putSelectOptions(selectId4, options4);
          
          // Build type5 select-options and put it into the form adapter
          final String selectId5 = ("type5");
          final SelectOptions options5 = new SimpleSelectOptions(selectId5, Source.getSubtypes(), type5);
          adapter.putSelectOptions(selectId5, options5);
          
          // Build type6 select-options and put it into the form adapter
          final String selectId6 = ("type6");
          final SelectOptions options6 = new SimpleSelectOptions(selectId6, Source.getSubtypes(), type6);
          adapter.putSelectOptions(selectId6, options6);
          
          // Build type7 select-options and put it into the form adapter
          final String selectId7 = ("type7");
          final SelectOptions options7 = new SimpleSelectOptions(selectId7, Source.getSubtypes(), type7);
          adapter.putSelectOptions(selectId7, options7);
          
          // Build type8 select-options and put it into the form adapter
          final String selectId8 = ("type8");
          final SelectOptions options8 = new SimpleSelectOptions(selectId8, Source.getSubtypes(), type8);
          adapter.putSelectOptions(selectId8, options8);
          
          // Build type9 select-options and put it into the form adapter
          final String selectId9 = ("type9");
          final SelectOptions options9 = new SimpleSelectOptions(selectId9, Source.getSubtypes(), type9);
          adapter.putSelectOptions(selectId9, options9);
        }
        
        String html = null;
        try {
          /*
           * Put adapter into view
           */
          final POJO anPOJO = adapter.getClass().getAnnotation(POJO.class);
          final String element = anPOJO.classId();
          
          final View view = new SimpleHTMLView(false, _document3, _md53);
          view.putAdapter(element, adapter);
          view.bindAll(View.SERVER_SIDE_BINDING);
       
          html = view.toHTMLString();
        } catch (Throwable e) {
          html = render(e).toHTMLString();
        }
        
        rc.response().putHeader("content-type", "text/html").end(html);
        
        final Date end = new Date();
        final long cost = end.getTime() - begin.getTime();
        if (DEBUG) {
          StackTraceElement t = (new Throwable()).getStackTrace()[0];
          String format = "(%s:%d) %s: formHandler: end is %s, cost is %d";
          Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), end.toString(), cost));
        }
      } else {
        rc.response().setStatusCode(500).end(result.cause().toString());
      }
    });
  }
  
  private final void helloHandler(RoutingContext rc) {
    rc.response().putHeader("content-type", "text/html").end("Hello from Vert.x + Spring Boot");
  }
  
  private final Htmlable render(final Throwable thrown) {
    final ErrorPage doc = new ErrorPage("ERROR");
    doc.writeln("<div class=\"page-header\"><h1>Error: </h1></div>");
    doc.writeln("<div class=\"content\">");
    doc.write(Log.getStackTraceHTMLString(thrown));
    doc.writeln("</div>");
    return (doc);
  }
}
