package com.tranquocanh.service.impl;

import com.tranquocanh.constant.SystemConstant;
import com.tranquocanh.dto.MyUserDetail;
import com.tranquocanh.entity.RoleEntity;
import com.tranquocanh.entity.UserEntity;
import com.tranquocanh.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findOneByUserNameAndStatus(userName, SystemConstant.ACTIVE);
        if(userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(RoleEntity item: userEntity.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(item.getCode()));
        }
        MyUserDetail myUserDetail = new MyUserDetail(userEntity.getUserName(),userEntity.getPassword(),authorities);
        BeanUtils.copyProperties(userEntity,myUserDetail);
        return myUserDetail;
    }
}
