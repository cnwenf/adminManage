package com.bupt.sse.adminManage.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by WenFe on 2017/5/11.
 */
@Entity(name="materialInfo")
public class MaterialEntity {
    @Id
    private String id;
    private String name;
    private String companyId;
    private String spUserName;
    private String spUserId;
    private String exUserName;
    private String exUserId;
    private String projectName;
    private String projectId;
    private String num;
    private String time;
    private String spInfo;
    private String exInfo;
    private String status;

    public String getId() {
        return id;
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getSpUserName() {
        return spUserName;
    }

    public void setSpUserName(String spUserName) {
        this.spUserName = spUserName;
    }

    public String getSpUserId() {
        return spUserId;
    }

    public void setSpUserId(String spUserId) {
        this.spUserId = spUserId;
    }

    public String getExUserName() {
        return exUserName;
    }

    public void setExUserName(String exUserName) {
        this.exUserName = exUserName;
    }

    public String getExUserId() {
        return exUserId;
    }

    public void setExUserId(String exUserId) {
        this.exUserId = exUserId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSpInfo() {
        return spInfo;
    }

    public void setSpInfo(String spInfo) {
        this.spInfo = spInfo;
    }

    public String getExInfo() {
        return exInfo;
    }

    public void setExInfo(String exInfo) {
        this.exInfo = exInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
