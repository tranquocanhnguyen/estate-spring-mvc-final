package com.tranquocanh.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer_manage")
public class CustomerManageEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name="id", insertable = false, updatable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name="id", insertable = false, updatable = false)
    private CustomerEntity customerEntity;

    @OneToMany(mappedBy = "customerManageEntity",fetch = FetchType.LAZY)
    private List<HistoryManagementEntity> historyManagementEntities = new ArrayList<>();

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public List<HistoryManagementEntity> getHistoryManagementEntities() {
        return historyManagementEntities;
    }

    public void setHistoryManagementEntities(List<HistoryManagementEntity> historyManagementEntities) {
        this.historyManagementEntities = historyManagementEntities;
    }
}
