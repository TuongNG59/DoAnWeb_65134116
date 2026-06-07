package com.nguyenhuynhtuong65134116.quancomtam.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Giohang;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Monan;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Taikhoan;

@Repository
public interface GiohangRepository extends JpaRepository<Giohang, Integer> {
    // Lấy danh sách giỏ hàng của một tài khoản cụ thể
    List<Giohang> findByTaikhoan(Taikhoan taikhoan);
    
    // Tìm xem món ăn này đã có trong giỏ hàng của người dùng này chưa
    Optional<Giohang> findByTaikhoanAndMonan(Taikhoan taikhoan, Monan monan);
    
    // Xóa sạch giỏ hàng của khách sau khi họ đã bấm Đặt hàng thành công
    void deleteByTaikhoan(Taikhoan taikhoan);
}
