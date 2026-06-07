package com.nguyenhuynhtuong65134116.quancomtam.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Taikhoan;

@Repository
public interface TaikhoanRepository extends JpaRepository<Taikhoan, Integer> {
    // Hàm tìm kiếm tài khoản bằng email (phục vụ chức năng Đăng nhập / Đăng ký)
    Optional<Taikhoan> findByEmail(String email);
}
