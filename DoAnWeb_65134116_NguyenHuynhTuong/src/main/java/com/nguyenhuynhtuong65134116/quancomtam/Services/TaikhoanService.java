package com.nguyenhuynhtuong65134116.quancomtam.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Taikhoan;
import com.nguyenhuynhtuong65134116.quancomtam.Repositories.TaikhoanRepository;

@Service
public class TaikhoanService {

    @Autowired
    private TaikhoanRepository taikhoanRepository;

    // Xử lý logic Đăng ký tài khoản mới
    public String dangKyTaiKhoan(Taikhoan taiKhoanMoi) {
        // 1. Kiểm tra xem email đã tồn tại trong hệ thống chưa
        Optional<Taikhoan> taiKhoanTonTai = taikhoanRepository.findByEmail(taiKhoanMoi.getEmail());
        if (taiKhoanTonTai.isPresent()) {
            return "Email này đã được sử dụng bởi một tài khoản khác!";
        }

        // 2. Thiết lập mặc định cho tài khoản mới đăng ký
        taiKhoanMoi.setVaitro("USER"); // Mặc định là khách hàng mua cơm
        taiKhoanMoi.setTrangthai(1);   // Mặc định là hoạt động bình thường

        // 3. Lưu tài khoản xuống cơ sở dữ liệu
        taikhoanRepository.save(taiKhoanMoi);
        return "Đăng ký tài khoản thành công!";
    }

    // Xử lý logic Đăng nhập
    public Taikhoan dangNhap(String email, String matKhau) {
        Optional<Taikhoan> taiKhoanOpt = taikhoanRepository.findByEmail(email);
        
        // Nếu tìm thấy email và mật khẩu khớp khít (tạm thời kiểm tra text thuần, bước sau ta sẽ dùng Spring Security băm sau)
        if (taiKhoanOpt.isPresent() && taiKhoanOpt.get().getMatkhau().equals(matKhau)) {
            if (taiKhoanOpt.get().getTrangthai() == 0) {
                return null; // Tài khoản bị khóa không cho đăng nhập
            }
            return taiKhoanOpt.get(); // Đăng nhập thành công, trả về thông tin tài khoản
        }
        return null; // Đăng nhập thất bại
    }
}
