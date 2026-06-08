package com.nguyenhuynhtuong65134116.quancomtam.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Danhmuc;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Hoadon;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Monan;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Taikhoan;
import com.nguyenhuynhtuong65134116.quancomtam.Services.AdminService;
import com.nguyenhuynhtuong65134116.quancomtam.Services.MonanService;

@Controller
@RequestMapping("/admin") // Toàn bộ các đường dẫn trong class này đều bắt đầu bằng /admin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private MonanService monanService;

    // 1. Giao diện trang Dashboard chính của Admin (Xem danh sách đơn hàng)
    @GetMapping("/dashboard")
    public String hienThiTrangQuanTri(Model model) {
        List<Hoadon> dsHoaDon = adminService.layTatCaHoaDon();
        model.addAttribute("hoadonList", dsHoaDon);
        return "admin/dashboard"; // Trỏ về file dashboard.html nằm trong thư mục templates/admin
    }

    // 2. Hành động xử lý chuyển trạng thái đơn hàng của khách
    @PostMapping("/don-hang/cap-nhat")
    public String capNhatDonHang(@RequestParam("hoadonid") Integer hoadonid, 
                                 @RequestParam("trangthai") String trangthai) {
        adminService.capNhatTrangThaiDonHang(hoadonid, trangthai);
        return "redirect:/admin/dashboard"; // Xử lý xong tải lại trang quản trị
    }

    // 3. Giao diện trang Thêm món ăn mới vào thực đơn
    @GetMapping("/mon-an/them")
    public String trangThemMonAn(Model model) {
        model.addAttribute("monanMoi", new Monan());
        model.addAttribute("danhmucList", monanService.layTatCaDanhMuc()); // Để hiển thị thẻ chọn <select> danh mục
        return "admin/them-mon-an"; // Trỏ về file them-mon-an.html trong thư mục templates/admin
    }

    // 4. Xử lý lưu dữ liệu Form thêm món ăn gửi lên
    @PostMapping("/mon-an/them")
    public String xuLyThemMonAn(@ModelAttribute("monanMoi") Monan monanMoi) {
        adminService.themMonAnMoi(monanMoi);
        return "redirect:/trang-chu"; // Thêm xong nhảy ra trang chủ xem món mới xuất hiện chưa liền
    }
    
    // 5. Giao diện trang Quản lý danh mục thể loại 
    @GetMapping("/danh-muc")
    public String quanLyDanhMuc(Model model) {
        model.addAttribute("danhmucList", monanService.layTatCaDanhMuc());
        model.addAttribute("danhmucMoi", new Danhmuc());
        return "admin/danh-muc";
    }
    
    // 6. Xử lý dữ liệu Form thêm danh mục mới gửi lên
    @PostMapping("/danh-muc/them")
    public String xuLyThemDanhMuc(@ModelAttribute("danhmucMoi") Danhmuc danhmucMoi) {
        adminService.themDanhMucMoi(danhmucMoi);
        return "redirect:/admin/danh-muc";
    }
    
    // 7. Giao diện trang Quản lý danh sách món ăn (Mục 4.9.2 trong báo cáo)
    @GetMapping("/mon-an")
    public String quanLyMonAn(Model model) {
        List<Monan> dsMonAn = adminService.layTatCaMonAn();
        model.addAttribute("monanList", dsMonAn);
        return "admin/danh-sach-mon-an"; // Trỏ về file danh-sach-mon-an.html trong thư mục templates/admin
    }
    
    // 8. Xử lý yêu cầu Xóa món ăn
    @GetMapping("/mon-an/xoa/{id}")
    public String xuLyXoaMonAn(@PathVariable("id") Integer id) {
        adminService.xoaMonAn(id);
        return "redirect:/admin/mon-an"; // Xóa xong tải lại trang quản lý thực đơn
    }
    
    // 9. Giao diện trang Quản lý tài khoản (Mục 4.9.3 trong báo cáo)
    @GetMapping("/tai-khoan")
    public String quanLyTaiKhoan(Model model) {
        List<Taikhoan> dsTaiKhoan = adminService.layTatCaTaiKhoan();
        model.addAttribute("taikhoanList", dsTaiKhoan);
        return "admin/tai-khoan";
    }
    
    // 10. Xử lý yêu cầu Thay đổi vai trò tài khoản
    @PostMapping("/tai-khoan/doi-vai-tro")
    public String xuLyDoiVaiTro(@RequestParam("id") Integer id, @RequestParam("vaitro") String vaitro) {
        adminService.doiVaiTroTaiKhoan(id, vaitro);
        return "redirect:/admin/tai-khoan"; // Xử lý xong tải lại trang quản lý thành viên
    }
}