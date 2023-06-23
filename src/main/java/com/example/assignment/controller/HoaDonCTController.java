package com.example.assignment.controller;

import com.example.assignment.entity.HoaDon;
import com.example.assignment.entity.HoaDonChiTiet;
import com.example.assignment.entity.SanPham;
import com.example.assignment.service.HoaDonChiTietService;
import com.example.assignment.service.HoaDonService;
import com.example.assignment.service.SanPhamService;
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

import java.math.BigDecimal;
import java.util.UUID;

@Controller
@RequestMapping("/admin/hoa-don-chi-tiet")
public class HoaDonCTController {

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    private UUID idUpdate;

    @GetMapping()
    public String view(Model model){
        String pageParam = request.getParameter("page");
        String limitParam = request.getParameter("limit");

        int page = pageParam == null ? 0 : Integer.valueOf(pageParam);
        int limit = limitParam == null ? 4 : Integer.valueOf(limitParam);
        model.addAttribute("pageData",hoaDonChiTietService.phanTrang(page,limit));
        return "admin/hoa-don-chi-tiet";
    }

    @PostMapping("/add")
    public String add(@RequestParam("idHD")String idHD,
                      @RequestParam("idSP")String idSP,
                      @RequestParam("gia")String gia,
                      @RequestParam("soLuong")String soLuong){
        if (idHD.isEmpty()||idSP.isEmpty()||gia.isEmpty()||soLuong.isEmpty()){
            session.setAttribute("checkSP","*Vui lòng nhập thông tin !!");
            return "redirect:/admin/hoa-don-chi-tiet";
        }else if (idHD.isBlank()||idSP.isBlank()||gia.isBlank()||soLuong.isBlank()){
            session.setAttribute("checkSP","*Vui lòng nhập thông tin !!");
            return "redirect:/admin/hoa-don-chi-tiet";
        }else{
            HoaDon hoaDon = hoaDonService.getOne(UUID.fromString(idHD));
            SanPham sanPham = sanPhamService.getOne(UUID.fromString(idSP));
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(null,hoaDon,sanPham,BigDecimal.valueOf(Long.parseLong(gia)),Integer.valueOf(soLuong));
            hoaDonChiTietService.add(hoaDonChiTiet);
        }
        return "redirect:/admin/hoa-don-chi-tiet";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("idEdit")UUID idEdit,
                       Model model){
        idUpdate = idEdit;
        model.addAttribute("hdct",hoaDonChiTietService.getOne(idEdit));
        return "forward:/admin/hoa-don-chi-tiet";
    }

    @PostMapping("/update")
    public String update(@RequestParam("idHD")String idHD,
                      @RequestParam("idSP")String idSP,
                      @RequestParam("gia")String gia,
                      @RequestParam("soLuong")String soLuong){
        if (idHD.isEmpty()||idSP.isEmpty()||gia.isEmpty()||soLuong.isEmpty()){
            session.setAttribute("checkSP","*Vui lòng nhập thông tin !!");
            return "redirect:/admin/hoa-don-chi-tiet";
        }else if (idHD.isBlank()||idSP.isBlank()||gia.isBlank()||soLuong.isBlank()){
            session.setAttribute("checkSP","*Vui lòng nhập thông tin !!");
            return "redirect:/admin/hoa-don-chi-tiet";
        }else{
            HoaDon hoaDon = hoaDonService.getOne(UUID.fromString(idHD));
            SanPham sanPham = sanPhamService.getOne(UUID.fromString(idSP));
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(idUpdate,hoaDon,sanPham,BigDecimal.valueOf(Long.parseLong(gia)),Integer.valueOf(soLuong));
            hoaDonChiTietService.add(hoaDonChiTiet);
        }
        return "redirect:/admin/hoa-don-chi-tiet";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")UUID id){
        hoaDonChiTietService.delete(id);
        return "forward:/admin/hoa-don-chi-tiet";
    }

    @PostMapping("/reset")
    public String reset(){
        return "redirect:/admin/hoa-don-chi-tiet";
    }

}
