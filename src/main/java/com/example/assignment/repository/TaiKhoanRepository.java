package com.example.assignment.repository;

import com.example.assignment.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, UUID> {
    @Query("select p from TaiKhoan p where p.email = :email")
    TaiKhoan getByEmail(@Param("email")String email);

    @Query("select p from TaiKhoan p where p.tenDangNhap = :tenDangNhap")
    TaiKhoan getByTenDangNhap(@Param("tenDangNhap")String tenDangNhap);
}
