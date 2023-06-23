package com.example.assignment.controller;

import com.example.assignment.entity.HoaDon;
import com.example.assignment.entity.TaiKhoan;
import com.example.assignment.service.HoaDonService;
import com.example.assignment.service.TaiKhoanService;
import jakarta.servlet.http.HttpServletRequest;
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
import java.util.UUID;

@Controller
@RequestMapping("/admin/hoa-don")
public class HoaDonController {

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private HttpSession session;

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private HttpServletRequest request;

    private UUID idUpdate;

    @GetMapping()
    public String viewHoaDon(Model model){
        String pageParam = request.getParameter("page");
        String limitParam = request.getParameter("limit");


        int page = pageParam == null ? 0:Integer.valueOf(pageParam);
        int limit = limitParam == null ? 4: Integer.valueOf(limitParam);
        model.addAttribute("pageData",hoaDonService.phanTrang(page,limit));
        return "admin/hoa-don";
    }

    @PostMapping("/add")
    public String add(@RequestParam("idTK")String idTaiKhoan,
                      @RequestParam("nguoiNhan") String nguoiNhan,
                      @RequestParam("email") String email,
                      @RequestParam("sdt")String sdt,
                      @RequestParam("diaChi") String diaChi,
                      @RequestParam("ngayThanhToan")String ngayThanhToan,
                      @RequestParam("trangThai")int trangThai){
        if (idTaiKhoan.isEmpty()||nguoiNhan.isEmpty()||email.isEmpty()||sdt.isEmpty()||diaChi.isEmpty()||ngayThanhToan.isEmpty()){
            session.setAttribute("checkSP","*Vui lòng nhập thông tin !!");
            return "redirect:/admin/hoa-don";
        }else if (idTaiKhoan.isBlank()||nguoiNhan.isBlank()||email.isBlank()||sdt.isBlank()||diaChi.isBlank()||ngayThanhToan.isBlank()){
            session.setAttribute("checkSP","*Vui lòng nhập thông tin !!");
            return "redirect:/admin/hoa-don";
        }else{
            TaiKhoan taiKhoan1= taiKhoanService.getOne(UUID.fromString(idTaiKhoan));
            HoaDon hoaDon = new HoaDon(null,taiKhoan1, Date.valueOf(ngayThanhToan),nguoiNhan,email,sdt,diaChi,trangThai,null);
            hoaDonService.add(hoaDon);
            return "redirect:/admin/hoa-don";
        }
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("idEdit") UUID idEdit,
                       Model model){
        idUpdate = idEdit;
        model.addAttribute("hd",hoaDonService.getOne(idEdit));
        return "forward:/admin/hoa-don";
    }

    @PostMapping("/update")
    public String update(@RequestParam("idTK")String idTaiKhoan,
                      @RequestParam("nguoiNhan") String nguoiNhan,
                      @RequestParam("email") String email,
                      @RequestParam("sdt")String sdt,
                      @RequestParam("diaChi") String diaChi,
                      @RequestParam("ngayThanhToan")String ngayThanhToan,
                      @RequestParam("trangThai")int trangThai){
        if (idTaiKhoan.isEmpty()||nguoiNhan.isEmpty()||email.isEmpty()||sdt.isEmpty()||diaChi.isEmpty()||ngayThanhToan.isEmpty()){
            session.setAttribute("checkSP","*Vui lòng nhập thông tin !!");
            return "redirect:/admin/hoa-don";
        }else if (idTaiKhoan.isBlank()||nguoiNhan.isBlank()||email.isBlank()||sdt.isBlank()||diaChi.isBlank()||ngayThanhToan.isBlank()){
            session.setAttribute("checkSP","*Vui lòng nhập thông tin !!");
            return "redirect:/admin/hoa-don";
        }else{
            TaiKhoan taiKhoan1= taiKhoanService.getOne(UUID.fromString(idTaiKhoan));
            HoaDon hoaDon = new HoaDon(idUpdate,taiKhoan1, Date.valueOf(ngayThanhToan),nguoiNhan,email,sdt,diaChi,trangThai,null);
            hoaDonService.add(hoaDon);
            return "redirect:/admin/hoa-don";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")UUID id){
        hoaDonService.delete(id);
        return "redirect:/admin/hoa-don";
    }

    @GetMapping("/reset")
    public String reset(){
        return "redirect:/admin/hoa-don";
    }
}
