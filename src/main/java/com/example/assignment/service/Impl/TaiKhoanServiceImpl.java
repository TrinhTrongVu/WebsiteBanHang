package com.example.assignment.service.Impl;

import com.example.assignment.entity.TaiKhoan;
import com.example.assignment.repository.TaiKhoanRepository;
import com.example.assignment.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Override
    public List<TaiKhoan> getAll() {
        return taiKhoanRepository.findAll();
    }

    @Override
    public TaiKhoan getOne(UUID id) {
        return taiKhoanRepository.getReferenceById(id);
    }

    @Override
    public void add(TaiKhoan taiKhoan) {
        taiKhoanRepository.save(taiKhoan);
    }

    @Override
    public void update(TaiKhoan taiKhoan) {
        taiKhoanRepository.save(taiKhoan);
    }

    @Override
    public void delete(UUID id) {
        taiKhoanRepository.deleteById(id);
    }

    @Override
    public TaiKhoan getByEmail(String email) {
        return taiKhoanRepository.getByEmail(email);
    }

    @Override
    public TaiKhoan getByTenDangNhap(String tenDangNhap) {
        return taiKhoanRepository.getByTenDangNhap(tenDangNhap);
    }

    @Override
    public Page<TaiKhoan> phanTrang(int page, int limit) {
        Pageable pageable = PageRequest.of(page,limit);
        return taiKhoanRepository.findAll(pageable);
    }
}
