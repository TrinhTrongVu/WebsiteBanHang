<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 06/06/2023
  Time: 10:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<section class="vh-100">
    <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-9 col-lg-6 col-xl-5">
                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
                     class="img-fluid" alt="Sample image">
            </div>
            <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                <form action="/login" method="post">
                    <!-- Email input -->
                    <div class="form-outline mb-5">
                        <input type="text" name="tenDangNhap" id="form3Example3" class="form-control form-control-lg" placeholder="Username"/>
                    </div>
                    <!-- Password input -->
                    <div class="form-outline mb-3">
                        <input type="password" name="password" id="form3Example4" class="form-control form-control-lg" placeholder="Password"/>
                        <c:if test="${not empty sessionScope.errorLogin}">
                            <label class="text-danger mt-3">${sessionScope.errorLogin}</label>
                            <c:remove var="errorLogin" scope="session"></c:remove>
                        </c:if>
                    </div>
                    <div class="text-center text-lg-start mt-3 pt-2">
                        <button type="submit" class="btn btn-primary btn-lg"
                                style="padding-left: 2.5rem; padding-right: 2.5rem;">Đăng nhập
                        </button>
                        <p class="small fw-bold mt-2 pt-1 mb-0">Bạn chưa có tài khoản?
                            <a href="/dang-ky" class="link-danger">Đăng ký</a></p>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
</body>

</html>
