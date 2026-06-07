package com.nguyenhuynhtuong65134116.quancomtam.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "danhmuc")
public class Danhmuc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String tendanhmuc;

    // Constructor không tham số
    public Danhmuc() {
    }

    // Constructor đầy đủ tham số
    public Danhmuc(Integer id, String tendanhmuc) {
        this.id = id;
        this.tendanhmuc = tendanhmuc;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTendanhmuc() {
		return tendanhmuc;
	}

	public void setTendanhmuc(String tendanhmuc) {
		this.tendanhmuc = tendanhmuc;
	}
    
}
