package com.tranquocanh.converter;

import com.tranquocanh.dto.UserDTO;
import com.tranquocanh.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter  {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertToDTO(UserEntity entity) {
        UserDTO dto = modelMapper.map(entity,UserDTO.class);
        return dto;
    }

    public UserEntity convertToEntity(UserDTO dto) {
        UserEntity entity = modelMapper.map(dto, UserEntity.class);
        return  entity;
    }
}
