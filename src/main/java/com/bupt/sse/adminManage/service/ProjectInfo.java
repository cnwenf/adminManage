package com.bupt.sse.adminManage.service;

import com.bupt.sse.adminManage.dao.iface.DepartmentDao;
import com.bupt.sse.adminManage.dao.iface.UserDao;
import com.bupt.sse.adminManage.entity.DepartmentEntity;
import com.bupt.sse.adminManage.entity.ProjectEntity;
import com.bupt.sse.adminManage.entity.UserEntity;
import com.bupt.sse.adminManage.entity.common.ProjectStatus;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by WenFe on 2017/5/8.
 */
public class ProjectInfo {
    private String id;
    private String companyId;
    private String name;
    private String startDate;
    private String endDate;
    private ProjectStatus status;
    private String introduce;
    private String budget;
    private UserEntity owner;
    private List<UserEntity> persons;
    private DepartmentEntity department;

    public ProjectInfo() {}

    public ProjectInfo(String projectInfo, UserService userService, DepartmentService departmentService) {
        JSONObject jsonObject = new JSONObject(projectInfo);
        if (null != jsonObject.getString("id") && !jsonObject.getString("id").equals("")) {
            this.setId(jsonObject.getString("id"));
        } else {
            this.setId(UUID.randomUUID().toString());
        }
        this.setName(jsonObject.getString("name"));
        this.setCompanyId(jsonObject.getString("companyId"));
        this.setStartDate(jsonObject.getString("startDate"));
        this.setEndDate(jsonObject.getString("endDate"));
        if (jsonObject.getString("status").equals("run")) {
            this.setStatus(ProjectStatus.RUN);
        } else if (jsonObject.getString("status").equals("end")) {
            this.setStatus(ProjectStatus.END);
        } else if (jsonObject.getString("status").equals("delay")) {
            this.setStatus(ProjectStatus.DELAY);
        } else if (jsonObject.getString("status").equals("forcestop")) {
            this.setStatus(ProjectStatus.FORCESTOP);
        }

        this.setIntroduce(jsonObject.getString("introduce"));
        this.setBudget(jsonObject.getString("budget"));
        JSONObject jsonOwner = jsonObject.getJSONObject("owner");
        UserEntity owner = userService.get(jsonOwner.getString("name"));
        this.setOwner(owner);

        DepartmentEntity departmentEntity = departmentService.getById(jsonObject.getString("companyId")
                , jsonObject.getJSONObject("department").getString("id"));
        this.setDepartment(departmentEntity);

        JSONArray personsArray = jsonObject.getJSONArray("persons");
        List<UserEntity> persons = new ArrayList<UserEntity>();
        for (int i = 0; i < personsArray.length(); i++) {
            JSONObject p  = personsArray.getJSONObject(i);
            UserEntity u = userService.get(p.getString("name"));
            persons.add(u);
        }
        this.setPersons(persons);
    }

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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
