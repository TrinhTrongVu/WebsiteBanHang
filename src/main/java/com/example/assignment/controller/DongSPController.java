package com.example.assignment.controller;

import com.example.assignment.entity.DongSP;
import com.example.assignment.service.DongSPService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping(value = "/admin/dong-san-pham")
public class DongSPController {

    @Autowired
    private DongSPService dongSPService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    private UUID idUpdate;

    @GetMapping()
    public String viewAdd(Model model) {
        model.addAttribute("dongsp", dongSPService.getAll());

        String pageParam = request.getParameter("page");
        String limitParam = request.getParameter("limit");

        int page = pageParam == null ? 0 : Integer.valueOf(pageParam);
        int limit = limitParam == null ? 3 : Integer.valueOf(limitParam);
        model.addAttribute("pageData", dongSPService.phanTrang(page, limit));
        return "admin/dongsp";
    }

    @PostMapping(value = "/add")
    public String add(@RequestParam("ten") String ten,
                      Model model) {
        String p_chu = "^[^\\d]+$";
        if (ten.isEmpty()||ten.isBlank()){
            session.setAttribute("tenDongSP","*Vui lòng nhập thông tin !");
            return "redirect:/admin/dong-san-pham";
        }else if(!ten.matches(p_chu)){
            session.setAttribute("tenDongSP","*Vui lòng không nhập số !");
            return "redirect:/admin/dong-san-pham";
        }else{
            DongSP dongSP = new DongSP(null, ten, null);
            dongSPService.add(dongSP);
            return "redirect:/admin/dong-san-pham";
        }
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(@PathVariable("id") UUID id, Model model) {
        DongSP dongSP = dongSPService.getOne(id);
        model.addAttribute("dsp", dongSP);
        return "forward:/admin/dong-san-pham";
    }

    @GetMapping(value = "/update/{id}")
    public String viewUpdate(@PathVariable("id") UUID id, Model model) {
        idUpdate = id;
        DongSP dongSP = dongSPService.getOne(id);
        model.addAttribute("dsp", dongSP);
        return "forward:/admin/dong-san-pham";
    }

    @PostMapping(value = "/update")
    public String update( @RequestParam("ten") String ten,
                         Model model) {

        String p_chu = "^[^\\d]+$";
        if (ten.isEmpty()||ten.isBlank()){
            session.setAttribute("tenDongSP","*Vui lòng nhập thông tin !");
            return "redirect:/admin/dong-san-pham";
        }else if(!ten.matches(p_chu)){
            session.setAttribute("tenDongSP","*Vui lòng không nhập số !");
            return "redirect:/admin/dong-san-pham";
        }else{
            DongSP dongSP = new DongSP(idUpdate, ten, null);
            dongSPService.update(dongSP);
            return "redirect:/admin/dong-san-pham";
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") UUID id, Model model) {
        dongSPService.delete(id);
        return "forward:/admin/dong-san-pham";
    }

    @GetMapping(value = "/reset")
    public String delete() {
        return "redirect:/admin/dong-san-pham";
    }
}
