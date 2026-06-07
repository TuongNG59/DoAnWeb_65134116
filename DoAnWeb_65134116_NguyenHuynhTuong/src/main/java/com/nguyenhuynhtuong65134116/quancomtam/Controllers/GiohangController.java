package com.nguyenhuynhtuong65134116.quancomtam.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Giohang;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Hoadon;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Monan;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Taikhoan;
import com.nguyenhuynhtuong65134116.quancomtam.Services.GiohangService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/gio-hang")
public class GiohangController {

    @Autowired
    private GiohangService giohangService;

    // 1. Xem chi tiết giỏ hàng hiện tại
    @GetMapping
    public String xemGioHang(HttpSession session, Model model) {
        // Lấy thông tin người dùng đang đăng nhập từ Session hệ thống
        Taikhoan userLogged = (Taikhoan) session.getAttribute("USER_LOGGED");
        if (userLogged == null) {
            return "redirect:/dang-nhap"; // Chưa đăng nhập bắt buộc đi đăng nhập
        }

        List<Giohang> dsGioHang = giohangService.layGioHangCuaUser(userLogged);
        model.addAttribute("giohangList", dsGioHang);
        
        return "giohang"; // Trả về file giohang.html ở Chương 6
    }

    // 2. Thêm món ăn vào giỏ cơm
    @PostMapping("/them")
    public String themMonVaoGioHang(
            @RequestParam("monanid") Monan monan,
            @RequestParam("soluong") Integer soluong,
            @RequestParam(value = "ghichu", required = false) String ghichu,
            HttpSession session) {
        
        Taikhoan userLogged = (Taikhoan) session.getAttribute("USER_LOGGED");
        if (userLogged == null) {
            return "redirect:/dang-nhap";
        }

        giohangService.themMonVaoGio(userLogged, monan, soluong, ghichu);
        return "redirect:/gio-hang"; // Thêm xong nhảy thẳng vào trang xem giỏ hàng
    }

    // 3. Xóa một món ăn khỏi giỏ
    @GetMapping("/xoa/{id}")
    public String xoaKhoiGioHang(@PathVariable("id") Integer id) {
        giohangService.xoaMonKhoiGio(id);
        return "redirect:/gio-hang";
    }

    // 4. Thực hiện bấm nút đặt hàng chốt đơn cơm tấm
    @PostMapping("/thanh-toan")
    public String thanhToanDonHang(
            @RequestParam("sodienthoai") String sodienthoai,
            @RequestParam("diachigiaohang") String diachigiaohang,
            @RequestParam(value = "ghichu", required = false) String ghichu,
            HttpSession session, Model model) {
        
        Taikhoan userLogged = (Taikhoan) session.getAttribute("USER_LOGGED");
        if (userLogged == null) {
            return "redirect:/dang-nhap";
        }

        Hoadon hoadonResult = giohangService.datHangThanhToan(userLogged, sodienthoai, diachigiaohang, ghichu);
        if (hoadonResult != null) {
            model.addAttribute("hoadonThanhCong", hoadonResult);
            return "dat-hang-thanh-cong"; // Chuyển tới giao diện chúc mừng đặt cơm thành công
        }
        
        return "redirect:/trang-chu";
    }
}
