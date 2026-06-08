package com.nguyenhuynhtuong65134116.quancomtam.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Taikhoan;
import com.nguyenhuynhtuong65134116.quancomtam.Repositories.TaikhoanRepository;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalAppAdvice {

    @Autowired
    private TaikhoanRepository taikhoanRepository;

    // Hàm này sẽ tự động chạy ngầm ở mọi trang để đồng bộ Spring Security với Session
    @ModelAttribute
    public void dongBoSession(HttpSession session, Principal principal) {
        // Nếu Spring Security báo là đã đăng nhập (principal != null) nhưng Session lại trống rỗng
        if (principal != null && session.getAttribute("USER_LOGGED") == null) {
            // Lấy email từ Spring Security chui xuống DB tìm tài khoản
            Taikhoan tk = taikhoanRepository.findByEmail(principal.getName()).orElse(null);
            if (tk != null) {
                // Nhét tài khoản vào Session
                session.setAttribute("USER_LOGGED", tk);
            }
        }
    }
}
