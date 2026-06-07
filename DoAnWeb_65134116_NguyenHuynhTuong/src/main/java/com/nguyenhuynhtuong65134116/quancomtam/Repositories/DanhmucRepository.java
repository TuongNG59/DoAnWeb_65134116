package com.nguyenhuynhtuong65134116.quancomtam.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nguyenhuynhtuong65134116.quancomtam.Entities.Danhmuc;

@Repository
public interface DanhmucRepository extends JpaRepository<Danhmuc, Integer> {
}
