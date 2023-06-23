package com.example.assignment.controller;

import com.example.assignment.entity.SanPham;
import com.example.assignment.entity.TaiKhoan;
import com.example.assignment.service.TaiKhoanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Controller
public class TaiKhoanController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private HttpSession session;

    @Value("${upload.path}")
    private String fileUpload;

    @Autowired
    private HttpServletRequest request;

    private UUID idUpdate;

    @GetMapping("/admin/tai-khoan")
    public String viewTaiKhoan(Model model){
        String pageParam = request.getParameter("page");
        String limitParam = request.getParameter("limit");

        int page = pageParam == null?0:Integer.valueOf(pageParam);
        int limit = limitParam == null ? 4 : Integer.valueOf(limitParam);
        model.addAttribute("pageData",taiKhoanService.phanTrang(page,limit));
        return "admin/tai-khoan";
    }

    @PostMapping("/admin/tai-khoan/add")
    public String add(@RequestParam("tenDangNhap")String tenDangNhap,
                      @RequestParam("hoTen")String hoTen,
                      @RequestParam("anh")MultipartFile multipartFile,
                      @RequestParam("email")String email,
                      @RequestParam("matKhau")String matKhau,
                      @RequestParam("trangThai")int trangThai,
                      @RequestParam("quyen")int quyen){

        if (tenDangNhap.isEmpty()||hoTen.isEmpty()||email.isEmpty()||matKhau.isEmpty()){
            session.setAttribute("checkSP","*Vui lòng nhập thông tin !");
            return "redirect:/admin/tai-khoan";
        }else if (tenDangNhap.isBlank()||hoTen.isBlank()||email.isBlank()||matKhau.isBlank()){
            session.setAttribute("checkSP","*Vui lòng nhập thông tin !");
            return "redirect:/admin/tai-khoan";
        }else{
            String anh = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), new File(this.fileUpload + anh));
            } catch (IOException e) {
                e.printStackTrace();
            }
            TaiKhoan taiKhoan = new TaiKhoan(null,tenDangNhap,matKhau,hoTen,email,anh,trangThai,quyen,null);
            taiKhoanService.add(taiKhoan);
            return "redirect:/admin/tai-khoan";
        }
    }

    @GetMapping("/admin/tai-khoan/edit")
    public String edit(@RequestParam("idEdit")UUID idEdit,
                       Model model){
        idUpdate = idEdit;
        model.addAttribute("tk",taiKhoanService.getOne(idEdit));
        return "forward:/admin/tai-khoan";
    }

    @PostMapping("/admin/tai-khoan/update")
    public String update(@RequestParam("tenDangNhap")String tenDangNhap,
                      @RequestParam("hoTen")String hoTen,
                      @RequestParam("anh")MultipartFile multipartFile,
                      @RequestParam("email")String email,
                      @RequestParam("matKhau")String matKhau,
                      @RequestParam("trangThai")int trangThai,
                      @RequestParam("quyen")int quyen){

        if (tenDangNhap.isEmpty()||hoTen.isEmpty()||email.isEmpty()||matKhau.isEmpty()){
            session.setAttribute("checkSP","*Vui lòng nhập thông tin !");
            return "redirect:/admin/tai-khoan";
        }else if (tenDangNhap.isBlank()||hoTen.isBlank()||email.isBlank()||matKhau.isBlank()){
            session.setAttribute("checkSP","*Vui lòng nhập thông tin !");
            return "redirect:/admin/tai-khoan";
        }else if (multipartFile.isEmpty()){
            TaiKhoan taiKhoan = taiKhoanService.getOne(idUpdate);
            TaiKhoan taiKhoan1 = new TaiKhoan(idUpdate,tenDangNhap,matKhau,hoTen,email,taiKhoan.getAnh(),trangThai,quyen,null);
            taiKhoanService.update(taiKhoan1);
            return "redirect:/admin/tai-khoan";
        }else{
            String anh = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), new File(this.fileUpload + anh));
            } catch (IOException e) {
                e.printStackTrace();
            }
            TaiKhoan taiKhoan = new TaiKhoan(idUpdate,tenDangNhap,matKhau,hoTen,email,anh,trangThai,quyen,null);
            taiKhoanService.update(taiKhoan);
            return "redirect:/admin/tai-khoan";
        }
    }

    @GetMapping("/admin/tai-khoan/delete/{id}")
    public String delete(@PathVariable("id")UUID id){
        taiKhoanService.delete(id);
        return "redirect:/admin/tai-khoan";
    }

    @GetMapping("/doi-mat-khau")
    public String viewDoiMK(){
        return "doi-mat-khau";
    }

    @PostMapping("/doi-mat-khau")
    public String doiMatKhau(@RequestParam("matKhauCu")String matKhauCu,
                             @RequestParam("matKhauMoi") String matKhauMoi,
                             @RequestParam("xacNhanMatKhau") String xacNhanMatKhau){
        TaiKhoan taiKhoan1 = (TaiKhoan) session.getAttribute("user");
        TaiKhoan taiKhoan = taiKhoanService.getByEmail(taiKhoan1.getEmail());
        if (matKhauCu.isEmpty()||matKhauMoi.isEmpty()||xacNhanMatKhau.isEmpty()){
            session.setAttribute("errorDoiMK","*Vui lòng nhập thông tin");
            return "redirect:/doi-mat-khau";
        }else if(matKhauCu.isBlank()||matKhauMoi.isBlank()||xacNhanMatKhau.isBlank()){
            session.setAttribute("errorDoiMK","*Vui lòng nhập thông tin");
            return "redirect:/doi-mat-khau";
        }else if (!matKhauMoi.equals(xacNhanMatKhau)){
            session.setAttribute("errorDoiMK","*Mật khẩu không khớp. Hãy thử lại!");
            return "redirect:/doi-mat-khau";
        }else if(!taiKhoan.getMatKhau().equals(matKhauCu)){
            session.setAttribute("errorDoiMK","*Sai mật khẩu hiện tại. Hãy thử lại!");
            return "redirect:/doi-mat-khau";
        }else{
            TaiKhoan taiKhoan2 = new TaiKhoan(taiKhoan1.getId(),taiKhoan1.getTenDangNhap(),matKhauMoi,taiKhoan1.getHoTen(),taiKhoan1.getEmail(),taiKhoan1.getAnh(),taiKhoan1.getTrangThai(),taiKhoan1.getAdmin(),null);
            taiKhoanService.update(taiKhoan2);
            return "redirect:/home";
        }
    }

    @GetMapping("/dang-ky")
    public String viewDangKy(){
        return "dang-ky";
    }

    @PostMapping("dang-ky")
    public String dangKy(@RequestParam("tenDangNhap")String tenDangNhap,
                         @RequestParam("hoTen") String hoTen,
                         @RequestParam("matKhau") String matKhau,
                         @RequestParam("email") String email,
                         @RequestParam("anh")MultipartFile multipartFile){
        String p_chu = "^[^\\d]+$";
        TaiKhoan taiKhoan = taiKhoanService.getByEmail(email);
        TaiKhoan taiKhoan1 = taiKhoanService.getByTenDangNhap(tenDangNhap);
        if (hoTen.isEmpty()||matKhau.isEmpty()||email.isEmpty()||tenDangNhap.isEmpty()){
            session.setAttribute("errorDangKy","*Vui lòng nhập đầy đủ thông tin!");
            return "redirect:/dang-ky";
        }else if(hoTen.isBlank()||matKhau.isBlank()||email.isBlank()||tenDangNhap.isBlank()){
            session.setAttribute("errorDangKy","*Vui lòng nhập đầy đủ thông tin!");
            return "redirect:/dang-ky";
        }else if (!(taiKhoan == null)){
            session.setAttribute("errorDangKy","*Email đã được đăng ký!");
            return "redirect:/dang-ky";
        }else if (!(taiKhoan1 == null)){
            session.setAttribute("errorDangKy","*Tên đăng nhập đã tồn tại!");
            return "redirect:/dang-ky";
        }else if(!hoTen.matches(p_chu)){
            session.setAttribute("errorDangKy","*Họ và Tên không được có số.Vui lòng nhập lại!");
            return "redirect:/dang-ky";
        }else if (!email.contains("@")){
            session.setAttribute("errorDangKy","*Email không đúng định dạng.Vui lòng nhập lại!");
            return "redirect:/dang-ky";
        }else {
            String anh = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), new File(this.fileUpload + anh));
            } catch (IOException e) {
                e.printStackTrace();
            }
            TaiKhoan taiKhoan2 = new TaiKhoan(null,tenDangNhap,matKhau,hoTen,email,anh,0,1,null);
            taiKhoanService.add(taiKhoan2);
            return "redirect:/login";
        }
    }
}
