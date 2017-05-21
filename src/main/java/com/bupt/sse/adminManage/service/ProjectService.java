package com.bupt.sse.adminManage.service;

import com.bupt.sse.adminManage.dao.iface.ProjectDao;
import com.bupt.sse.adminManage.dao.iface.ProjectMetadataDao;
import com.bupt.sse.adminManage.entity.DepartmentEntity;
import com.bupt.sse.adminManage.entity.ProjectEntity;
import com.bupt.sse.adminManage.entity.ProjectMetadataEntity;
import com.bupt.sse.adminManage.entity.UserEntity;
import com.bupt.sse.adminManage.entity.common.ProjectMetadataType;
import com.bupt.sse.adminManage.entity.common.ProjectStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Resource
    private MeetingService meetingService;
    @Resource
    private MaterialService materialService;

    public boolean create (ProjectInfo projectInfo) {
        String id = projectInfo.getId();
        projectMetadataService.create(id, ProjectMetadataType.owner, projectInfo.getOwner().getName(), projectInfo.getOwner().getDisplayName());
        //创建部门参与人metadata
        for (UserEntity person : projectInfo.getPersons()) {
            projectMetadataService.create(id, ProjectMetadataType.persons, person.getName(), person.getDisplayName());
        }
        projectMetadataService.create(id, ProjectMetadataType.department, projectInfo.getDepartment().getId(), projectInfo.getDepartment().getName());
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(id);
        projectEntity.setCompanyId(projectInfo.getCompanyId());
        projectEntity.setName(projectInfo.getName());
        projectEntity.setStartDate(projectInfo.getStartDate());
        projectEntity.setEndDate(projectInfo.getEndDate());
        projectEntity.setStatus(projectInfo.getStatus());
        projectEntity.setBudget(projectInfo.getBudget());
        projectEntity.setIntroduce(projectInfo.getIntroduce());
        return projectDao.create(projectEntity);
    }

    public boolean update (ProjectInfo projectInfo) {
        String id = projectInfo.getId();
        List<ProjectMetadataEntity> metadataEntities = projectMetadataService.getByProjectId(projectInfo.getId());
        for (ProjectMetadataEntity projectMetadataEntity : metadataEntities) {
            projectMetadataService.delete(projectMetadataEntity.getId());
        }
        projectMetadataService.create(id, ProjectMetadataType.owner, projectInfo.getOwner().getName(), projectInfo.getOwner().getDisplayName());
        //创建部门参与人metadata
        for (UserEntity person : projectInfo.getPersons()) {
            projectMetadataService.create(id, ProjectMetadataType.persons, person.getName(), person.getDisplayName());
        }
        projectMetadataService.create(id, ProjectMetadataType.department, projectInfo.getDepartment().getId(), projectInfo.getDepartment().getName());
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(id);
        projectEntity.setCompanyId(projectInfo.getCompanyId());
        projectEntity.setName(projectInfo.getName());
        projectEntity.setStartDate(projectInfo.getStartDate());
        projectEntity.setEndDate(projectInfo.getEndDate());
        projectEntity.setStatus(projectInfo.getStatus());
        projectEntity.setBudget(projectInfo.getBudget());
        projectEntity.setIntroduce(projectInfo.getIntroduce());
        projectDao.update(projectEntity);
        return true;
    }

    public ProjectInfo getProjectInfo(String companyId, String id) {
        ProjectInfo projectInfo = new ProjectInfo();
        ProjectEntity projectEntity = projectDao.getById(id);
        List<ProjectMetadataEntity> projectMetadataEntities = projectMetadataService.getByProjectId(id);
        List<UserEntity> persons = new ArrayList<UserEntity>();
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String TimeString = time.format(new java.util.Date());
        System.out.println(TimeString);
        for (ProjectMetadataEntity projectMetadataEntity : projectMetadataEntities) {
            if (projectMetadataEntity.getProjectMetadataType().equals(ProjectMetadataType.persons)) {
                UserEntity userEntity = new UserEntity();
                userEntity.setName(projectMetadataEntity.getDataId());
                userEntity.setDisplayName(projectMetadataEntity.getDataName());
                persons.add(userEntity);
                //persons.add(userService.get(projectMetadataEntity.getDataId()));
            } else if (projectMetadataEntity.getProjectMetadataType().equals(ProjectMetadataType.owner)) {
                UserEntity userEntity = new UserEntity();
                userEntity.setName(projectMetadataEntity.getDataId());
                userEntity.setDisplayName(projectMetadataEntity.getDataName());
                projectInfo.setOwner(userEntity);
                //projectInfo.setOwner(userService.get(projectMetadataEntity.getDataId()));
            } else if (projectMetadataEntity.getProjectMetadataType().equals(ProjectMetadataType.department)) {
                DepartmentEntity departmentEntity = new DepartmentEntity();
                departmentEntity.setId(projectMetadataEntity.getDataId());
                departmentEntity.setName(projectMetadataEntity.getDataName());
                projectInfo.setDepartment(departmentEntity);
                //projectInfo.setDepartment(departmentService.getById(companyId, projectMetadataEntity.getDataId()));
            }
        }
        projectInfo.setPersons(persons);
        projectInfo.setByProjectEntity(projectEntity);
        SimpleDateFormat time2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String TimeString2 = time2.format(new java.util.Date());
        System.out.println(TimeString2);
        return projectInfo;
    }

    public ProjectInfo getProjectInfo(List<ProjectMetadataEntity> projectMetadataList, ProjectEntity projectEntity) {
        ProjectInfo projectInfo = new ProjectInfo();
        List<ProjectMetadataEntity> projectMetadataEntities = projectMetadataService.getByProjectId(projectEntity.getId(), projectMetadataList);
        List<UserEntity> persons = new ArrayList<UserEntity>();
        for (ProjectMetadataEntity projectMetadataEntity : projectMetadataEntities) {
            if (projectMetadataEntity.getProjectMetadataType().equals(ProjectMetadataType.persons)) {
                UserEntity userEntity = new UserEntity();
                userEntity.setName(projectMetadataEntity.getDataId());
                userEntity.setDisplayName(projectMetadataEntity.getDataName());
                persons.add(userEntity);
            } else if (projectMetadataEntity.getProjectMetadataType().equals(ProjectMetadataType.owner)) {
                UserEntity userEntity = new UserEntity();
                userEntity.setName(projectMetadataEntity.getDataId());
                userEntity.setDisplayName(projectMetadataEntity.getDataName());
                projectInfo.setOwner(userEntity);
            } else if (projectMetadataEntity.getProjectMetadataType().equals(ProjectMetadataType.department)) {
                DepartmentEntity departmentEntity = new DepartmentEntity();
                departmentEntity.setId(projectMetadataEntity.getDataId());
                departmentEntity.setName(projectMetadataEntity.getDataName());
                projectInfo.setDepartment(departmentEntity);
            }
        }
        projectInfo.setPersons(persons);
        projectInfo.setByProjectEntity(projectEntity);
        return projectInfo;
    }


    public List<ProjectInfo> listProjectInfo(String companyId) {
        List<ProjectInfo> projectInfos = new ArrayList<ProjectInfo>();
        List<ProjectEntity> projectEntities = projectDao.list();
        List<ProjectMetadataEntity> projectMetadataEntityList = projectMetadataService.list();
        for (ProjectEntity p : projectEntities) {
            if (p.getCompanyId().equals(companyId)) {
                projectInfos.add(getProjectInfo(projectMetadataEntityList, p));
            }
        }
        return  projectInfos;
    }

    public List<ProjectInfo> listProjectInfo(String companyId, String userId) {
        UserEntity userEntity = userService.get(userId);
        if ("admin".equals(userEntity.getRole())) {
            return list4admin(companyId);
        } else if("departmentadmin".equals(userEntity.getRole())) {
            return listByDepartmentId(companyId, userEntity.getDepartmentId());
        } else {
            return listByUserId(companyId, userEntity.getName());
        }
    }

    private List<ProjectInfo> list4admin(String companyId) {
        List<ProjectInfo> projectInfos = new ArrayList<ProjectInfo>();
        List<ProjectEntity> projectEntities = projectDao.list();
        List<ProjectMetadataEntity> projectMetadataEntityList = projectMetadataService.list();
        for (ProjectEntity p : projectEntities) {
            if (p.getCompanyId().equals(companyId)) {
                projectInfos.add(getProjectInfo(projectMetadataEntityList, p));
            }
        }
        return projectInfos;
    }

    private List<ProjectInfo> listByDepartmentId(String companyId, String departmentId) {
        List<ProjectInfo> projectInfos = new ArrayList<ProjectInfo>();
        List<ProjectMetadataEntity> projectMetadataEntities = projectMetadataService.listByDepartmentId(departmentId);
        List<ProjectMetadataEntity> projectMetadataEntityList = projectMetadataService.list();
        for (ProjectMetadataEntity projectMetadataEntity : projectMetadataEntities) {
            ProjectEntity projectEntity = projectDao.getById(projectMetadataEntity.getProjectId());
            projectInfos.add(getProjectInfo(projectMetadataEntityList, projectEntity));
        }
        return projectInfos;
    }

    private List<ProjectInfo> listByUserId(String companyId, String userId) {
        List<ProjectInfo> projectInfos = new ArrayList<ProjectInfo>();
        List<ProjectMetadataEntity> projectMetadataEntities = projectMetadataService.listByUserId(userId);
        List<ProjectMetadataEntity> projectMetadataEntityList = projectMetadataService.list();
        for (ProjectMetadataEntity projectMetadataEntity : projectMetadataEntities) {
            ProjectEntity projectEntity = projectDao.getById(projectMetadataEntity.getProjectId());
            projectInfos.add(getProjectInfo(projectMetadataEntityList, projectEntity));
        }
        return projectInfos;
    }

    public boolean deleteByDepartmentId(String departmentId) {
        List<ProjectMetadataEntity> projectMetadataEntities = projectMetadataService.listByDepartmentId(departmentId);
        for (ProjectMetadataEntity projectMetadataEntity : projectMetadataEntities) {
            String projectId = projectMetadataEntity.getProjectId();
            this.delete(projectId);
        }
        return true;
    }

    public boolean delete(String id) {
        meetingService.deleteByProjectId(id);
        materialService.deleteByProjectId(id);
        projectMetadataService.deleteByProjectId(id);
        projectDao.deleteById(id);
        return true;
    }
}
