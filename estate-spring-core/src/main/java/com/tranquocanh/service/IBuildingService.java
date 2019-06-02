package com.tranquocanh.service;

import com.tranquocanh.builder.BuildingBuilder;
import com.tranquocanh.dto.BuildingDTO;
import com.tranquocanh.paging.Pageble;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
    List<BuildingDTO > findAll(Pageble pageble, BuildingBuilder builder);
    BuildingDTO save(BuildingDTO dto);
    int getToTalItem(BuildingBuilder builder);
    BuildingDTO findById(Long id);
    Map<String, String> getBuildingType();
    void deleteById(Long[] ids);
    BuildingDTO update(BuildingDTO model);
    void assignBuilding(BuildingDTO model);
    List<BuildingDTO> findBuidingAssigned(Pageble pageble, BuildingBuilder buildingBuilder);
}
