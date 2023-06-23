package com.example.assignment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "taikhoan")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class TaiKhoan {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "Id", columnDefinition = "uniqueidentifier default newid()")
    private UUID id;

    @Column(name = "tendangnhap")
    private String tenDangNhap;

    @Column(name = "matkhau")
    private String matKhau;

    @Column(name = "hoten")
    private String hoTen;

    @Column(name = "email")
    private String email;

    @Column(name = "anh")
    private String anh;

    @Column(name = "trangthai")
    private Integer trangThai;

    @Column(name = "admins")
    private Integer admin;

    @OneToMany(mappedBy = "taiKhoan")
    List<HoaDon> hoaDon;


}
