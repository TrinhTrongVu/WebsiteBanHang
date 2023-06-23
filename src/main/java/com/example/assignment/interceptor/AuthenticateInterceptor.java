package com.example.assignment.interceptor;

import com.example.assignment.entity.TaiKhoan;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class AuthenticateInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws IOException {
        HttpSession session = request.getSession();
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("user");
        if (taiKhoan == null){
            response.sendRedirect("/login");
            return false;
        }else if(taiKhoan.getAdmin()==1){
            response.sendRedirect("/home");
            return false;
        }
        return true;
    }
}
