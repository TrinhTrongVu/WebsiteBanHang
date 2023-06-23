package com.example.assignment.service;

import com.example.assignment.entity.HoaDonChiTiet;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface HoaDonChiTietService {

    List<HoaDonChiTiet> getAll();

    void add(HoaDonChiTiet hoaDonChiTiet);

    void update(HoaDonChiTiet hoaDonChiTiet);

    HoaDonChiTiet getOne(UUID id);

    HoaDonChiTiet getByIdSPAndIdHD(UUID idSP, UUID idHD);

    List<HoaDonChiTiet> getAllByIdHD(UUID idHD);

    BigDecimal tongTien(UUID idHD);

    void delete(UUID id);

    Page<HoaDonChiTiet> phanTrang(int page,int limit);
}
