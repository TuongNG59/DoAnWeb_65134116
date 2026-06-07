package com.nguyenhuynhtuong65134116.quancomtam.Entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "monan")
public class Monan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String tenmon;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal dongia;

    private String hinhanh;

    @Column(columnDefinition = "TEXT")
    private String mota;

    @Column(nullable = false)
    private Integer trangthaiban = 1;

    // Thiết lập khóa ngoại liên kết tới bảng danhmuc
    @ManyToOne
    @JoinColumn(name = "danhmucid", nullable = false)
    private Danhmuc danhmuc;

	public Monan() {
	}

	public Monan(Integer id, String tenmon, BigDecimal dongia, String hinhanh, String mota, Integer trangthaiban,
			Danhmuc danhmuc) {
		super();
		this.id = id;
		this.tenmon = tenmon;
		this.dongia = dongia;
		this.hinhanh = hinhanh;
		this.mota = mota;
		this.trangthaiban = trangthaiban;
		this.danhmuc = danhmuc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenmon() {
		return tenmon;
	}

	public void setTenmon(String tenmon) {
		this.tenmon = tenmon;
	}

	public BigDecimal getDongia() {
		return dongia;
	}

	public void setDongia(BigDecimal dongia) {
		this.dongia = dongia;
	}

	public String getHinhanh() {
		return hinhanh;
	}

	public void setHinhanh(String hinhanh) {
		this.hinhanh = hinhanh;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public Integer getTrangthaiban() {
		return trangthaiban;
	}

	public void setTrangthaiban(Integer trangthaiban) {
		this.trangthaiban = trangthaiban;
	}

	public Danhmuc getDanhmuc() {
		return danhmuc;
	}

	public void setDanhmuc(Danhmuc danhmuc) {
		this.danhmuc = danhmuc;
	}
    
}