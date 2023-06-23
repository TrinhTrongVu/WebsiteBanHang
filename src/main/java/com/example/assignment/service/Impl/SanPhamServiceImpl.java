package com.example.assignment.service.Impl;

import com.example.assignment.entity.SanPham;
import com.example.assignment.repository.SanPhamRepository;
import com.example.assignment.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SanPhamServiceImpl implements SanPhamService {
    @Autowired
    private SanPhamRepository sanPhamRepository;
    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.findAll();
    }

    @Override
    public List<SanPham> getAllBySoLuong() {
        return null;
    }

    @Override
    public Page<SanPham> getAllByIdDsp(UUID idDsp,int page, int limit) {
        Pageable pageable = PageRequest.of(page,limit);
        return sanPhamRepository.getAllByIdDsp(idDsp,pageable);
    }

    @Override
    public Page<SanPham> phanTrangAndSort(int page, int limit, Sort sort) {
        Pageable pageable = PageRequest.of(page,limit,sort);
        return  sanPhamRepository.findAll(pageable);
    }

    @Override
    public Page<SanPham> phanTrang(int page, int limit) {
        Pageable pageable = PageRequest.of(page,limit);
        return sanPhamRepository.findAll(pageable);
    }

    @Override
    public SanPham getOne(UUID id) {
        return sanPhamRepository.getReferenceById(id);
    }

    @Override
    public SanPham getByMa(String ma) {
        return sanPhamRepository.getByMa(ma);
    }

    @Override
    public void add(SanPham sp) {
        sanPhamRepository.save(sp);
    }

    @Override
    public void update(SanPham sp) {
        sanPhamRepository.save(sp);
    }

    @Override
    public void delete(UUID id) {
        sanPhamRepository.deleteById(id);
    }
}
