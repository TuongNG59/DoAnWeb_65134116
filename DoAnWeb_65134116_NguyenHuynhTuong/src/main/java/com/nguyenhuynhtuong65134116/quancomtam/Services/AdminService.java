package com.nguyenhuynhtuong65134116.quancomtam.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Danhmuc;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Hoadon;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Monan;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Taikhoan;
import com.nguyenhuynhtuong65134116.quancomtam.Repositories.DanhmucRepository;
import com.nguyenhuynhtuong65134116.quancomtam.Repositories.HoadonRepository;
import com.nguyenhuynhtuong65134116.quancomtam.Repositories.MonanRepository;
import com.nguyenhuynhtuong65134116.quancomtam.Repositories.TaikhoanRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminService {

    @Autowired
    private HoadonRepository hoadonRepository;

    @Autowired
    private MonanRepository monanRepository;

    @Autowired
    private DanhmucRepository danhmucRepository;
    
    @Autowired
    private TaikhoanRepository taikhoanRepository;
    
    @Transactional
    
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
    
    // 7. Lấy danh sách tất cả tài khoản trong hệ thống (Đã sửa ngắn gọn nhờ import)
    public List<Taikhoan> layTatCaTaiKhoan() {
        return taikhoanRepository.findAll();
    }
    
    // 8. Đổi vai trò của thành viên (Đã sửa ngắn gọn nhờ import)
    public void doiVaiTroTaiKhoan(Integer id, String vaiTroMoi) {
        Optional<Taikhoan> tkOpt = taikhoanRepository.findById(id);
        if (tkOpt.isPresent()) {
            Taikhoan tk = tkOpt.get();
            tk.setVaitro(vaiTroMoi);
            taikhoanRepository.save(tk);
        }
    }
    
    //Xử lý xóa danh mục an toàn
    public void xoaDanhMucAnToan(Integer danhMucId) {
        // Bước A: Tìm và xóa tất cả các món ăn thuộc danh mục này trước
        // (Cậu kiểm tra xem MonanRepository của cậu có hàm findByDanhmucId hoặc tương tự không)
        // Nếu chưa có, ta có thể dùng ra lệnh xóa dựa vào Id danh mục:
        List<Monan> dsMonAnThuocDanhMuc = monanRepository.findAll();
        for (Monan monan : dsMonAnThuocDanhMuc) {
            if (monan.getDanhmuc() != null && monan.getDanhmuc().getId().equals(danhMucId)) {
                monanRepository.delete(monan);
            }
        }
        
        // Bước B: Sau khi các món ăn "con" đã sạch sẽ, tiến hành xóa danh mục "cha"
        danhmucRepository.deleteById(danhMucId);
    }
}