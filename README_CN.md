# adapterj-example-vertx-spring

一个基于，标准HTML模板(没有任何特殊的语法、标签、属性)、Vert.x，Spring Boot，Spring Data，及[AdapterJ](https://github.com/york-deng/adapterj)的范例. 

## 运行环境
* JDK 1.8+
* Maven 3.6+
* Vert.x 3.8+

## 使用方法 
1. 下载 adapterj-example-vertx-spring   
2. 解压 adapterj-example-vertx-spring-master.zip   
3. 运行以下命令行   
4. 在浏览器中打开[演示地址](http://localhost:8080/)   

## 命令行
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

## 演示地址
http://localhost:8080/
