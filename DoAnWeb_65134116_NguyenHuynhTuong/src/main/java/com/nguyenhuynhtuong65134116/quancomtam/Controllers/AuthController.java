package com.nguyenhuynhtuong65134116.quancomtam.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Taikhoan;
import com.nguyenhuynhtuong65134116.quancomtam.Services.TaikhoanService;

@Controller
public class AuthController {

    @Autowired
    private TaikhoanService taikhoanService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    // 1. Hiển thị trang Đăng ký
    @GetMapping("/dang-ky")
    public String hienThiTrangDangKy(Model model) {
        model.addAttribute("taikhoan", new Taikhoan());
        return "dangky"; // Trả về file dangky.html
    }

    // 2. Xử lý dữ liệu gửi lên từ Form Đăng ký
    @PostMapping("/dang-ky")
    public String xuLyDangKy(@ModelAttribute("taikhoan") Taikhoan taikhoan, Model model) {
        
        String ketQua = taikhoanService.dangKyTaiKhoan(taikhoan);
        
        if (ketQua.equals("Đăng ký tài khoản thành công!")) {
            model.addAttribute("thongBaoThanhCong", ketQua);
            return "dangnhap"; // Đăng ký xong chuyển hướng sang trang đăng nhập
        } else {
            model.addAttribute("thongBaoLoi", ketQua);
            return "dangky"; // Lỗi thì ở lại trang đăng ký
        }
    }

    // 3. Hiển thị trang Đăng nhập (Chỉ trả về giao diện HTML)
    @GetMapping("/dang-nhap")
    public String hienThiTrangDangNhap() {
        return "dangnhap"; // Trả về file dangnhap.html trong thư mục templates
    }
}