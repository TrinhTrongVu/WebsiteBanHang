package com.example.assignment.controller;

import com.example.assignment.entity.DongSP;
import com.example.assignment.entity.SanPham;
import com.example.assignment.service.DongSPService;
import com.example.assignment.service.SanPhamService;
import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Controller
public class SanPhamController<ParamService> {

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private DongSPService dongSPService;

    @Value("${upload.path}")
    private String fileUpload;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    private UUID idUpdate;

    @GetMapping("/san-pham")
    public String viewSanPham(Model model){
        String pageParam = request.getParameter("page");
        String limitParam = request.getParameter("limit");

        int page = pageParam == null ? 0 : Integer.valueOf(pageParam);
        int limit = limitParam == null ? 9 : Integer.valueOf(limitParam);
        model.addAttribute("pageData",sanPhamService.phanTrang(page,limit));
        model.addAttribute("dsp",dongSPService.getAll());
        return "view-san-pham";
    }

    @GetMapping("/san-pham/{id}")
    public String viewSanPhamDetail(@PathVariable("id")UUID idDsp,
                                    Model model){
        String pageParam = request.getParameter("page");
        String limitParam = request.getParameter("limit");

        int page = pageParam == null ? 0 : Integer.valueOf(pageParam);
        int limit = limitParam == null ? 8 : Integer.valueOf(limitParam);
        model.addAttribute("pageData",sanPhamService.getAllByIdDsp(idDsp,page,limit));
        model.addAttribute("dsp",dongSPService.getAll());
        return "view-san-pham";
    }

    @GetMapping("/admin/san-pham")
    public String viewAdminSanPham(Model model) {
        model.addAttribute("dsp", dongSPService.getAll());
        model.addAttribute("sp", sanPhamService.getAll());

        String sortBy = request.getParameter("sapXep");
        String sortDirection = request.getParameter("thuTu");
        String pageParam = request.getParameter("page");
        String limitParam = request.getParameter("limit");

        String sortField = sortBy == null ? "id" : sortBy;
        Sort sort = (sortDirection == null || sortDirection.equals("asc")) ?
                Sort.by(Sort.Direction.ASC, sortField) :
                Sort.by(Sort.Direction.DESC, sortField);

        int page = pageParam == null ? 0 : Integer.valueOf(pageParam);
        int limit = limitParam == null ? 3 : Integer.valueOf(limitParam);
        model.addAttribute("pageData", sanPhamService.phanTrangAndSort(page, limit, sort));
        return "admin/san-pham";
    }

    @PostMapping(value = "/admin/san-pham/add")
    public String add(@RequestParam("ma") String ma,
                      @RequestParam("ten") String ten,
                      @RequestParam("gia") String gia,
                      @RequestParam("ngayTao") String ngayTao,
                      @RequestParam("trangThai") Integer trangThai,
                      @RequestParam("anh") MultipartFile multipartFile,
                      @RequestParam("dongsp") DongSP dongSP,
                      Model model
    ) {

        String p_chu = "^[^\\d]+$";
        SanPham sanPham = sanPhamService.getByMa(ma);
        if (ten.isBlank() || gia.isBlank() || ngayTao.isBlank() || ma.isEmpty()) {
            session.setAttribute("checkSP", "*Vui lòng nhập thông tin !");
            return "redirect:/admin/san-pham";
        } else if (ten.isEmpty() || gia.isEmpty() || ngayTao.isEmpty() || multipartFile.isEmpty() || ma.isBlank()) {
            session.setAttribute("checkSP", "*Vui lòng nhập thông tin !");
            return "redirect:/admin/san-pham";
        }else if (gia.matches(p_chu)) {
            session.setAttribute("checkSP", "*Vui lòng nhập thông tin !!");
            return "redirect:/admin/san-pham";
        } else {
            String anh = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), new File(this.fileUpload + anh));
            } catch (IOException e) {
                e.printStackTrace();
            }
            SanPham sanPham1 = new SanPham(null, dongSP, ma, ten, anh, BigDecimal.valueOf(Long.parseLong(gia)), Date.valueOf(ngayTao), trangThai, null);
            sanPhamService.add(sanPham1);
            return "redirect:/admin/san-pham";
        }

    }

    @GetMapping(value = "/admin/san-pham/delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        sanPhamService.delete(id);
        return "redirect:/admin/san-pham";
    }

    @GetMapping(value = "/admin/san-pham/edit")
    public String edit(@RequestParam("idEdit") UUID id, Model model) {
        idUpdate = id;
        SanPham sanPham = sanPhamService.getOne(id);
        model.addAttribute("SP", sanPham);
        return "forward:/admin/san-pham";
    }

    @PostMapping(value = "/admin/san-pham/update")
    public String update(@RequestParam("ma") String ma,
                         @RequestParam("ten") String ten,
                         @RequestParam("gia") String gia,
                         @RequestParam("ngayTao") String ngayTao,
                         @RequestParam("trangThai") Integer trangThai,
                         @RequestParam("anh") MultipartFile multipartFile,
                         @RequestParam("dongsp") DongSP dongSP,
                         Model model) {

        String p_chu = "^[^\\d]+$";
        SanPham sanPham = sanPhamService.getByMa(ma);
        if (ten.isBlank() || gia.isBlank() || ngayTao.isBlank() || ma.isEmpty()) {
            session.setAttribute("checkSP", "*Vui lòng nhập thông tin 1!");
            return "redirect:/admin/san-pham";
        } else if (ten.isEmpty() || gia.isEmpty() || ngayTao.isEmpty() || ma.isBlank()) {
            session.setAttribute("checkSP", "*Vui lòng nhập thông tin 2!");
            return "redirect:/admin/san-pham";
        }else if (gia.matches(p_chu)) {
            session.setAttribute("checkGiaSP", "*Vui lòng không nhập chữ !");
            return "redirect:/admin/san-pham";
        } else if (multipartFile.isEmpty()) {
            SanPham sanPhamGetById = sanPhamService.getOne(idUpdate);
            SanPham sanPham1 = new SanPham(idUpdate, dongSP, ma, ten, sanPhamGetById.getAnh(), BigDecimal.valueOf(Long.parseLong(gia)), Date.valueOf(ngayTao), trangThai, null);
            sanPhamService.update(sanPham1);
        } else {
            String anh = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), new File(this.fileUpload + anh));
            } catch (IOException e) {
                e.printStackTrace();
            }
            SanPham sanPham2 = new SanPham(idUpdate, dongSP, ma, ten, anh, BigDecimal.valueOf(Long.parseLong(gia)), Date.valueOf(ngayTao), trangThai, null);
            sanPhamService.update(sanPham2);
        }
        return "redirect:/admin/san-pham";
    }

    @PostMapping(value = "/admin/san-pham/reset")
    public String reset() {
        return "redirect:/admin/san-pham";
    }

    @GetMapping("/san-pham/detail/{id}")
    public String detail(@PathVariable("id")UUID id,
                         Model model){
        model.addAttribute("sp",sanPhamService.getOne(id));
        return "view-san-pham-detail";
    }


}
