package com.tranquocanh.repository;

import com.tranquocanh.dto.BuildingDTO;
import com.tranquocanh.entity.BuildingEntity;
import com.tranquocanh.repository.custom.BuidingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> , BuidingRepositoryCustom {
    BuildingEntity findById(Long id);
}
