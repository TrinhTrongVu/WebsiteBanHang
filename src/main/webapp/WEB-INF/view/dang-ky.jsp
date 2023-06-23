<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 06/06/2023
  Time: 11:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Đăng ký</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<section class="vh-100 bg-image"
         style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
    <div class="container h-100">
        <div class="row  justify-content-center align-items-center h-100">
            <div class="col-xl-5">
                <div class="card" style="border-radius: 15px;">
                    <div class="card-body p-4">
                        <h2 class="text-uppercase text-center mb-5">Đăng ký</h2>

                        <form action="/dang-ky" method="post" enctype="multipart/form-data">
                            <div class="col-12 form-outline mb-4 ">
                                <label class="form-label" for="form3Example1cg">Tên đăng nhập</label>
                                <input type="text" name="tenDangNhap" id="form3Example5cg" class="form-control form-control-lg"/>
                            </div>

                            <div class="col-12 form-outline mb-4 ">
                                <label class="form-label" for="form3Example1cg">Họ và Tên</label>
                                <input type="text" name="hoTen" id="form3Example1cg" class="form-control form-control-lg"/>
                            </div>

                            <div class="col-12 form-outline mb-4">
                                <label class="form-label" for="form3Example2cg">Email</label>
                                <input type="email" name="email" id="form3Example2cg" class="form-control form-control-lg"/>
                            </div>

                            <div class="col-12 form-outline mb-4">
                                <label class="form-label" for="form3Example3cg">Mật khẩu</label>
                                <input type="password" name="matKhau" id="form3Example3cg" class="form-control form-control-lg"/>
                            </div>

                            <div class="col-12 form-outline mb-4">
                                <label class="form-label" for="form3Example4cg">Ảnh</label>
                                <input type="file" name="anh" id="form3Example4cg" class="form-control form-control-lg"/>
                                <c:if test="${not empty sessionScope.errorDangKy}">
                                    <label class="form-label mt-3 text-danger" for="form3Example3cg">${sessionScope.errorDangKy}</label>
                                    <c:remove var="errorDangKy" scope="session"></c:remove>
                                </c:if>
                            </div>

                            <div class="d-flex justify-content-center">
                                <button type="submit"
                                        class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Đăng ký
                                </button>
                            </div>

                            <p class="text-center text-muted mt-3 ">Đã có tài khoản?
                                <a href="/login" class="fw-bold text-body"><u>Đăng nhập</u></a></p>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
