package com.example.assignment.service.Impl;

import com.example.assignment.entity.HoaDonChiTiet;
import com.example.assignment.repository.HoaDonChiTietRepository;
import com.example.assignment.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Override
    public List<HoaDonChiTiet> getAll() {
        return hoaDonChiTietRepository.findAll();
    }

    @Override
    public void add(HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public void update(HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public HoaDonChiTiet getOne(UUID id) {
        return hoaDonChiTietRepository.getReferenceById(id);
    }

    @Override
    public HoaDonChiTiet getByIdSPAndIdHD(UUID idSP, UUID isHD) {
        return hoaDonChiTietRepository.getByIdSPAndIdHD(idSP,isHD);
    }

    @Override
    public List<HoaDonChiTiet> getAllByIdHD(UUID idHD) {
        return hoaDonChiTietRepository.getByIdHD(idHD);
    }

    @Override
    public BigDecimal tongTien(UUID idHD) {
        return hoaDonChiTietRepository.tongTien(idHD);
    }

    @Override
    public void delete(UUID id) {
        hoaDonChiTietRepository.deleteById(id);
    }

    @Override
    public Page<HoaDonChiTiet> phanTrang(int page, int limit) {
        Pageable pageable = PageRequest.of(page,limit);
        return hoaDonChiTietRepository.findAll(pageable);
    }
}
