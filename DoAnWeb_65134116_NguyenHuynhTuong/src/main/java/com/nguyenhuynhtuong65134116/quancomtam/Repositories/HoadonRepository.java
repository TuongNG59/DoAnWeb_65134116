package com.nguyenhuynhtuong65134116.quancomtam.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Hoadon;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Taikhoan;

@Repository
public interface HoadonRepository extends JpaRepository<Hoadon, Integer> {
    // Lấy lịch sử mua hàng của một khách hàng cụ thể
    List<Hoadon> findByTaikhoanOrderByIdDesc(Taikhoan taikhoan);
}
