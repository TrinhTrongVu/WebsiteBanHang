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
    <div class="row mt-3">
        <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="/banner1.jpg" class="d-block w-100 " alt="..." height="500px">
                </div>
                <div class="carousel-item">
                    <img src="/banner2.jpg" class="d-block w-100 " alt="..." height="500px">
                </div>
                <div class="carousel-item">
                    <img src="/banner3.jpg" class="d-block w-100 " alt="..." height="500px">
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </div>
    <div class="row">
        <c:forEach var="sp" items="${pageData.content}">
            <div class="card ms-5 mt-5"  style="width: 18rem;">
                <a href="/san-pham/detail/${sp.id}"><img src="${sp.anh}" class="card-img-top mt-3" alt="ảnh"></a>
                <div class="card-body">
                    <h5 class="card-title">${sp.ten}</h5>
                    <h5 class="card-title">Giá: ${sp.gia} VNĐ</h5>
                    <input type="hidden"  value="${sessionScope.loiChuaLogin}">
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="row mb-5">
        <nav aria-label="Page navigation example" class="position-absolute mt-5">
            <ul class="pagination " style="margin-left: 45%">
                <li class="page-item">
                </li>
                <c:forEach begin="0" end="${pageData.totalPages}" varStatus="page">
                    <li class="page-item"><a class="page-link" href="/home?page=${page.index}">${page.index+1}</a></li>
                </c:forEach>
                <li class="page-item">
                </li>
            </ul>
        </nav>
    </div>
    <div class="row bg-light "style="height: 50px;margin-top: 10%" >
        <div class="col-6 mt-3" style="margin-left: 38%;">
            ©2023 BẢN QUYỀN THUỘC: TRỊNH TRỌNG VŨ
        </div>
    </div>

</div>
</body>
</html>
