package com.tranquocanh.repository.custom;

import com.tranquocanh.builder.BuildingBuilder;
import com.tranquocanh.entity.BuildingEntity;
import com.tranquocanh.paging.Pageble;

import java.util.List;

public interface BuidingRepositoryCustom {

    List<BuildingEntity> findAll(Pageble pageble, BuildingBuilder builder);
    int getToTalItem(BuildingBuilder builder);
}
