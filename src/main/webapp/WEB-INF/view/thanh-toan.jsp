<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 10/06/2023
  Time: 10:44 PM
<!-- <%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 01/06/2023
  Time: 10:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Trang chủ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

    <link rel="icon" type="image/x-icon" href="assets/favicon.ico"/>
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light ">
            <div class="container px-4 px-lg-5">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false"
                        aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">

                        <li class="nav-item"><a class="nav-link fs-4" href="/home">Trang chủ</a></li>
                        <li class="nav-item"><a class="nav-link fs-5 mt-1" href="/san-pham">Sản phẩm</a></li>
                        <li class="nav-item"><a class="nav-link fs-5 mt-1" >Tin tức</a></li>
                        <li class="nav-item"><a class="nav-link fs-5 mt-1" >Sale</a></li>
                        <c:if test="${sessionScope.user.admin==0}">
                            <li class="nav-item dropdown ">
                                <a class="nav-link dropdown-toggle fs-5 mt-1" id="navbarDropdown" href="#" role="button"
                                   data-bs-toggle="dropdown"
                                   aria-expanded="false">Quản trị</a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="/admin/hoa-don">Quản lý hóa đơn</a></li>
                                    <li><a class="dropdown-item" href="/admin/tai-khoan">Quản lý tài khoản</a></li>
                                    <li><a class="dropdown-item" href="/admin/san-pham">Quản lý sản phẩm</a></li>
                                    <li><a class="dropdown-item" href="/admin/dong-san-pham">Quản lý dòng sản phẩm</a>
                                    </li>
                                    <li><a class="dropdown-item" href="/admin/hoa-don-chi-tiet">Quản lý hóa đơn chi
                                        tiết</a></li>
                                </ul>
                            </li>
                        </c:if>
                    </ul>
                    <c:if test="${empty sessionScope.user}">
                        <a href="/dang-ky" class="btn btn-outline-success me-2">Đăng ký</a>
                        <a href="/login" class="btn btn-outline-success">Đăng nhập</a>
                    </c:if>
                    <c:if test="${not empty sessionScope.user}">
                        <a class="btn btn-outline-dark me-2" href="/gio-hang"><span
                                class="badge bg-dark text-white ms-1 rounded-pill">${sessionScope.slGioHang}</span>
                            Cart
                            <i class="bi-cart-fill me-1"></i></a>
                        <div class="dropdown me-5">
                            <button class="btn  btn-outline-dark rounded-circle" type="button" data-bs-toggle="dropdown"
                                    aria-expanded="false">
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
        <section class="h-100 gradient-form" style="background-color: #eee;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-xl-10">
                        <div class="card rounded-3 text-black">
                            <div class="row g-0">
                                <div class="col-lg-6">
                                    <div class="card-body p-md-5 mx-md-4">


                                        <form action="/thanh-toan/add" method="post">
                                            <div class="form-outline mb-4">
                                                <label class="form-label">Tên người nhận</label>
                                                <input type="text" name="ten" class="form-control"/>
                                                <label class="text-danger">${sessionScope.error}</label>
                                            </div>

                                            <div class="form-outline mb-4">
                                                <label class="form-label">Email</label>
                                                <input type="email" name="email" class="form-control"/>
                                                <label class="text-danger">${sessionScope.error}</label>
                                            </div>

                                            <div class="form-outline mb-4">
                                                <label class="form-label">Số điện thoại</label>
                                                <input type="text" name="sdt" class="form-control"/>
                                                <label class="text-danger">${sessionScope.error}</label>
                                            </div>

                                            <div class="form-outline mb-4">
                                                <label class="form-label">Địa chỉ</label>
                                                <textarea class="form-control" name="diaChi"></textarea>
                                                <label class="text-danger">${sessionScope.error}</label>
                                                <c:remove var="error" scope="session"></c:remove>
                                            </div>

                                            <div class="d-flex align-items-center justify-content-center pb-3 mt-5">
                                                <button type="submit" class="btn btn-outline-success">Thanh toán
                                                </button>
                                            </div>
                                        </form>

                                    </div>
                                </div>
                                <div class="col-lg-6  border-start p-3">

                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col">STT</th>
                                            <th scope="col">Tên sản phẩm</th>
                                            <th scope="col">Số lượng</th>
                                            <th scope="col">Đơn giá</th>
                                            <th scope="col">Thành tiền</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="gh" items="${gioHang}" varStatus="viTri">
                                            <tr>
                                                <th>${viTri.index+1}</th>
                                                <td>${gh.sanPham.ten}</td>
                                                <td>${gh.soLuong}</td>
                                                <td>${gh.gia} VNĐ</td>
                                                <td>${gh.gia*gh.soLuong} VNĐ</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <h5 class="float-end">Tổng tiền: ${tongTien} VNĐ</h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <div class="row bg-light "style="height: 50px;" >
        <div class="col-6 mt-3" style="margin-left: 38%;">
            ©2023 BẢN QUYỀN THUỘC: TRỊNH TRỌNG VŨ
        </div>
    </div>
</div>
</body>
</html>

