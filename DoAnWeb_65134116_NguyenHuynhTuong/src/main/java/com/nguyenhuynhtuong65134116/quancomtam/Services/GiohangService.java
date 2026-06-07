package com.nguyenhuynhtuong65134116.quancomtam.Services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Chitiethoadon;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Giohang;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Hoadon;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Monan;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Taikhoan;
import com.nguyenhuynhtuong65134116.quancomtam.Repositories.ChitiethoadonRepository;
import com.nguyenhuynhtuong65134116.quancomtam.Repositories.GiohangRepository;
import com.nguyenhuynhtuong65134116.quancomtam.Repositories.HoadonRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional // Đảm bảo tính toàn vẹn dữ liệu khi đặt hàng (Nếu lỗi một dòng chi tiết, toàn bộ hóa đơn sẽ rollback)
public class GiohangService {

    @Autowired
    private GiohangRepository giohangRepository;

    @Autowired
    private HoadonRepository hoadonRepository;

    @Autowired
    private ChitiethoadonRepository chitiethoadonRepository;

    // 1. Lấy danh sách giỏ hàng của một người dùng
    public List<Giohang> layGioHangCuaUser(Taikhoan taikhoan) {
        return giohangRepository.findByTaikhoan(taikhoan);
    }

    // 2. Thêm món ăn vào giỏ hàng (Xử lý thông minh: Cộng dồn số lượng nếu trùng món)
    public void themMonVaoGio(Taikhoan taikhoan, Monan monan, Integer soluong, String ghichu) {
        Optional<Giohang> gioHangOpt = giohangRepository.findByTaikhoanAndMonan(taikhoan, monan);
        
        if (gioHangOpt.isPresent()) {
            // Nếu đã có món này trong giỏ, tiến hành cộng dồn số lượng
            Giohang giohangTonTai = gioHangOpt.get();
            giohangTonTai.setSoluong(giohangTonTai.getSoluong() + soluong);
            if (ghichu != null && !ghichu.trim().isEmpty()) {
                giohangTonTai.setGhichu(ghichu); // Cập nhật ghi chú topping mới nếu có
            }
            giohangRepository.save(giohangTonTai);
        } else {
            // Nếu món ăn mới tinh, tạo mới một dòng trong giỏ hàng
            Giohang giohangMoi = new Giohang(null, taikhoan, monan, soluong, ghichu);
            giohangRepository.save(giohangMoi);
        }
    }

    // 3. Xóa một món ra khỏi giỏ hàng
    public void xoaMonKhoiGio(Integer giohangId) {
        giohangRepository.deleteById(giohangId);
    }

    // 4. LOGIC CHỐT ĐƠN: Chuyển toàn bộ giỏ hàng thành Hóa đơn đặt cơm
    public Hoadon datHangThanhToan(Taikhoan taikhoan, String sodienthoai, String diachigiaohang, String ghichuDonHang) {
        // Bước A: Lấy toàn bộ món trong giỏ hàng hiện tại của khách
        List<Giohang> dsGioHang = giohangRepository.findByTaikhoan(taikhoan);
        if (dsGioHang.isEmpty()) {
            return null; // Giỏ hàng trống không làm gì cả
        }

        // Bước B: Tính toán tổng tiền của đơn hàng cơm tấm
        BigDecimal tongTien = BigDecimal.ZERO;
        for (Giohang item : dsGioHang) {
            BigDecimal thanhTienCuaMon = item.getMonan().getDongia().multiply(new BigDecimal(item.getSoluong()));
            tongTien = tongTien.add(thanhTienCuaMon);
        }

        // Bước C: Tạo mới 1 dòng Hóa đơn (Hoadon) lưu thông tin giao hàng
        Hoadon hoaDonMoi = new Hoadon();
        hoaDonMoi.setTaikhoan(taikhoan);
        hoaDonMoi.setTongtien(tongTien);
        hoaDonMoi.setSodienthoai(sodienthoai);
        hoaDonMoi.setDiachigiaohang(diachigiaohang);
        hoaDonMoi.setGhichu(ghichuDonHang);
        hoaDonMoi.setTrangthai("Chờ xác nhận"); // Trạng thái mặc định khi vừa bấm đặt
        
        Hoadon hoaDonDaLuu = hoadonRepository.save(hoaDonMoi);

        // Bước D: Đổ chi tiết từng món từ giỏ hàng sang bảng Chi tiết hóa đơn (Chitiethoadon)
        for (Giohang item : dsGioHang) {
            Chitiethoadon chiTiet = new Chitiethoadon();
            chiTiet.setHoadon(hoaDonDaLuu);
            chiTiet.setMonan(item.getMonan());
            chiTiet.setSoluong(item.getSoluong());
            chiTiet.setDongiaban(item.getMonan().getDongia()); // Ghi nhận giá bán tại thời điểm đặt cơm
            chiTiet.setGhichutopping(item.getGhichu());
            
            chitiethoadonRepository.save(chiTiet);
        }

        // Bước E: Sau khi chốt đơn thành công, dọn sạch giỏ hàng của khách đó
        giohangRepository.deleteByTaikhoan(taikhoan);

        return hoaDonDaLuu;
    }
}
