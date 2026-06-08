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
@Table(name = "chitiethoadon")
public class Chitiethoadon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "hoadonid", nullable = false)
    private Hoadon hoadon;

    @ManyToOne
    @JoinColumn(name = "monanid", nullable = false)
    private Monan monan;

    @Column(nullable = false)
    private Integer soluong;

    @Column(name = "dongiaban", nullable = false, precision = 10, scale = 2)
    private BigDecimal dongiaban;

    private String ghichutopping;

    // Constructor không tham số
    public Chitiethoadon() {
    }

    // Constructor đầy đủ tham số
    public Chitiethoadon(Integer id, Hoadon hoadon, Monan monan, Integer soluong, BigDecimal dongiaban, String ghichutopping) {
        this.id = id;
        this.hoadon = hoadon;
        this.monan = monan;
        this.soluong = soluong;
        this.dongiaban = dongiaban;
        this.ghichutopping = ghichutopping;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Hoadon getHoadon() {
		return hoadon;
	}

	public void setHoadon(Hoadon hoadon) {
		this.hoadon = hoadon;
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

	public BigDecimal getDongiaban() {
		return dongiaban;
	}

	public void setDongiaban(BigDecimal dongiaban) {
		this.dongiaban = dongiaban;
	}

	public String getGhichutopping() {
		return ghichutopping;
	}

	public void setGhichutopping(String ghichutopping) {
		this.ghichutopping = ghichutopping;
	}
    
}