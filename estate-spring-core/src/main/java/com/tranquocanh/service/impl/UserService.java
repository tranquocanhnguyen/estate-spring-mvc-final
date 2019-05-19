package com.tranquocanh.service.impl;

import com.tranquocanh.builder.UserBuilder;
import com.tranquocanh.constant.SystemConstant;
import com.tranquocanh.converter.UserConverter;
import com.tranquocanh.dto.UserDTO;
import com.tranquocanh.entity.BuildingEntity;
import com.tranquocanh.entity.RoleEntity;
import com.tranquocanh.entity.UserEntity;
import com.tranquocanh.repository.BuildingRepository;
import com.tranquocanh.repository.RoleRepository;
import com.tranquocanh.repository.UserRepository;
import com.tranquocanh.repository.custom.UserRepositoryCustom;
import com.tranquocanh.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public List<UserDTO> findAllByStatus(Integer status, UserBuilder builder) {
        List<UserEntity> entites = userRepository.findAllByStatus(status,builder);
        return entites.stream().map(item -> userConverter.convertToDTO(item)).collect(Collectors.toList());
    }

    @Override
    public UserDTO save(UserDTO model) {
        List<RoleEntity> roleEntities = new ArrayList<>();
        //java 7
        /*for(String code: model.getTypeArray()) {
            roleEntities.add(roleRepository.findByCode(code));
        }*/
        //java 8
        Arrays.stream(model.getTypeArray()).forEach(code -> roleEntities.add(roleRepository.findByCode(code)));
        UserEntity entity = userConverter.convertToEntity(model);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setStatus(SystemConstant.ACTIVE);
        entity.setRoles(roleEntities);
        return userConverter.convertToDTO(userRepository.save(entity));
    }

    @Override
    public UserDTO findOne(Long id) {
        return userConverter.convertToDTO(userRepository.findOne(id));
    }

    @Override
    public Map<String, String> getRoles() {
        List<RoleEntity> entities = roleRepository.findAll();
        Map<String, String> roles = new HashMap<>();
        entities.forEach(item -> roles.put(item.getCode(), item.getName()));
        return roles;
    }

    @Override
    public void delete(Long[] ids) {
        List<UserEntity> entities = new ArrayList<>();
        Arrays.stream(ids).forEach(id -> entities.add(userRepository.findOne(id)));
        entities.forEach(entity -> entity.setStatus(SystemConstant.INACTIVE));
        userRepository.save(entities);
    }

    @Override
    public UserDTO update(UserDTO model) {
        List<RoleEntity> roleEntities = new ArrayList<>();
        Arrays.stream(model.getTypeArray()).forEach(code -> roleEntities.add(roleRepository.findByCode(code)));
        UserEntity oldUser = userRepository.findOne(model.getId());
        UserEntity newUser = userConverter.convertToEntity(model);
        newUser.setRoles(roleEntities);
        newUser.setStatus(SystemConstant.ACTIVE);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setCreatedBy(oldUser.getCreatedBy());
        newUser.setCreatedDate(oldUser.getCreatedDate());
        return userConverter.convertToDTO(userRepository.save(newUser));
    }

    @Override
    public UserDTO getEmployee(Long buildingId ) {
        List<UserEntity> entities = userRepository.findAllByRole();
        UserDTO userDTO = new UserDTO();
        userDTO.setListResult(entities.stream().map(entity -> userConverter.convertToDTO(entity)).collect(Collectors.toList()));
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId);
        userDTO.setManagers(buildingEntity.getStaffs().stream().map(item -> userConverter.convertToDTO(item)).collect(Collectors.toList()));
        return userDTO;
    }


}
