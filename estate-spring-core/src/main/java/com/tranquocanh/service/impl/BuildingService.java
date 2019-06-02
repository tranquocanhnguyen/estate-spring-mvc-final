package com.tranquocanh.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tranquocanh.builder.BuildingBuilder;
import com.tranquocanh.converter.BuildingConverter;
import com.tranquocanh.dto.BuildingDTO;
import com.tranquocanh.entity.BuildingEntity;
import com.tranquocanh.entity.UserEntity;
import com.tranquocanh.enums.BuildingType;
import com.tranquocanh.paging.Pageble;
import com.tranquocanh.repository.BuildingRepository;
import com.tranquocanh.repository.UserRepository;
import com.tranquocanh.service.IBuildingService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BuildingService implements IBuildingService {


    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<BuildingDTO> findAll(Pageble pageble, BuildingBuilder builder) {
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(pageble,builder);
        //java7
     /* List<BuildingDTO> buildingDTOS = new ArrayList<>();
        for (BuildingEntity item: buildingEntities) {
            buildingDTOS.add(buildingConverter.convertToDTO(item));
        }*/
        //java8
        return buildingEntities.stream().map(item -> buildingConverter.convertToDTO(item)).collect(Collectors.toList());
    }

    @Override
    public BuildingDTO save(BuildingDTO dto) {
        dto.setType(Arrays.stream(dto.getBuildingTypes()).collect(Collectors.joining(",")));
        BuildingEntity entity = buildingConverter.convertToEntity(dto);
        return buildingConverter.convertToDTO(buildingRepository.save(entity));
    }

    @Override
    public int getToTalItem(BuildingBuilder builder) {
        return buildingRepository.getToTalItem(builder);
    }

    @Override
    public BuildingDTO findById(Long id) {
        return buildingConverter.convertToDTO(buildingRepository.findById(id));
    }

    @Override
    public Map<String, String> getBuildingType() {
        Map<String,String> result = new HashMap<>();
        Stream.of(BuildingType.values()).forEach(item -> result.put(item.name(),item.getValue()));
        return result;
    }

    @Override
    public void deleteById(Long[] ids) {
        for (Long id: ids) {
            buildingRepository.delete(id);
        }
    }

    @Override
    public BuildingDTO update(BuildingDTO model) {
        BuildingEntity oldEntity = buildingRepository.findById(model.getId());
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(model);
        buildingEntity.setCreatedDate(oldEntity.getCreatedDate());
        buildingEntity.setCreatedBy(oldEntity.getCreatedBy());
        return buildingConverter.convertToDTO(buildingRepository.save(buildingEntity));
    }

    @Override
    public void assignBuilding(BuildingDTO model) {
        BuildingEntity entity = buildingRepository.findById(model.getId());
        List<UserEntity> userEntities = new ArrayList<>();
        Arrays.stream(model.getUserId()).forEach(id -> userEntities.add(userRepository.findOne(id)));
        entity.setStaffs(userEntities);
        buildingRepository.save(entity);
    }

    @Override
    public List<BuildingDTO> findBuidingAssigned(Pageble pageble, BuildingBuilder buildingBuilder) {
        List<BuildingEntity> entities = buildingRepository.findBuildingAssigned(pageble,buildingBuilder);
        return entities.stream().map(item -> buildingConverter.convertToDTO(item)).collect(Collectors.toList());
    }
}
