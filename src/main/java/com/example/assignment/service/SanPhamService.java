package com.example.assignment.service;

import com.example.assignment.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface SanPhamService {
    List<SanPham> getAll();

    List<SanPham> getAllBySoLuong();

    Page<SanPham> getAllByIdDsp(UUID idDsp,int page, int limit);

    Page<SanPham> phanTrangAndSort(int page, int limit, Sort sort);

    Page<SanPham> phanTrang(int page, int limit);

    SanPham getOne(UUID id);

    SanPham getByMa(String ma);

    void add(SanPham sp);

    void update(SanPham sp);

    void delete(UUID id);
}
