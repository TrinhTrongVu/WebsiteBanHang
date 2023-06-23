package com.example.assignment.service;

import com.example.assignment.entity.HoaDon;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface HoaDonService {
    List<HoaDon> getAll();

    HoaDon getOne(UUID id);

    void add(HoaDon hoaDon);

    void update(HoaDon hoaDon);

    void delete(UUID id);

    HoaDon getByIdTKAndTrangThai(UUID idTK, Integer trangThai);

    List<HoaDon> getByIdTKAndTrangThai1(UUID idTK, Integer trangThai);

    Page<HoaDon> phanTrang(int page,int limit);
}
