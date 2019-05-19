package com.tranquocanh.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tranquocanh.builder.BuildingBuilder;
import com.tranquocanh.constant.SystemConstant;
import com.tranquocanh.dto.BuildingDTO;
import com.tranquocanh.paging.PageRequest;
import com.tranquocanh.paging.Pageble;
import com.tranquocanh.service.IBuildingService;
import com.tranquocanh.sort.Sorter;
import com.tranquocanh.util.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class BuildingController {

    @Autowired
    IBuildingService buildingService;

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/admin/building/list")
    public ModelAndView showList(@ModelAttribute(SystemConstant.MODEL) BuildingDTO model,
                                 HttpServletRequest request) {
        ResourceBundle bundle = ResourceBundle.getBundle("message");
         ModelAndView mav = new ModelAndView("building/list");
         Pageble pageble = new PageRequest(model.getPage(),model.getMaxPageItem(),new Sorter(model.getSortName(),model.getSortBy()));
        BuildingBuilder buildingBuilder = new BuildingBuilder.Builder()
                .setName(model.getName())
                .setStreet(model.getStreet())
                .setNumberOfBasement(model.getNumberOfBasement())
                .setType(model.getBuildingTypes())
                .build();
         model.setListResult(buildingService.findAll(pageble,buildingBuilder)) ;
         model.setTotalItem(buildingService.getToTalItem(buildingBuilder));
         mav.addObject(SystemConstant.MODEL,model);
         //add buidling type
        mav.addObject("buildingTypes",buildingService.getBuildingType());
        //check message
        if(StringUtils.isNotBlank(model.getMessage())) {
            MessageUtil.of(bundle.getString(model.getMessage()),model.getAlert()).buildMessage(request);
        }
         return mav;
    }

    @GetMapping("/admin/building/edit")
    public ModelAndView editForm(@ModelAttribute(SystemConstant.MODEL) BuildingDTO model ,
                                 @RequestParam(value = "id" ,required = false) Long id,
                                 HttpServletRequest request) {
        ResourceBundle bundle =  ResourceBundle.getBundle("message");
        ModelAndView mav = new ModelAndView("building/edit");
        if(id == null) {
            mav.addObject(SystemConstant.MODEL,new BuildingDTO());
        } else {
            mav.addObject(SystemConstant.MODEL,buildingService.findById(id));
        }
        //check message
        if (StringUtils.isNotBlank(model.getMessage())) {
            MessageUtil.of(bundle.getString(model.getMessage()),model.getAlert()).buildMessage(request);
        }
        mav.addObject("typeArray",buildingService.getBuildingType());
        return mav;
    }
}
