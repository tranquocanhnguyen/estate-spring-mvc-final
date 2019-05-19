package com.tranquocanh.converter;

import com.tranquocanh.dto.RoleDTO;
import com.tranquocanh.dto.UserDTO;
import com.tranquocanh.entity.RoleEntity;
import com.tranquocanh.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    @Autowired
    private ModelMapper modelMapper;

    public RoleDTO  convertToDTO(RoleEntity entity) {
        RoleDTO dto = modelMapper.map(entity,RoleDTO.class);
        return dto;
    }

    public RoleEntity convertToEntity(RoleDTO dto) {
        RoleEntity entity = modelMapper.map(dto,RoleEntity.class);
        return entity;
    }
}
