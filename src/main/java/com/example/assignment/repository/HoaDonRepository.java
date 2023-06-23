package com.example.assignment.repository;

import com.example.assignment.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {
    @Query("select h from HoaDon h where h.taiKhoan.id = :idTK and h.trangThai = :trangThai")
    HoaDon getByIdTKAndTrangThai(@Param("idTK")UUID idTK,@Param("trangThai")Integer trangThai);

    @Query("select h from HoaDon h where h.taiKhoan.id = :idTK and h.trangThai = :trangThai")
    List<HoaDon> getByIdTKAndTrangThai1(@Param("idTK")UUID idTK, @Param("trangThai")Integer trangThai);
}
