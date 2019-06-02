package com.tranquocanh.controller;

import com.tranquocanh.builder.UserBuilder;
import com.tranquocanh.constant.SystemConstant;
import com.tranquocanh.dto.UserDTO;
import com.tranquocanh.service.IRoleService;
import com.tranquocanh.service.IUserService;
import com.tranquocanh.service.impl.UserService;
import com.tranquocanh.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @GetMapping("/list")
    public ModelAndView showUser(@ModelAttribute(SystemConstant.MODEL) UserDTO model) {


        ModelAndView mav = new ModelAndView("user/list");
        UserBuilder builder = new UserBuilder.Builder()
                .setUserName(model.getUserName())
                .setFullName(model.getFullName())
                .setCode(model.getTypeArray())
                .build();

        model.setListResult(userService.findAllByStatus(SystemConstant.ACTIVE,builder));
        mav.addObject("rall",userService.getRoles());
        mav.addObject(SystemConstant.MODEL,model);
        return mav;
    }

    @GetMapping("/edit")
    public ModelAndView editForm(@RequestParam(value = "id" ,required = false) Long id) {
        ModelAndView mav = new ModelAndView("user/edit");
        if(id == null) {
            mav.addObject(SystemConstant.MODEL,new UserDTO());
        } else {
            mav.addObject(SystemConstant.MODEL, userService.findOne(id));
        }
        mav.addObject("roles",userService.getRoles());
        return mav;
    }
}
