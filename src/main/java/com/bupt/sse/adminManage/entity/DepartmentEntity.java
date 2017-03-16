package com.bupt.sse.adminManage.entity;

import com.bupt.sse.adminManage.entity.common.BasePK;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by WenFeng on 2017/3/15.
 */
@Entity(name="departmentInfo")
@IdClass(BasePK.class)
public class DepartmentEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private String parentId;
    private String ownerId;
    @Id
    private String companyId;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getParentId() {
        return parentId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
