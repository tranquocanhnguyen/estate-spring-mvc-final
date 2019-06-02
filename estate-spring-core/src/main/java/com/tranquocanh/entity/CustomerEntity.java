package com.tranquocanh.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity{


    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private Integer gender;

    @Column
    private String phone;

    @Column
    private String address;

    @Column(columnDefinition = "text")
    private String note;

    @Column
    private Integer status;

    @OneToMany(mappedBy = "customerEntity",fetch = FetchType.LAZY)
    private List<CustomerManageEntity> customerManageEntities = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<CustomerManageEntity> getCustomerManageEntities() {
        return customerManageEntities;
    }

    public void setCustomerManageEntities(List<CustomerManageEntity> customerManageEntities) {
        this.customerManageEntities = customerManageEntities;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
