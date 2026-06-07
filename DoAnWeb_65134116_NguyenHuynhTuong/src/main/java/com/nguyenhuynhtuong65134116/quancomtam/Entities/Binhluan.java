package com.nguyenhuynhtuong65134116.quancomtam.Entities;

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
@Table(name = "binhluan")
public class Binhluan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "taikhoanid", nullable = false)
    private Taikhoan taikhoan;

    @ManyToOne
    @JoinColumn(name = "monanid", nullable = false)
    private Monan monan;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String noidung;

    @Column(name = "ngaybinhluan", updatable = false)
    private LocalDateTime ngaybinhluan = LocalDateTime.now();

    // Constructor không tham số
    public Binhluan() {
    }

    // Constructor đầy đủ tham số
    public Binhluan(Integer id, Taikhoan taikhoan, Monan monan, String noidung, LocalDateTime ngaybinhluan) {
        this.id = id;
        this.taikhoan = taikhoan;
        this.monan = monan;
        this.noidung = noidung;
        this.ngaybinhluan = ngaybinhluan;
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

	public Monan getMonan() {
		return monan;
	}

	public void setMonan(Monan monan) {
		this.monan = monan;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	public LocalDateTime getNgaybinhluan() {
		return ngaybinhluan;
	}

	public void setNgaybinhluan(LocalDateTime ngaybinhluan) {
		this.ngaybinhluan = ngaybinhluan;
	}
    
}