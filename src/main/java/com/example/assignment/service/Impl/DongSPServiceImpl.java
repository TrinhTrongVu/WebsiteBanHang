package com.example.assignment.service.Impl;

import com.example.assignment.entity.DongSP;
import com.example.assignment.repository.DongSPRepository;
import com.example.assignment.service.DongSPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DongSPServiceImpl implements DongSPService {
    @Autowired
    private DongSPRepository dongSPRepository;

    @Override
    public void add(DongSP dongSP) {
        dongSPRepository.save(dongSP);
    }

    @Override
    public List<DongSP> getAll() {
        List<DongSP> listDongsp = dongSPRepository.findAll();
        return listDongsp;
    }

    @Override
    public DongSP getOne(UUID id) {
        DongSP dongSP = dongSPRepository.getReferenceById(id);
        return dongSP;
    }

    @Override
    public void update(DongSP dongSP) {
        dongSPRepository.save(dongSP);
    }

    @Override
    public void delete(UUID id) {
        dongSPRepository.deleteById(id);
    }

    @Override
    public Page<DongSP> phanTrang(int page, int limit) {
        Pageable pageable = PageRequest.of(page,limit);
        return  dongSPRepository.findAll(pageable);
    }
}
