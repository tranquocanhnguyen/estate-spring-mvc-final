package com.tranquocanh.repository;

import com.tranquocanh.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    RoleEntity findByCode(String code);
}
