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
    <title>Tài khoản</title>
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
        <h3 class="mt-2 col-6" style="margin-left: 40%">Quản lý tài khoản</h3>
        <form:form class="row g-3 border border-secondary pb-3 mt-3" method="post" enctype="multipart/form-data">
            <div class="col-md-6">
                <label class="form-label">Tên đăng nhập</label>
                <input type="text" name="tenDangNhap" value="${tk.tenDangNhap}" class="form-control">
                <label class="text-danger">${sessionScope.checkSP}</label>
            </div>
            <div class="col-md-6">
                <label class="form-label">Họ và Tên</label>
                <input type="text" name="hoTen" value="${tk.hoTen}" class="form-control">
                <label class="text-danger">${sessionScope.checkSP}</label>
            </div>
            <div class="col-md-6">
                <label class="form-label">Ảnh đại diện</label>
                <input type="file" name="anh" class="form-control">
            </div>
            <div class="col-md-6">
                <label class="form-label">Email</label>
                <input type="text" name="email" value="${tk.email}" class="form-control">
                <label class="text-danger">${sessionScope.checkSP}</label>
            </div>
            <div class="col-md-6">
                <label class="form-label">Mật khẩu</label>
                <input type="text" name="matKhau" value="${tk.matKhau}" class="form-control">
                <label class="text-danger">${sessionScope.checkSP}</label>
                <c:remove var="checkSP" scope="session"></c:remove>
            </div>
            <div class="col-md-6">
                <label class="form-label">Trạng Thái</label>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="trangThai"
                           value="0" ${tk.trangThai=='0'?'checked':''} checked>
                    <label class="form-check-label">Hoạt động</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="trangThai"
                           value="1" ${tk.trangThai=='1'?'checked':''}>
                    <label class="form-check-label">Vô hiệu hóa</label>
                </div>
            </div>
            <div class="col-md-6">
                <label class="form-label">Quyền</label>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="quyen" value="0" ${tk.admin=='0'?'checked':''}
                           checked>
                    <label class="form-check-label">Admin</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="quyen"
                           value="1" ${tk.admin=='1'?'checked':''}>
                    <label class="form-check-label">Khách</label>
                </div>
            </div>
            <div class="col-12">
                <button type="submit" formaction="/admin/tai-khoan/add" class="btn btn-primary">Thêm mới</button>
                <button type="submit" formaction="/admin/tai-khoan/update" class="btn btn-success">Cập nhật</button>
                <button type="submit" formaction="/admin/tai-khoan/reset" class="btn btn-danger">Reset</button>

            </div>
        </form:form>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">ID tài khoản</th>
                <th scope="col">Tên đăng nhập</th>
                <th scope="col">Họ và Tên</th>
                <th scope="col">Ảnh đại diện</th>
                <th scope="col">Email</th>
                <th scope="col">Mật khẩu</th>
                <th scope="col">Trạng thái</th>
                <th scope="col">Quyền</th>
                <th scope="col">Acction</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="tk" items="${pageData.content}" varStatus="viTri">
                <tr>
                    <td>${tk.id}</td>
                    <td>${tk.tenDangNhap}</td>
                    <td>${tk.hoTen}</td>
                    <td><img src="/${tk.anh}" height="50px" width="80px" alt="Ảnh"></td>
                    <td>${tk.email}</td>
                    <td>${tk.matKhau}</td>
                    <td>${tk.trangThai=='0'?'Hoạt động':'Vô hiệu hóa'}</td>
                    <td>${tk.admin=='0'?'Admin':'Khách'}</td>
                    <td>
                        <a class="btn btn-info" href="/admin/tai-khoan/edit?idEdit=${tk.id}" role="button">Edit</a>
                        <a class="btn btn-danger" href="/admin/tai-khoan/delete/${tk.id}" role="button">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="row mb-5">
        <nav aria-label="Page navigation example" class="position-absolute ">
            <ul class="pagination" style="margin-left: 45%;margin-top: 5%">
                <li class="page-item">
                </li>
                <c:forEach begin="0" end="${pageData.totalPages}" varStatus="page">
                    <li class="page-item">
                        <a class="page-link"href="/admin/tai-khoan?page=${page.index}">${page.index+1}</a>
                    </li>
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
