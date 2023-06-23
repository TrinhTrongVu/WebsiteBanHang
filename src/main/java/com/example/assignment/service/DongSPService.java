package com.example.assignment.service;

import com.example.assignment.entity.DongSP;
import com.example.assignment.entity.SanPham;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface DongSPService {
    void add(DongSP dongSP);

    List<DongSP> getAll();

    DongSP getOne(UUID id);

    void update(DongSP dongSP);

    void delete(UUID id);

    Page<DongSP> phanTrang(int page, int limit);
}
