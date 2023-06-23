package com.example.assignment.controller;

import com.example.assignment.entity.HoaDon;
import com.example.assignment.entity.HoaDonChiTiet;
import com.example.assignment.entity.TaiKhoan;
import com.example.assignment.service.HoaDonChiTietService;
import com.example.assignment.service.HoaDonService;
import com.example.assignment.service.SanPhamService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;


    @GetMapping(value = "/home")
    public String home(Model model) {
        String pageParam = request.getParameter("page");
        String limitParam = request.getParameter("limit");
        int page = pageParam == null ? 0 : Integer.valueOf(pageParam);
        int limit = limitParam == null ? 8 : Integer.valueOf(limitParam);

        int soLuong = 0;
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("user");
        if (taiKhoan == null) {
            session.setAttribute("slGioHang",soLuong);
            Sort sort = Sort.by(Sort.Direction.ASC, "id");
            model.addAttribute("pageData", sanPhamService.phanTrangAndSort(page, limit, sort));
            return "/home";
        } else {

            Sort sort = Sort.by(Sort.Direction.ASC, "id");
            model.addAttribute("pageData", sanPhamService.phanTrangAndSort(page, limit, sort));

            HoaDon hoaDon = hoaDonService.getByIdTKAndTrangThai(taiKhoan.getId(), 0);
            List<HoaDonChiTiet> list = hoaDonChiTietService.getAllByIdHD(hoaDon.getId());

            soLuong = list.size();
            session.setAttribute("slGioHang",soLuong);
            return "/home";
        }

    }
}
