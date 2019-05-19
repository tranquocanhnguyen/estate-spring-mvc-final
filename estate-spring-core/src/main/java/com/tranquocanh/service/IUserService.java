package com.tranquocanh.service;

import com.tranquocanh.builder.UserBuilder;
import com.tranquocanh.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IUserService {
    List<UserDTO> findAllByStatus(Integer status, UserBuilder builder);
    UserDTO save(UserDTO model);
    UserDTO findOne(Long id);
    Map<String,String> getRoles();
    void delete(Long[] ids);
    UserDTO update(UserDTO model);
    UserDTO getEmployee(Long BuildingId);
}
