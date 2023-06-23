<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 01/06/2023
  Time: 10:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trang chủ</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light ">
            <div class="container px-4 px-lg-5">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                        aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">

                        <li class="nav-item"><a class="nav-link fs-4" href="/home">Trang chủ</a></li>
                        <li class="nav-item"><a class="nav-link fs-5 mt-1" href="/san-pham">Sản phẩm</a></li>
                        <li class="nav-item"><a class="nav-link fs-5 mt-1" >Tin tức</a></li>
                        <li class="nav-item"><a class="nav-link fs-5 mt-1" >Sale</a></li>
                        <c:if test="${sessionScope.user.admin==0}">
                            <li class="nav-item dropdown ">
                                <a class="nav-link dropdown-toggle fs-5 mt-1" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">Quản trị</a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="/admin/hoa-don">Quản lý hóa đơn</a></li>
                                    <li><a class="dropdown-item" href="/admin/tai-khoan">Quản lý tài khoản</a></li>
                                    <li><a class="dropdown-item" href="/admin/san-pham">Quản lý sản phẩm</a></li>
                                    <li><a class="dropdown-item" href="/admin/dong-san-pham">Quản lý dòng sản phẩm</a></li>
                                    <li><a class="dropdown-item" href="/admin/hoa-don-chi-tiet">Quản lý hóa đơn chi tiết</a></li>
                                </ul>
                            </li>
                        </c:if>
                    </ul>
                    <c:if test="${empty sessionScope.user}">
                        <a href="/dang-ky" class="btn btn-outline-success me-2">Đăng ký</a>
                        <a href="/login" class="btn btn-outline-success">Đăng nhập</a>
                    </c:if>
                    <c:if test="${not empty sessionScope.user}">
                        <a class="btn btn-outline-dark me-2" href="/gio-hang"><span class="badge bg-dark text-white ms-1 rounded-pill">${sessionScope.slGioHang}</span>
                            Cart
                            <i class="bi-cart-fill me-1"></i></a>
                        <div class="dropdown me-5">
                            <button class="btn  btn-outline-dark rounded-circle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <img src="${sessionScope.user.anh}" style="height: 30px; width: 20px;">
                            </button>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="/hoa-don/da-thanh-toan">Lịch sử mua hàng</a></li>
                                <li><a class="dropdown-item" href="/doi-mat-khau">Đổi mật khẩu</a></li>
                                <li><a class="dropdown-item" href="/login">Đăng xuất</a></li>
                            </ul>
                        </div>
                    </c:if>
                </div>
            </div>
        </nav>
    </div>
    <div class="row">
        <div class="col-2"></div>
        <div class="col-8 p-5">
            <div class="card mb-3" >
                <div class="row g-0">
                    <div class="col-md-6 ">
                        <img src="/${sp.anh}" class="img-fluid rounded-start" alt="...">
                    </div>
                    <div class="col-md-6">
                        <div class="card-body">
                            <h4 class="card-title">${sp.ten}</h4>
                            <p class="card-text">${sp.gia}VNĐ</p>
                            <h5 class="card-text">Size</h5>
                            <button type="button" class="btn btn-outline-secondary">36</button>
                            <button type="button" class="btn btn-outline-secondary">37</button>
                            <button type="button" class="btn btn-outline-secondary">38</button>
                            <button type="button" class="btn btn-outline-secondary">39</button>
                            <button type="button" class="btn btn-outline-secondary">40</button>
                            <button type="button" class="btn btn-outline-secondary">41</button>
                            <button type="button" class="btn btn-outline-secondary">42</button>
                            <button type="button" class="btn btn-outline-secondary">43</button>
                            <c:if test="${not empty sessionScope.loiChuaLogin}">
                                <label hidden id="chuaDangNhap">${sessionScope.loiChuaLogin}</label>
                            </c:if>
                            <form action="/gio-hang/add/${sp.id}/${sp.gia}" method="post">
                                <button class="btn btn-outline-primary" type="submit" onclick="chuaLogin()" style="margin-top: 70%;margin-left: 30%;">Thêm vào giỏ hàng</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-2"></div>
    </div>
    <div class="row bg-light "style="height: 50px;margin-top: 10%" >
        <div class="col-6 mt-3" style="margin-left: 38%;">
            ©2023 BẢN QUYỀN THUỘC: TRỊNH TRỌNG VŨ
        </div>
    </div>

</div>
<script>
    function chuaLogin(){
        var element = document.getElementById("chuaDangNhap");
        var labelText = element.textContent;
        if (labelText !== null){
            alert(labelText)
        }
    }
</script>
</body>
</html>
