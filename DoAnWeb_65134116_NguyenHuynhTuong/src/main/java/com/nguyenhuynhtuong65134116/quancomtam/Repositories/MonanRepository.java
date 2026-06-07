package com.nguyenhuynhtuong65134116.quancomtam.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Monan;

@Repository
public interface MonanRepository extends JpaRepository<Monan, Integer> {
    // Tìm các món ăn theo trạng thái bán (ví dụ: đang bán trangthaiban = 1)
    List<Monan> findByTrangthaiban(Integer trangthaiban);
    
    // Tìm kiếm món ăn theo tên (phục vụ thanh tìm kiếm của khách hàng)
    List<Monan> findByTenmonContainingIgnoreCase(String tenmon);
}
