package com.example.assignment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sanpham")
public class SanPham {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "Id", columnDefinition = "uniqueidentifier default newid()")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "iddongsp" , referencedColumnName = "id")
    private DongSP dongSP;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "Anh")
    private String anh;

    @Column(name = "Gia")
    private BigDecimal gia;

    @Column(name = "ngaytao")
    private Date ngayTao;

    @Column(name = "trangthai")
    private Integer trangThai;

    @OneToMany(mappedBy = "sanPham")
    List<HoaDonChiTiet> hoaDonChiTiet;


}

