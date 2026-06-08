package com.nguyenhuynhtuong65134116.quancomtam.Entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hoadon")
public class Hoadon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "taikhoanid", nullable = false)
    private Taikhoan taikhoan;

    @Column(name = "ngaydat", updatable = false)
    private LocalDateTime ngaydat = LocalDateTime.now();

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal tongtien;

    @Column(nullable = false, length = 50)
    private String trangthai = "Chờ xác nhận";

    @Column(name = "sdtnhan")
    private String sodienthoai;

    @Column(name = "diachinhan")
    private String diachigiaohang;

    private String ghichu;

    // Constructor không tham số
    public Hoadon() {
    }

    // Constructor đầy đủ tham số
    public Hoadon(Integer id, Taikhoan taikhoan, LocalDateTime ngaydat, BigDecimal tongtien, String trangthai, String sodienthoai, String diachigiaohang, String ghichu) {
        this.id = id;
        this.taikhoan = taikhoan;
        this.ngaydat = ngaydat;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
        this.sodienthoai = sodienthoai;
        this.diachigiaohang = diachigiaohang;
        this.ghichu = ghichu;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Taikhoan getTaikhoan() {
		return taikhoan;
	}

	public void setTaikhoan(Taikhoan taikhoan) {
		this.taikhoan = taikhoan;
	}

	public LocalDateTime getNgaydat() {
		return ngaydat;
	}

	public void setNgaydat(LocalDateTime ngaydat) {
		this.ngaydat = ngaydat;
	}

	public BigDecimal getTongtien() {
		return tongtien;
	}

	public void setTongtien(BigDecimal tongtien) {
		this.tongtien = tongtien;
	}

	public String getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	public String getSodienthoai() {
		return sodienthoai;
	}

	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}

	public String getDiachigiaohang() {
		return diachigiaohang;
	}

	public void setDiachigiaohang(String diachigiaohang) {
		this.diachigiaohang = diachigiaohang;
	}

	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}
    
}    