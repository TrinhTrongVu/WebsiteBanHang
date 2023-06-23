package com.example.assignment.controller;

import com.example.assignment.entity.HoaDon;
import com.example.assignment.entity.HoaDonChiTiet;
import com.example.assignment.entity.SanPham;
import com.example.assignment.entity.TaiKhoan;
import com.example.assignment.service.HoaDonChiTietService;
import com.example.assignment.service.HoaDonService;
import com.example.assignment.service.SanPhamService;
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
@RequestMapping(value = "/gio-hang")
public class GioHangController {

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private HttpSession session;

    @GetMapping()
    public String viewGioHang(Model model){
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("user");
        if (taiKhoan==null){
            return "view-gio-hang";
        }else{
            HoaDon hoaDon = hoaDonService.getByIdTKAndTrangThai(taiKhoan.getId(),0);
            model.addAttribute("gioHang", hoaDonChiTietService.getAllByIdHD(hoaDon.getId()));
            model.addAttribute("tongTien", hoaDonChiTietService.tongTien(hoaDon.getId()));
            return "gio-hang";
        }

    }

    @PostMapping(value = "/add/{id}/{gia}")
    public String add(@PathVariable("id") UUID id,
                      @PathVariable("gia")BigDecimal gia){
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("user");
        if (taiKhoan == null){
            session.setAttribute("loiChuaLogin","Vui lòng đăng nhập !!!");
            System.out.println(session.getAttribute("loiChuaLogin"));
            return "redirect:/login";
        }else{
            SanPham sanPham = sanPhamService.getOne(id);
            HoaDon hoaDon = hoaDonService.getByIdTKAndTrangThai(taiKhoan.getId(),0);
            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getByIdSPAndIdHD(id,hoaDon.getId());
            if (hoaDonChiTiet == null){
                HoaDonChiTiet hoaDonChiTiet1 = new HoaDonChiTiet(null,hoaDon,sanPham,gia,1);
                hoaDonChiTietService.add(hoaDonChiTiet1);
                return "redirect:/gio-hang";
            }else{
                HoaDonChiTiet hoaDonChiTiet2 = new HoaDonChiTiet(hoaDonChiTiet.getId(),hoaDonChiTiet.getHoaDon(),hoaDonChiTiet.getSanPham(),hoaDonChiTiet.getGia(),hoaDonChiTiet.getSoLuong()+1);
                hoaDonChiTietService.add(hoaDonChiTiet2);
                return "redirect:/gio-hang";
            }

        }
    }

    @PostMapping(value = "/update/{id}")
    public String update(@PathVariable("id")UUID id,
                         @RequestParam("soLuong")String soLuong){
        HoaDonChiTiet hoaDonCT =  hoaDonChiTietService.getOne(id);
        String p_so = "^[0-9]+$";
        if (!soLuong.matches(p_so)){
            hoaDonChiTietService.update(new HoaDonChiTiet(hoaDonCT.getId(),hoaDonCT.getHoaDon(),hoaDonCT.getSanPham(),hoaDonCT.getGia(),hoaDonCT.getSoLuong()));
            return "redirect:/gio-hang";
        }else if(Integer.valueOf(soLuong) == 0){
            hoaDonChiTietService.delete(id);
            return "redirect:/gio-hang";
        }
        else {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(hoaDonCT.getId(),hoaDonCT.getHoaDon(),hoaDonCT.getSanPham(),hoaDonCT.getGia(),Integer.valueOf(soLuong));
            hoaDonChiTietService.add(hoaDonChiTiet);
            return "redirect:/gio-hang";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")UUID id){
        hoaDonChiTietService.delete(id);
        return "redirect:/gio-hang";
    }


}
