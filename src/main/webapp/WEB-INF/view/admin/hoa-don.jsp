<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 01/06/2023
  Time: 10:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hóa Đơn</title>
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
                                <img src="/${sessionScope.user.anh}" style="height: 30px; width: 20px;">
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
        <h3 class="col-6 mt-2" style="margin-left: 40%">Quản lý hóa đơn</h3>
        <form:form class="row g-3 border border-secondary pb-3 mt-3" method="post">
            <div class="col-md-6">
                <label class="form-label">ID Tài khoản</label>
                <input type="text" name="idTK" value="${hd.taiKhoan.id}" class="form-control">
                <label class="text-danger">${sessionScope.checkSP}</label>
            </div>
            <div class="col-md-6">
                <label class="form-label">Người nhận</label>
                <input type="text" name="nguoiNhan" value="${hd.nguoiNhan}" class="form-control">
                <label class="text-danger">${sessionScope.checkSP}</label>
            </div>
            <div class="col-md-6">
                <label class="form-label">Email</label>
                <input type="text" name="email" value="${hd.email}" class="form-control">
                <label class="text-danger">${sessionScope.checkSP}</label>
            </div>
            <div class="col-md-6">
                <label class="form-label">SĐT</label>
                <input type="text" name="sdt" value="${hd.sdt}" class="form-control">
                <label class="text-danger">${sessionScope.checkSP}</label>
            </div>
            <div class="col-md-6">
                <label class="form-label">Địa chỉ</label>
                <input type="text" name="diaChi" value="${hd.diaChi}" class="form-control">
                <label class="text-danger">${sessionScope.checkSP}</label>
            </div>
            <div class="col-md-6">
                <label class="form-label">Ngày thanh toán</label>
                <input type="date" name="ngayThanhToan" value="${hd.ngayThanhToan}" class="form-control">
                <label class="text-danger">${sessionScope.checkSP}</label>
                <c:remove var="checkSP" scope="session"></c:remove>
            </div>
            <div class="col-md-6">
                <label class="form-label">Trạng Thái</label>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="trangThai"
                           value="0" ${hd.trangThai=='0'?'checked':''} checked>
                    <label class="form-check-label">Chưa thanh toán</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="trangThai"
                           value="1" ${hd.trangThai=='1'?'checked':''}>
                    <label class="form-check-label">Đã thanh toán</label>
                </div>
            </div>
            <div class="col-12">
                <button type="submit" formaction="/admin/hoa-don/add" class="btn btn-primary">Thêm mới</button>
                <button type="submit" formaction="/admin/hoa-don/update" class="btn btn-success">Cập nhật</button>
                <button type="submit" formaction="/admin/hoa-don/reset" class="btn btn-danger">Reset</button>

            </div>
        </form:form>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">ID Hóa đơn</th>
                <th scope="col">ID Tài khoản</th>
                <th scope="col">Người nhận</th>
                <th scope="col">Ngày thanh toán</th>
                <th scope="col">Email</th>
                <th scope="col">SĐT</th>
                <th scope="col">Địa Chỉ</th>
                <th scope="col">Trạng thái</th>
                <th scope="col">Acction</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="hd" items="${pageData.content}" varStatus="viTri">
                <tr>
                    <td>${hd.id}</td>
                    <td>${hd.taiKhoan.id}</td>
                    <td>${hd.nguoiNhan}</td>
                    <td>${hd.ngayThanhToan}</td>
                    <td>${hd.email}</td>
                    <td>${hd.sdt}</td>
                    <td>${hd.diaChi}</td>
                    <td>${hd.trangThai=='0'?'Chưa thanh toán':'Đã thanh toán'}</td>
                    <td>
                        <a class="btn btn-info" href="/admin/hoa-don/edit?idEdit=${hd.id}" role="button">Edit</a>
                        <c:if test="${hd.trangThai==0}">
                            <a class="btn btn-danger" href="/admin/hoa-don/delete/${hd.id}" role="button">Xóa</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="row mb-5">
        <nav aria-label="Page navigation example" class="position-absolute ">
            <ul class="pagination"  style="margin-left: 45%;margin-top: 15%">
                <li class="page-item">
                </li>
                <c:forEach begin="0" end="${pageData.totalPages}" varStatus="page">
                    <li class="page-item">
                        <a class="page-link"href="/admin/hoa-don?page=${page.index}">${page.index+1}</a>
                    </li>
                </c:forEach>
                <li class="page-item">
                </li>
            </ul>
        </nav>
    </div>
    <div class="row bg-light "style="height: 50px;margin-top: 20%" >
        <div class="col-6 mt-3" style="margin-left: 38%;">
            ©2023 BẢN QUYỀN THUỘC: TRỊNH TRỌNG VŨ
        </div>
    </div>
</div>
</body>
</html>
