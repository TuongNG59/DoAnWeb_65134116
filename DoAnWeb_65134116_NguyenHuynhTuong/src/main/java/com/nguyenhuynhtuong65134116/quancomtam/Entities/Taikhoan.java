package com.nguyenhuynhtuong65134116.quancomtam.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "taikhoan")
public class Taikhoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String hoten;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String matkhau;

    @Column(length = 50)
    private String vaitro = "USER";

    private Integer trangthai = 1;

    @Column(name = "ngaytao", updatable = false)
    private LocalDateTime ngaytao = LocalDateTime.now();

    // Constructor không tham số (Bắt buộc phải có trong JPA)
    public Taikhoan() {
    }

    // Constructor đầy đủ tham số
    public Taikhoan(Integer id, String hoten, String email, String matkhau, String vaitro, Integer trangthai, LocalDateTime ngaytao) {
        this.id = id;
        this.hoten = hoten;
        this.email = email;
        this.matkhau = matkhau;
        this.vaitro = vaitro;
        this.trangthai = trangthai;
        this.ngaytao = ngaytao;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public String getVaitro() {
		return vaitro;
	}

	public void setVaitro(String vaitro) {
		this.vaitro = vaitro;
	}

	public Integer getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(Integer trangthai) {
		this.trangthai = trangthai;
	}

	public LocalDateTime getNgaytao() {
		return ngaytao;
	}

	public void setNgaytao(LocalDateTime ngaytao) {
		this.ngaytao = ngaytao;
	}
    
    
}
