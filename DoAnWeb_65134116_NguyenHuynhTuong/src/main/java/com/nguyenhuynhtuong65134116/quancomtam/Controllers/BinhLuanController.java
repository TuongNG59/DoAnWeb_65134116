package com.nguyenhuynhtuong65134116.quancomtam.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Binhluan;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Monan;
import com.nguyenhuynhtuong65134116.quancomtam.Entities.Taikhoan;
import com.nguyenhuynhtuong65134116.quancomtam.Services.BinhluanService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/binh-luan")
public class BinhLuanController {

    @Autowired
    private BinhluanService binhluanService;

    // Xử lý khi khách bấm nút "Gửi bình luận"
    @PostMapping("/gui")
    public String guiBinhLuan(@RequestParam("monanid") Monan monan,
                              @RequestParam("noidung") String noidung,
                              HttpSession session) {
        
        // Kiểm tra xem người dùng đã đăng nhập chưa, nếu chưa thì bắt đi đăng nhập
        Taikhoan userLogged = (Taikhoan) session.getAttribute("USER_LOGGED");
        if (userLogged == null) {
            return "redirect:/dang-nhap";
        }

        // Tạo đối tượng bình luận mới và lưu xuống DB
        Binhluan bl = new Binhluan();
        bl.setTaikhoan(userLogged);
        bl.setMonan(monan);
        bl.setNoidung(noidung);
        
        binhluanService.luuBinhLuan(bl);

        // Bình luận xong quay trở lại đúng trang chi tiết của món ăn đó để xem
        return "redirect:/mon-an/" + monan.getId();
    }
}
