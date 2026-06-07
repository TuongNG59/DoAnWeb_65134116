package com.nguyenhuynhtuong65134116.quancomtam.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Chitiethoadon;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Hoadon;

@Repository
public interface ChitiethoadonRepository extends JpaRepository<Chitiethoadon, Integer> {
    // Lấy danh sách các món ăn thuộc về một hóa đơn cụ thể (để hiển thị khi xem chi tiết đơn hàng)
    List<Chitiethoadon> findByHoadon(Hoadon hoadon);
}
