package com.example.assignment.repository;

import com.example.assignment.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SanPhamRepository extends JpaRepository<SanPham, UUID> {
    @Query("select sp from SanPham sp where sp.ma = :ma")
    SanPham getByMa(@Param("ma")String ma);

    @Query("select sp from SanPham  sp where sp.dongSP.id = :idDSP")
    Page<SanPham> getAllByIdDsp(@Param("idDSP")UUID idDSP, Pageable pageable);
}
