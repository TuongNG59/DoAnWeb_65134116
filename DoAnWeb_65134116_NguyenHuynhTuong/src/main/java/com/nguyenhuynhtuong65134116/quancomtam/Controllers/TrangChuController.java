package com.nguyenhuynhtuong65134116.quancomtam.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Danhmuc;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Monan;
import com.nguyenhuynhtuong65134116.quancomtam.Services.MonanService;

@Controller
public class TrangChuController {

    @Autowired
    private MonanService monanService;

    // Hiển thị trang chủ kèm danh sách món ăn và thanh tìm kiếm
    @GetMapping({"/", "/trang-chu"})
    public String hienThiTrangChu(
            @RequestParam(value = "search", required = false) String search,
            Model model) {
        
        // 1. Lấy danh sách các danh mục để hiển thị lên menu bộ lọc
        List<Danhmuc> danhSachDanhMuc = monanService.layTatCaDanhMuc();
        model.addAttribute("danhmucList", danhSachDanhMuc);

        // 2. Xử lý logic tìm kiếm hoặc hiển thị mặc định
        List<Monan> danhSachMonAn;
        if (search != null && !search.trim().isEmpty()) {
            danhSachMonAn = monanService.timKiemMonAn(search);
            model.addAttribute("tuKhoaTimKiem", search); // Gửi lại từ khóa ra ô input để khách thấy
        } else {
            danhSachMonAn = monanService.layMonAnDangBan();
        }
        
        // 3. Đẩy danh sách món ăn ra giao diện Thymeleaf
        model.addAttribute("monanList", danhSachMonAn);
        
        return "trangchu"; // Sẽ khớp với file trangchu.html
    }
    
    // Hiển thị trang chi tiết món ăn
    @GetMapping("/mon-an/{id}")
    public String hienThiChiTietMonAn(@PathVariable("id") Integer id, Model model) {
        Monan monan = monanService.layMonAnTheoId(id);
        if (monan == null) {
            return "redirect:/trang-chu"; // Nếu không tìm thấy món thì đá về trang chủ
        }
        model.addAttribute("monan", monan);
        return "chitietmonan"; // Sẽ khớp với file chitietmonan.html ở Bước 3
    }
}
