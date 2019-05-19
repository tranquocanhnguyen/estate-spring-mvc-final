package com.tranquocanh.repository.custom;

import com.tranquocanh.builder.UserBuilder;
import com.tranquocanh.entity.UserEntity;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserEntity> findAllByStatus(Integer status, UserBuilder builder);
    List<UserEntity> findAllByRole();
}
