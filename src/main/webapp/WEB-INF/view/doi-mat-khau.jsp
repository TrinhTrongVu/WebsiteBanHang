<!-- <%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 06/06/2023
  Time: 11:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
-->
<html>
<head>
    <title>Đổi mật khẩu</title>
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
                    <div class="card-body p-5">
                        <h2 class="text-uppercase text-center mb-5">Đổi mật khẩu</h2>

                        <form action="doi-mat-khau" method="post">
                            <div class="col form-outline mb-4 ">
                                <input type="password"  name="matKhauCu" class="form-control form-control-lg"/>
                                <label class="form-label">Mật khẩu hiện tại</label>
                            </div>

                            <div class="col form-outline mb-4 ">
                                <input type="password" name="matKhauMoi"  class="form-control form-control-lg"/>
                                <label class="form-label" >Mật khẩu mới</label>
                            </div>

                            <div class="col form-outline mb-4 ">
                                <input type="password" name="xacNhanMatKhau" class="form-control form-control-lg"/>
                                <label class="form-label" >Xác nhận mật khẩu</label><br>
                                <c:if test="${not empty sessionScope.errorDoiMK}">
                                    <label class="text-danger mt-3">${sessionScope.errorDoiMK}</label>
                                    <c:remove var="errorDoiMK" scope="session"></c:remove>
                                </c:if>
                            </div>

                            <div class="d-flex justify-content-center">
                                <button type="submit"
                                        class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Đổi mật
                                    khẩu
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
