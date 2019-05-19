package com.tranquocanh.service.impl;

import com.tranquocanh.converter.RoleConverter;
import com.tranquocanh.dto.RoleDTO;
import com.tranquocanh.entity.RoleEntity;
import com.tranquocanh.repository.RoleRepository;
import com.tranquocanh.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleConverter roleConverter;
    @Override
    public List<RoleDTO> findAll() {
        List<RoleEntity> entities = roleRepository.findAll();
        return entities.stream().map(item -> roleConverter.convertToDTO(item)).collect(Collectors.toList());
    }
}
