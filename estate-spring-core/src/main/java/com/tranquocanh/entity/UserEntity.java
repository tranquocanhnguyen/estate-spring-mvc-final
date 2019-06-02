package com.tranquocanh.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(unique = true)
    private String userName;

    @Column
    private String password;

    @Column
    private String fullName;

    @Column
    private Integer status;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",joinColumns = {
            @JoinColumn(name = "user_id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "role_id",nullable = false)})
    private List<RoleEntity> roles =new ArrayList<>();

    @OneToMany(mappedBy = "userEntity",fetch = FetchType.LAZY)
    private List<CustomerManageEntity> customerManageEntities;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public List<CustomerManageEntity> getCustomerManageEntities() {
        return customerManageEntities;
    }

    public void setCustomerManageEntities(List<CustomerManageEntity> customerManageEntities) {
        this.customerManageEntities = customerManageEntities;
    }
}
