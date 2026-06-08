package com.nguyenhuynhtuong65134116.quancomtam.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Danhmuc;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Monan;
import com.nguyenhuynhtuong65134116.quancomtam.Repositories.DanhmucRepository;
import com.nguyenhuynhtuong65134116.quancomtam.Repositories.MonanRepository;

@Service
public class MonanService {

    @Autowired
    private MonanRepository monanRepository;

    @Autowired
    private DanhmucRepository danhmucRepository;

    // 1. Lấy tất cả danh mục thực đơn (để hiển thị lên thanh menu lọc)
    public List<Danhmuc> layTatCaDanhMuc() {
        return danhmucRepository.findAll();
    }

    // 2. Lấy tất cả món ăn đang được mở bán (trangthaiban = 1) hiển thị lên trang chủ
    public List<Monan> layMonAnDangBan() {
        return monanRepository.findByTrangthaiban(1);
    }

    // 3. Tìm kiếm món ăn theo tên (không phân biệt chữ hoa, chữ thường)
    public List<Monan> timKiemMonAn(String tuKhoa) {
        if (tuKhoa != null && !tuKhoa.trim().isEmpty()) {
            return monanRepository.findByTenmonContainingIgnoreCase(tuKhoa);
        }
        return layMonAnDangBan(); // Nếu từ khóa trống thì trả về tất cả món đang bán
    }
    
    // 4. Tìm kiếm chi tiết một món ăn theo ID
    public Monan layMonAnTheoId(Integer id) {
        return monanRepository.findById(id).orElse(null);
    }
}
