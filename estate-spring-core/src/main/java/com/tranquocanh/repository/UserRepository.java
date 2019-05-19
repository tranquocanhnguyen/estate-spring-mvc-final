package com.tranquocanh.repository;

import com.tranquocanh.builder.UserBuilder;
import com.tranquocanh.entity.UserEntity;
import com.tranquocanh.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Long>, UserRepositoryCustom {

    UserEntity findOneByUserNameAndStatus(String userName, Integer status);

    List<UserEntity> findAll();
}
