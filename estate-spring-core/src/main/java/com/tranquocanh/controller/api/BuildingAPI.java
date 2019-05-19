package com.tranquocanh.controller.api;

import com.tranquocanh.dto.BuildingDTO;
import com.tranquocanh.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {

    @Autowired
    private IBuildingService buildingService;

/*    @PostMapping("/api/building")
    @ResponseBody
    public  BuildingDTO saveOrUpdate(@RequestBody BuildingDTO buildingDTO) {
        BuildingDTO model = buildingService.save(buildingDTO);
       return model;
    }*/

    //add building
    @PostMapping
    public  ResponseEntity<BuildingDTO> save(@RequestBody BuildingDTO buildingDTO) {
        BuildingDTO model = buildingService.save(buildingDTO);
        return ResponseEntity.ok(model);
    }

    @PutMapping
    public ResponseEntity<BuildingDTO> update(@RequestBody BuildingDTO buildingDTO) {
        BuildingDTO model = buildingService.update(buildingDTO);
        return ResponseEntity.ok(model);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBuilding(@RequestBody Long[] ids) {
        if (ids.length > 0) {
            buildingService.deleteById(ids);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/assign")
    public ResponseEntity<Void> assign(@RequestBody BuildingDTO model){
        buildingService.assignBuilding(model);
        return ResponseEntity.noContent().build();
    }
}
