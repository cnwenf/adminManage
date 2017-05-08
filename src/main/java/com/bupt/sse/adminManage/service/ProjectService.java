package com.bupt.sse.adminManage.service;

import com.bupt.sse.adminManage.dao.iface.ProjectDao;
import com.bupt.sse.adminManage.dao.iface.ProjectMetadataDao;
import com.bupt.sse.adminManage.entity.ProjectEntity;
import com.bupt.sse.adminManage.entity.ProjectMetadataEntity;
import com.bupt.sse.adminManage.entity.UserEntity;
import com.bupt.sse.adminManage.entity.common.ProjectMetadataType;
import com.bupt.sse.adminManage.entity.common.ProjectStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by WenFe on 2017/5/8.
 */
@Service("projectService")
public class ProjectService {
    @Resource
    private ProjectDao projectDao;
    @Resource
    private ProjectMetadataService projectMetadataService;
    @Resource
    private UserService userService;
    @Resource
    private DepartmentService departmentService;

    public boolean create (ProjectInfo projectInfo) {
        String id = UUID.randomUUID().toString();
        projectMetadataService.create(id, ProjectMetadataType.owner, projectInfo.getOwner().getName(), projectInfo.getOwner().getDisplayName());
        //创建部门参与人metadata
        for (UserEntity person : projectInfo.getPersons()) {
            projectMetadataService.create(id, ProjectMetadataType.persons, person.getName(), person.getDisplayName());
        }
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(id);
        projectEntity.setCompanyId(projectInfo.getCompanyId());
        projectEntity.setName(projectInfo.getName());
        projectEntity.setStartDate(projectInfo.getStartDate());
        projectEntity.setEndDate(projectInfo.getEndDate());
        projectEntity.setStatus(projectInfo.getStatus());//
        projectEntity.setBudget(projectInfo.getBudget());
        projectEntity.setIntroduce(projectInfo.getIntroduce());
        return projectDao.create(projectEntity);
    }

    public ProjectInfo getProjectInfo(String companyId, String id) {
        ProjectInfo projectInfo = new ProjectInfo();
        ProjectEntity projectEntity = projectDao.getById(id);
        List<ProjectMetadataEntity> projectMetadataEntities = projectMetadataService.getByProjectId(id);
        List<UserEntity> persons = new ArrayList<UserEntity>();
        for (ProjectMetadataEntity projectMetadataEntity : projectMetadataEntities) {
            if (projectMetadataEntity.getProjectMetadataType().equals(ProjectMetadataType.persons)) {
                persons.add(userService.get(projectMetadataEntity.getDataId()));
            } else if (projectMetadataEntity.getProjectMetadataType().equals(ProjectMetadataType.owner)) {
                projectInfo.setOwner(userService.get(projectMetadataEntity.getDataId()));
            } else if (projectMetadataEntity.getProjectMetadataType().equals(ProjectMetadataType.department)) {
                projectInfo.setDepartment(departmentService.getById(companyId, projectMetadataEntity.getDataId()));
            }
        }
        projectInfo.setPersons(persons);
        projectInfo.setByProjectEntity(projectEntity);
        return projectInfo;
    }
}
