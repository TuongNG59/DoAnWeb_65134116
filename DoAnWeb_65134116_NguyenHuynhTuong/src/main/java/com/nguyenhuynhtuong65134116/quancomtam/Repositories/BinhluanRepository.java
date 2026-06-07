package com.nguyenhuynhtuong65134116.quancomtam.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Binhluan;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Monan;

@Repository
public interface BinhluanRepository extends JpaRepository<Binhluan, Integer> {
    // Lấy tất cả các bình luận của một món ăn cụ thể để hiển thị lên trang chi tiết món ăn
    List<Binhluan> findByMonanOrderByIdDesc(Monan monan);
}
