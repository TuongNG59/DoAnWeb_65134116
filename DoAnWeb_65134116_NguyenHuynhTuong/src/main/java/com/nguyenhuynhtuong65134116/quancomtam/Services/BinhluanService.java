package com.nguyenhuynhtuong65134116.quancomtam.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Binhluan;
import com.nguyenhuynhtuong65134116.quancomtam.Repositories.BinhluanRepository;

@Service
public class BinhluanService {

    @Autowired
    private BinhluanRepository binhluanRepository;

    // 1. Lấy tất cả bình luận của một món ăn
    public List<Binhluan> layBinhLuanCuaMonAn(Integer monanId) {
        return binhluanRepository.findByMonanIdOrderByIdDesc(monanId);
    }

    // 2. Lưu bình luận mới của khách hàng vào CSDL
    public Binhluan luuBinhLuan(Binhluan binhluan) {
        return binhluanRepository.save(binhluan);
    }
}
