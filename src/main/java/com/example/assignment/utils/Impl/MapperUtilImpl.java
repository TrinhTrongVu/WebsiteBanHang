package com.example.assignment.utils.Impl;

import com.example.assignment.dto.SanPhamDTO;
import com.example.assignment.entity.SanPham;
import com.example.assignment.utils.MapperUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


public class MapperUtilImpl implements MapperUtils<SanPham> {

    @Autowired
    private static ModelMapper mapper;

    @Override
    public SanPham convertToEntity(SanPhamDTO sanPhamDTO) {
        SanPham entity = mapper.map(sanPhamDTO,SanPham.class);
        return entity;
    }

    @Override
    public SanPhamDTO convertToDTO(SanPham sanPham) {
        SanPhamDTO sanPhamDTO = mapper.map(sanPham,SanPhamDTO.class);
        return sanPhamDTO;
    }


}
