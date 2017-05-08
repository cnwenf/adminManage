package com.bupt.sse.adminManage.entity;

import com.bupt.sse.adminManage.entity.common.ProjectMetadataType;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by WenFe on 2017/5/8.
 */
@Entity(name="projectMetadata")
public class ProjectMetadataEntity {
    @Id
    private String id;
    private String projectId;
    private ProjectMetadataType projectMetadataType;
    private String dataId;
    private String dataName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public ProjectMetadataType getProjectMetadataType() {
        return projectMetadataType;
    }

    public void setProjectMetadataType(ProjectMetadataType projectMetadataType) {
        this.projectMetadataType = projectMetadataType;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }
}
