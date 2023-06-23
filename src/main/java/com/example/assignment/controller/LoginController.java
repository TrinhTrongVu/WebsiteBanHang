package com.example.assignment.controller;

import com.example.assignment.entity.HoaDon;
import com.example.assignment.entity.TaiKhoan;
import com.example.assignment.service.HoaDonService;
import com.example.assignment.service.TaiKhoanService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private HttpSession session;

    @GetMapping("/login")
    public String viewLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("tenDangNhap") String tenDangNhap,
                        @RequestParam("password") String password){

        if (tenDangNhap.isEmpty() || password.isEmpty() || tenDangNhap.isBlank() || password.isBlank()){
            session.setAttribute("errorLogin","*Sai username hoặc password !");
            return "redirect:/login";
        }
        TaiKhoan taiKhoan = taiKhoanService.getByTenDangNhap(tenDangNhap);
        if (taiKhoan==null){
            session.setAttribute("errorLogin","*Sai username hoặc password !");
            return "redirect:/login";
        }
        else if(!taiKhoan.getTenDangNhap().equals(tenDangNhap) || !taiKhoan.getMatKhau().equals(password)){
            session.setAttribute("errorLogin","*Sai username hoặc password !");
            return "redirect:/login";
        }else if(taiKhoan.getTrangThai()==1){
            session.setAttribute("errorLogin","*Tài khoản vô hiệu hóa !");
            return "redirect:/login";
        } else {
            if (hoaDonService.getByIdTKAndTrangThai(taiKhoan.getId(),0) == null) {
                HoaDon hoaDon = new HoaDon(null, taiKhoan, null,null,null,null, null, 0, null);
                hoaDonService.add(hoaDon);
                session.removeAttribute("loiChuaLogin");
                return "redirect:/home";
            } else {
                session.setAttribute("user", taiKhoan);
                session.removeAttribute("loiChuaLogin");
                return "redirect:/home";
            }
        }
    }

    @GetMapping("dang-xuat")
    public String dangXuat(){
        session.removeAttribute("user");
        return "redirect:/login";
    }
}
