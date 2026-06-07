package com.nguyenhuynhtuong65134116.quancomtam.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "giohang")
public class Giohang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "taikhoanid", nullable = false)
    private Taikhoan taikhoan;

    @ManyToOne
    @JoinColumn(name = "monanid", nullable = false)
    private Monan monan;

    @Column(nullable = false)
    private Integer soluong;

    private String ghichu;

    // Constructor không tham số
    public Giohang() {
    }

    // Constructor đầy đủ tham số
    public Giohang(Integer id, Taikhoan taikhoan, Monan monan, Integer soluong, String ghichu) {
        this.id = id;
        this.taikhoan = taikhoan;
        this.monan = monan;
        this.soluong = soluong;
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

	public Monan getMonan() {
		return monan;
	}

	public void setMonan(Monan monan) {
		this.monan = monan;
	}

	public Integer getSoluong() {
		return soluong;
	}

	public void setSoluong(Integer soluong) {
		this.soluong = soluong;
	}

	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}
    
}
