package com.tranquocanh.entity;

import javax.persistence.*;

@Entity
@Table(name = "history_management")
public class HistoryManagementEntity extends BaseEntity{

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private CustomerManageEntity customerManageEntity;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CustomerManageEntity getCustomerManageEntity() {
        return customerManageEntity;
    }

    public void setCustomerManageEntity(CustomerManageEntity customerManageEntity) {
        this.customerManageEntity = customerManageEntity;
    }
}
