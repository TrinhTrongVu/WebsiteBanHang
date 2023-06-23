package com.example.assignment.utils;

import com.example.assignment.dto.SanPhamDTO;

public interface MapperUtils<T> {
    T convertToEntity(SanPhamDTO t);
    SanPhamDTO convertToDTO(T t);
}
