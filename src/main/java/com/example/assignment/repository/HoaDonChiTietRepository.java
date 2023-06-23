package com.example.assignment.repository;

import com.example.assignment.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, UUID> {
    @Query("select h from HoaDonChiTiet h where h.sanPham.id=:idSP and h.hoaDon.id=:idHD")
    HoaDonChiTiet getByIdSPAndIdHD(@Param("idSP")UUID idSP,@Param("idHD")UUID idHD);

    @Query("select h from HoaDonChiTiet h where h.hoaDon.id=:idHD")
    List<HoaDonChiTiet> getByIdHD(@Param("idHD")UUID idHD);

    @Query("select sum(h.soLuong*h.gia) from HoaDonChiTiet h where h.hoaDon.id=:idHD")
    BigDecimal tongTien( @Param("idHD")UUID idHD);
}
