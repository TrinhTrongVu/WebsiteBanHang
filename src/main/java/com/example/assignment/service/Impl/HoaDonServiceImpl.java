package com.example.assignment.service.Impl;

import com.example.assignment.entity.HoaDon;
import com.example.assignment.repository.HoaDonRepository;
import com.example.assignment.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Override
    public List<HoaDon> getAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public HoaDon getOne(UUID id) {
        return hoaDonRepository.getReferenceById(id);
    }

    @Override
    public void add(HoaDon hoaDon) {
        hoaDonRepository.save(hoaDon);
    }

    @Override
    public void update(HoaDon hoaDon) {
        hoaDonRepository.save(hoaDon);
    }

    @Override
    public void delete(UUID id) {
        hoaDonRepository.deleteById(id);
    }

    @Override
    public HoaDon getByIdTKAndTrangThai(UUID idTK , Integer trangThai) {
        return hoaDonRepository.getByIdTKAndTrangThai(idTK,trangThai);
    }

    @Override
    public List<HoaDon> getByIdTKAndTrangThai1(UUID idTK, Integer trangThai) {
        return hoaDonRepository.getByIdTKAndTrangThai1(idTK,trangThai);
    }

    @Override
    public Page<HoaDon> phanTrang(int page, int limit) {
        Pageable pageable = PageRequest.of(page,limit);
        return hoaDonRepository.findAll(pageable);
    }
}
