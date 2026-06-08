package com.nguyenhuynhtuong65134116.quancomtam.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Bean băm mật khẩu theo chuẩn BCrypt mạnh mẽ (Để dùng khi đăng ký/đăng nhập)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Cấu hình bộ lọc phân quyền các đường dẫn URL (Chuẩn Spring Boot 4.x Lambda)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Tắt CSRF tạm thời để dễ dàng làm việc với Form HTML thuần không dính lỗi bảo mật token
            .csrf(csrf -> csrf.disable())
            
            // 2. Cấu hình phân quyền truy cập URL
            .authorizeHttpRequests(auth -> auth
                // Cho phép tất cả mọi người truy cập vào tài nguyên tĩnh và trang đăng ký/đăng nhập
                .requestMatchers("/css/**", "/js/**", "/images/**", "/dang-ky", "/dang-nhap", "/trang-chu", "/").permitAll()
                
                // Thay .hasAuthority("ADMIN") bằng .hasRole("ADMIN") để khớp chuẩn mã hóa Role của Spring
                .requestMatchers("/admin/**").hasRole("ADMIN")
                
                // Tất cả các yêu cầu khác phải đăng nhập mới dùng được
                .anyRequest().authenticated()
            )
            
            // 3. Cấu hình trang đăng nhập tùy chỉnh theo ý mình (Form Login)
            .formLogin(form -> form
                .loginPage("/dang-nhap") // Trỏ về url hiển thị trang đăng nhập của mình
                .loginProcessingUrl("/dang-nhap") // Url xử lý dữ liệu form gửi lên
                .usernameParameter("email") // Dùng trường email thay cho username mặc định
                .passwordParameter("matkhau")
                .defaultSuccessUrl("/trang-chu", true) // Đăng nhập thành công thì nhảy ra trang chủ
                .permitAll()
            )
            
            // 4. Cấu hình đăng xuất
            .logout(logout -> logout
                .logoutUrl("/dang-xuat")
                .logoutSuccessUrl("/dang-nhap?logout") // Đăng xuất xong đá về trang đăng nhập
                .permitAll()
            );

        return http.build();
    }
}