# adapterj-example-vertx-spring

点击[这里](https://github.com/york-deng/adapterj-example-vertx-spring/blob/master/README_CN.md)阅读[中文说明](https://github.com/york-deng/adapterj-example-vertx-spring/blob/master/README_CN.md)

An example based on Standard HTML Template (without any special syntax, tags, attributes), Vert.x, Spring Boot, Spring Data, and [AdapterJ](https://github.com/york-deng/adapterj). 

## Environment Requirements
* JDK 1.8+
* Maven 3.6+
* Vert.x 3.8+

## Guide 
1. Run these Command Lines as below   
2. Open the [URL](http://localhost:8080/) with a web browser   

## Command Lines
```
git clone https://github.com/york-deng/adapterj.git
cd adapterj
mvn clean deploy

cd ..

git clone https://github.com/york-deng/adapterj-example-vertx-spring.git
cd adapterj-example-vertx-spring
mvn clean package   
java -cp .:target/adapterj-example-vertx-spring-1.0.0-fat.jar com.adapterj.example.vertx.spring.Runner
```

## URL
http://localhost:8080/
