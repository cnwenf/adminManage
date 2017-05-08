package com.bupt.sse.adminManage.service;

import com.bupt.sse.adminManage.entity.DepartmentEntity;
import com.bupt.sse.adminManage.entity.ProjectEntity;
import com.bupt.sse.adminManage.entity.UserEntity;
import com.bupt.sse.adminManage.entity.common.ProjectStatus;

import java.util.Date;
import java.util.List;

/**
 * Created by WenFe on 2017/5/8.
 */
public class ProjectInfo {
    private String id;
    private String companyId;
    private String name;
    private Date startDate;
    private Date endDate;
    private ProjectStatus status;
    private String introduce;
    private String budget;
    private UserEntity owner;
    private List<UserEntity> persons;
    private DepartmentEntity department;

    public String getId() {
        return id;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public List<UserEntity> getPersons() {
        return persons;
    }

    public void setPersons(List<UserEntity> persons) {
        this.persons = persons;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setByProjectEntity(ProjectEntity projectEntity) {
        this.setName(projectEntity.getName());
        this.setId(projectEntity.getId());
        this.setCompanyId(projectEntity.getCompanyId());
        this.setIntroduce(projectEntity.getIntroduce());
        this.setBudget(projectEntity.getBudget());
        this.setStatus(projectEntity.getStatus());
        this.setStartDate(projectEntity.getStartDate());
        this.setEndDate(projectEntity.getEndDate());
    }
}
