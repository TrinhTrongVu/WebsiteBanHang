package com.example.assignment.controller;

import com.example.assignment.entity.HoaDon;
import com.example.assignment.entity.HoaDonChiTiet;
import com.example.assignment.entity.TaiKhoan;
import com.example.assignment.service.HoaDonChiTietService;
import com.example.assignment.service.HoaDonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
public class ThanhToanController {
    @Autowired
    private HttpSession session;

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    HoaDonService hoaDonService;
    @GetMapping("/thanh-toan")
    public String viewThanhToan(Model model){
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("user");
        if (taiKhoan==null){
            return "thanh-toan";
        }else{
            HoaDon hoaDon = hoaDonService.getByIdTKAndTrangThai(taiKhoan.getId(),0);
            model.addAttribute("gioHang", hoaDonChiTietService.getAllByIdHD(hoaDon.getId()));
            model.addAttribute("tongTien", hoaDonChiTietService.tongTien(hoaDon.getId()));
            return "thanh-toan";
        }
    }

    @GetMapping("/hoa-don/da-thanh-toan")
    public String daThanhToan(Model model){
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("user");
        List<HoaDon> listHoaDon = hoaDonService.getByIdTKAndTrangThai1(taiKhoan.getId(),1);
        model.addAttribute("listHoaDon",listHoaDon);
        return "da-thanh-toan";
    }

    @GetMapping("/hoa-don/da-thanh-toan/{id}")
    public String daThanhToanCT(@PathVariable("id")UUID id,
                                Model model){
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("user");

        List<HoaDon> listHoaDon = hoaDonService.getByIdTKAndTrangThai1(taiKhoan.getId(),1);
        List<HoaDonChiTiet> list = hoaDonChiTietService.getAllByIdHD(id);
        model.addAttribute("listHoaDon",listHoaDon);
        model.addAttribute("listHoaDonCT",list);
        return "da-thanh-toan";
    }

    @PostMapping("/thanh-toan/add")
    public String add(@RequestParam("ten")String ten,
                      @RequestParam("email")String email,
                      @RequestParam("sdt")String sdt,
                      @RequestParam("diaChi")String diaChi){
        String p_chu = "^[^\\d]+$";
        String p_sdt = "0[0-9]{9}";
        if (ten.isEmpty()||email.isEmpty()||sdt.isEmpty()||diaChi.isEmpty()){
            session.setAttribute("error","Vui lòng nhập thông tin !!!");
            return "redirect:/thanh-toan";
        }else if (ten.isBlank()||email.isBlank()||sdt.isBlank()||diaChi.isBlank()){
            session.setAttribute("error","Vui lòng nhập thông tin !!!");
            return "redirect:/thanh-toan";
        }else if (!ten.matches(p_chu)){
            session.setAttribute("error","Vui lòng nhập thông tin !!!");
            return "redirect:/thanh-toan";
        }else if (!sdt.matches(p_sdt)){
            session.setAttribute("error","Vui lòng nhập thông tin !!!");
            return "redirect:/thanh-toan";
        }else{
            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new Date(date.getTime());
            TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("user");
            HoaDon hoaDon = hoaDonService.getByIdTKAndTrangThai(taiKhoan.getId(),0);
            HoaDon hoaDon1 = new HoaDon(hoaDon.getId(),hoaDon.getTaiKhoan(),sqlDate,ten,email,sdt,diaChi,1,null);
            hoaDonService.update(hoaDon1);
            HoaDon hoaDon2 = new HoaDon(null,taiKhoan,null,null,null,null,null,0,null);
            hoaDonService.add(hoaDon2);
            return "redirect:/hoa-don/da-thanh-toan";
        }
    }




}
