package com.nguyenhuynhtuong65134116.quancomtam.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Danhmuc;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Hoadon;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Monan;
import com.nguyenhuynhtuong65134116.quancomtam.Repositories.DanhmucRepository;
import com.nguyenhuynhtuong65134116.quancomtam.Repositories.HoadonRepository;
import com.nguyenhuynhtuong65134116.quancomtam.Repositories.MonanRepository;

@Service
public class AdminService {

    @Autowired
    private HoadonRepository hoadonRepository;

    @Autowired
    private MonanRepository monanRepository;

    @Autowired
    private DanhmucRepository danhmucRepository;
    
    // 1. Lấy tất cả hóa đơn của quán ăn (Mới nhất xếp lên đầu)
    public List<Hoadon> layTatCaHoaDon() {
        return hoadonRepository.findAll();
    }

    // 2. Cập nhật trạng thái đơn hàng (Ví dụ: Chờ xác nhận -> Đang chuẩn bị cơm -> Đã giao)
    public boolean capNhatTrangThaiDonHang(Integer hoadonId, String trangThaiMoi) {
        Optional<Hoadon> hoadonOpt = hoadonRepository.findById(hoadonId);
        if (hoadonOpt.isPresent()) {
            Hoadon hoadon = hoadonOpt.get();
            hoadon.setTrangthai(trangThaiMoi);
            hoadonRepository.save(hoadon);
            return true;
        }
        return false;
    }

    // 3. Thêm một món ăn mới vào thực đơn quán cơm tấm Anh High
    public Monan themMonAnMoi(Monan monanMoi) {
        return monanRepository.save(monanMoi);
    }
    
    // 4. Thêm một danh mục thể loại mới vào hệ thống
    public Danhmuc themDanhMucMoi(Danhmuc danhmucMoi) {
        return danhmucRepository.save(danhmucMoi);
    }
    
    // 5. Lấy tất cả món ăn hiện có trong cơ sở dữ liệu (Phục vụ trang quản lý thực đơn)
    public List<Monan> layTatCaMonAn() {
        return monanRepository.findAll();
    }
    
    // 6. Xóa một món ăn khỏi thực đơn dựa vào ID
    public void xoaMonAn(Integer id) {
        monanRepository.deleteById(id);
    }
}