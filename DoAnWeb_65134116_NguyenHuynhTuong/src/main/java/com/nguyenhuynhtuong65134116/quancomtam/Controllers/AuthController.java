package com.nguyenhuynhtuong65134116.quancomtam.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Taikhoan;
import com.nguyenhuynhtuong65134116.quancomtam.Services.TaikhoanService;

@Controller
public class AuthController {

    @Autowired
    private TaikhoanService taikhoanService;

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
            return "dangky"; // Lỗi (trùng email) thì ở lại trang đăng ký để nhập lại
        }
    }

    // 3. Hiển thị trang Đăng nhập
    @GetMapping("/dang-nhap")
    public String hienThiTrangDangNhap() {
        return "dangnhap"; // Trả về file dangnhap.html 
    }

    // 4. Xử lý dữ liệu gửi lên từ Form Đăng nhập
    @PostMapping("/dang-nhap")
    public String xuLyDangNhap(@RequestParam("email") String email, 
                               @RequestParam("matkhau") String matkhau, 
                               Model model) {
        Taikhoan tkLogged = taikhoanService.dangNhap(email, matkhau);
        
        if (tkLogged != null) {
            // Kiểm tra vai trò để điều hướng về trang tương ứng
            if (tkLogged.getVaitro().equals("ADMIN")) {
                return "redirect:/admin/dashboard"; // Nếu là Admin thì vào trang quản trị
            }
            return "redirect:/trang-chu"; // Nếu là User thường thì ra trang chủ mua cơm
        } else {
            model.addAttribute("thongBaoLoi", "Email hoặc mật khẩu không chính xác, hoặc tài khoản đã bị khóa!");
            return "dangnhap"; // Đăng nhập lỗi thì quay lại trang đăng nhập
        }
    }
}