package com.bupt.sse.adminManage.service;

import com.bupt.sse.adminManage.dao.iface.ProjectMetadataDao;
import com.bupt.sse.adminManage.entity.ProjectMetadataEntity;
import com.bupt.sse.adminManage.entity.common.ProjectMetadataType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by WenFe on 2017/5/8.
 */
@Service("projectMetadataService")
public class ProjectMetadataService {
    @Resource
    private ProjectMetadataDao projectMetadataDao;

    public boolean create(String projectId, ProjectMetadataType type, String dataId, String dataName) {
        String id = UUID.randomUUID().toString();
        ProjectMetadataEntity projectMetadataEntity = new ProjectMetadataEntity();
        projectMetadataEntity.setId(id);
        projectMetadataEntity.setProjectId(projectId);
        projectMetadataEntity.setProjectMetadataType(type);
        projectMetadataEntity.setDataId(dataId);
        projectMetadataEntity.setDataName(dataName);
        return projectMetadataDao.create(projectMetadataEntity);
    }

    public List<ProjectMetadataEntity> getByProjectId(String projectId) {
        List<ProjectMetadataEntity> projectMetadataEntities = projectMetadataDao.list();
        List<ProjectMetadataEntity> result = new ArrayList<ProjectMetadataEntity>();
        for (ProjectMetadataEntity projectMetadataEntity : projectMetadataEntities) {
            if (projectMetadataEntity.getProjectId().equals(projectId)) {
                result.add(projectMetadataEntity);
            }
        }
        return result;
    }

    public void delete(String id) {
        projectMetadataDao.deleteById(id);
    }
}
