package com.nguyenhuynhtuong65134116.quancomtam.Configs;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 1. Tìm đường dẫn tuyệt đối tới thư mục chứa ảnh trong đồ án của cậu
        Path hinhAnhhPath = Paths.get("src/main/resources/static/images/");
        String duongDanTuyetDoi = hinhAnhhPath.toFile().getAbsolutePath();

        // 2. Cấu hình đường dẫn ảo: Khi gọi /images/** sẽ trỏ thẳng vào thư mục vật lý ngoài ổ cứng
        // Lưu ý: Cần thêm tiền tố "file:/" cho hệ điều hành Windows đọc đúng ổ đĩa
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/" + duongDanTuyetDoi + "/");
    }
}