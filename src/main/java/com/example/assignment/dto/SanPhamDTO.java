package com.example.assignment.dto;

import com.example.assignment.entity.DongSP;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SanPhamDTO {

    @Null
    private String id;

    private DongSP dongSP;

    @NotEmpty(message = "Vui lòng nhập thông tin")
    @NotBlank(message = "Vui lòng nhập thông tin")
    @Pattern(regexp = "[a-z A-Z]+",message = "Vui lòng không nhập số")
    private String ten;

    private MultipartFile anh;

    @NotEmpty(message = "Vui lòng nhập thông tin")
    @NotBlank(message = "Vui lòng nhập thông tin")
    @Pattern(regexp = "[0-9]+",message = "Vui lòng không nhập chữ")
    @Min(value = 0,message = "Vui lòng nhập số > 0")
    @Max(value = 100000000,message = "Vui lòng nhập số < 100.000.000")
    private BigDecimal gia;

    @NotEmpty(message = "Vui lòng chọn ngày tháng")
    @NotBlank(message = "Vui lòng chọn ngày tháng")
    @Pattern(regexp = "\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}",message = "Vui lòng nhập đúng định dạng")
    private Date ngayTao;

    private Boolean trangThai;

}
