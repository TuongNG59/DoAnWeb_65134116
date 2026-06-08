package com.nguyenhuynhtuong65134116.quancomtam.Services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Taikhoan;
import com.nguyenhuynhtuong65134116.quancomtam.Repositories.TaikhoanRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TaikhoanRepository taikhoanRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Tìm tài khoản trong DB bằng Email khách gõ
    	Taikhoan taiKhoan = taikhoanRepository.findByEmail(email).orElse(null);
        
        if (taiKhoan == null) {
            throw new UsernameNotFoundException("Không tìm thấy tài khoản với email: " + email);
        }

        // Tạo quyền hạn chuẩn Spring Security: Thêm tiền tố ROLE_ vào trước vai trò (Ví dụ: ROLE_ADMIN, ROLE_USER)
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + taiKhoan.getVaitro());

        // Trả về đối tượng User hợp lệ cho Spring Security quản lý kèm theo Session đăng nhập
        return new User(
                taiKhoan.getEmail(),
                taiKhoan.getMatkhau(),
                Collections.singletonList(authority)
        );
    }
}