package com.nguyenhuynhtuong65134116.quancomtam.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Taikhoan;
import com.nguyenhuynhtuong65134116.quancomtam.Repositories.TaikhoanRepository;

@Service
public class TaikhoanService {

    @Autowired
    private TaikhoanRepository taikhoanRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Tiêm bộ băm mật khẩu BCrypt từ SecurityConfig vào

    // Xử lý logic Đăng ký tài khoản mới (Có băm mật khẩu)
    public String dangKyTaiKhoan(Taikhoan taiKhoanMoi) {
        // 1. Kiểm tra xem email đã tồn tại trong hệ thống chưa
        Optional<Taikhoan> taiKhoanTonTai = taikhoanRepository.findByEmail(taiKhoanMoi.getEmail());
        if (taiKhoanTonTai.isPresent()) {
            return "Email này đã được sử dụng bởi một tài khoản khác!";
        }

        // 2. Tiến hành băm mật khẩu trước khi lưu vào database để bảo mật thông tin khách hàng
        String matKhauDaBam = passwordEncoder.encode(taiKhoanMoi.getMatkhau());
        taiKhoanMoi.setMatkhau(matKhauDaBam);

        // 3. Thiết lập mặc định cho tài khoản mới đăng ký
        taiKhoanMoi.setVaitro("USER"); // Mặc định là khách hàng mua cơm
        taiKhoanMoi.setTrangthai(1);   // Mặc định là hoạt động bình thường

        // 4. Lưu tài khoản xuống cơ sở dữ liệu
        taikhoanRepository.save(taiKhoanMoi);
        return "Đăng ký tài khoản thành công!";
    }

    // Xử lý logic Đăng nhập (Có đối chiếu mật khẩu đã băm)
    public Taikhoan dangNhap(String email, String matKhauRaw) {
        Optional<Taikhoan> taiKhoanOpt = taikhoanRepository.findByEmail(email);
        
        if (taiKhoanOpt.isPresent()) {
            Taikhoan taiKhoan = taiKhoanOpt.get();
            
            // Sử dụng hàm matches của passwordEncoder để kiểm tra xem mật khẩu người dùng gõ vào 
            // có khớp với chuỗi mật khẩu đã được băm mã hóa trong Database không
            if (passwordEncoder.matches(matKhauRaw, taiKhoan.getMatkhau())) {
                if (taiKhoan.getTrangthai() == 0) {
                    return null; // Tài khoản bị khóa không cho đăng nhập
                }
                return taiKhoan; // Đăng nhập thành công
            }
        }
        return null; // Đăng nhập thất bại
    }
}
