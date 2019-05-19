package com.tranquocanh.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AbstractDTO<T> implements Serializable {

    private static final long serialVersionUID = -4396911805210533643L;

    private Long id;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private List<T> listResult = new ArrayList<>();
    private Integer page = 1;
    private Integer maxPageItem = 2;
    private int totalPage = 0;
    private Integer totalItem = 0;
    private String sortBy;
    private String sortName;
    private Long checkList[];
    private String message;
    private String alert;
    private String[] typeArray;

    public String getCrudaction() {
        return crudaction;
    }

    public void setCrudaction(String crudaction) {
        this.crudaction = crudaction;
    }

    private String crudaction;

//    public String[] getCheckList() {
//        return checkList;
//    }
//
//    public void setCheckList(String[] checkList) {
//        this.checkList = checkList;
//    }
//
//    private String[] checkList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public List<T> getListResult() {
        return listResult;
    }

    public void setListResult(List<T> listResult) {
        this.listResult = listResult;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getMaxPageItem() {
        return maxPageItem;
    }

    public void setMaxPageItem(Integer maxPageItem) {
        this.maxPageItem = maxPageItem;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public int getTotalPage() {
        //return totalPage;
        return (int)Math.ceil((double) this.totalItem / this.maxPageItem);

    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public Long[] getCheckList() {
        return checkList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public void setCheckList(Long[] checkList) {
        this.checkList = checkList;
    }

    public String[] getTypeArray() {
        return typeArray;
    }

    public void setTypeArray(String[] typeArray) {
        this.typeArray = typeArray;
    }
}
