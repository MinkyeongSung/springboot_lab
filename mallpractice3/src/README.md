## gradle
```gradle
	implementation 'javax.servlet:jstl'
	implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
	
	// h2사용안하고 mysql 사용.
	//	runtimeOnly 'com.h2database:h2' 
	implementation 'com.mysql:mysql-connector-j:8.0.33'
```
## home jsp
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
<c:forEach var="p" items="${productList}"> <%--리퀘스트 객체에 접근 가능--%>
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
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
## write jsp
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
<%--    <form action="/action_page.php">--%>
        <div class="mb-3 mt-3">
            <input type="text" class="form-control" placeholder="Enter 상품명" value="바나나" name="name">
        </div>
        <div class="mb-3">
            <input type="text" class="form-control" placeholder="Enter 상품가격" value="1000" name="price">
        </div>
        <div class="mb-3">
            <input type="text" class="form-control" placeholder="Enter 상품재고" value="50" name="qty">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

</body>
</html>

```