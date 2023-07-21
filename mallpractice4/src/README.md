## gradle
```gradle
plugins {
    id 'java'
    id 'org.springframework.boot' version '2.7.13'
    id 'io.spring.dependency-management' version '1.0.15.RELEASE'
}

group = 'shop.mtcoding'
version = '0.0.1-SNAPSHOT'

java {
    sourceCompatibility = '11'
}

configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
}

repositories {
    mavenCentral()
}

dependencies {
    runtimeOnly 'com.h2database:h2'
    // https://mvnrepository.com/artifact/ch.simas.qlrm/qlrm
    implementation group: 'ch.simas.qlrm', name: 'qlrm', version: '1.7.1'

    implementation 'javax.servlet:jstl'
    implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    // https://mvnrepository.com/artifact/com.mysql/mysql-connector-j
    implementation group: 'com.mysql', name: 'mysql-connector-j', version: '8.0.33'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
    useJUnitPlatform()
}
```
## yml
```yml
server:
  port: 8080

spring:
  mvc:
    view:
      prefix: /WEB-INF/views/
      suffix: .jsp
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/metadb
    username: root
    password: 1234
  jpa:
    hibernate:
      ddl-auto: create
    show-sql: true
```
## 기능
```
## 컨트롤러
-product
## 모델
-Product
 -상품목록
 -디테일
 -상품등록
 -수정,삭제
-Seller
 -등록
 -
## DB
-상품테이플
상품번호, 상품이름, 가격, 갯수,
-판매자테이블
이름,이메일,
jsp
-home
-write
-detail
```
## DB
```sql
drop table product_tb;
drop table seller_tb;

select * from product_tb;
select * from seller_tb;

create table product_tb(
id integer primary key auto_increment,
name varchar(250) not null,
price integer not null,
qty integer not null
) default character set utf8mb4;

insert into seller_tb(name, email) values('홍길동', 'ssar@nate.com');
insert into product_tb(name, price, qty, seller_id) values('바나나', 1000, 50, 1);

select *
from product_tb pt inner join seller_tb st
on pt.seller_id = st.id
where pt.id = 1;

select id, name, price, qty, 'good' as des from product_tb where id = 3;
```
## jsp (home, write, detail)
```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Mall</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">쇼핑몰</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/">상품목록</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/write">상품등록</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-3">
    <table class="table">
        <thead>
            <tr>
                <th>상품번호</th>
                <th>상품명</th>
                <th>상품가격</th>
                <th>상품재고</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${productList}">
                <tr>
                    <td>${p.id}</td>
                    <td><a href="/product/${p.id}">${p.name}</a></td>
                    <td>${p.price}원</td>
                    <td>${p.qty}개</td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
</div>

</body>
</html>
```
```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Mall</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">쇼핑몰</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/">상품목록</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/write">상품등록</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-3">
    <form action="/product" method="post" enctype="application/x-www-form-urlencoded">
        <div class="mb-3 mt-3">
            <input type="text" class="form-control" placeholder="Enter 상품명" value="바나나" name="name">
        </div>
        <div class="mb-3">
            <input type="text" class="form-control" placeholder="Enter 상품가격" value="1000" name="price">
        </div>
        <div class="mb-3">
            <input type="text" class="form-control" placeholder="Enter 상품재고" value="50" name="qty">
        </div>
        <button type="submit" class="btn btn-primary">상품등록</button>
    </form>
</div>

</body>
</html>
```
```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Mall</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">쇼핑몰</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/">상품목록</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/write">상품등록</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-3">
    <form action="/product/update" method="post" enctype="application/x-www-form-urlencoded">
        <div class="mb-3 mt-3">
            <input type="text" class="form-control" value="${p.id}" name="id">
        </div>
        <div class="mb-3 mt-3">
            <input type="text" class="form-control" value="${p.name}" name="name">
        </div>
        <div class="mb-3">
            <input type="text" class="form-control" value="${p.price}" name="price">
        </div>
        <div class="mb-3">
            <input type="text" class="form-control" value="${p.qty}" name="qty">
        </div>
        <button type="submit" class="btn btn-primary">상품수정</button>
    </form>
    <form action="/product/delete" method="post">
        <input type="hidden" class="form-control" value="${p.id}" name="id">
        <button type="submit" class="btn btn-danger">상품삭제</button>
    </form>
</div>

</body>
</html>
```
