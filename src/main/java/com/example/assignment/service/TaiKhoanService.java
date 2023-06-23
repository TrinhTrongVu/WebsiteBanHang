package com.example.assignment.service;

import com.example.assignment.entity.TaiKhoan;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface TaiKhoanService {
    List<TaiKhoan> getAll();

    TaiKhoan getOne(UUID id);

    void add(TaiKhoan taiKhoan);

    void update(TaiKhoan taiKhoan);

    void delete(UUID id);

    TaiKhoan getByEmail(String email);

    TaiKhoan getByTenDangNhap(String tenDangNhap);

    Page<TaiKhoan> phanTrang(int page,int limit);
}
