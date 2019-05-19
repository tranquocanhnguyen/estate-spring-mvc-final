package com.tranquocanh.converter;

import com.tranquocanh.dto.BuildingDTO;
import com.tranquocanh.entity.BuildingEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    public BuildingDTO  convertToDTO(BuildingEntity buildingEntity) {
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity,BuildingDTO.class);
        return buildingDTO;
    }

    public BuildingEntity convertToEntity(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO,BuildingEntity.class);
        return buildingEntity;
    }
}
