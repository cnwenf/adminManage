package com.bupt.sse.adminManage.service;

import com.bupt.sse.adminManage.dao.iface.MeetingDao;
import com.bupt.sse.adminManage.entity.MeetingEntity;
import com.bupt.sse.adminManage.entity.UserEntity;
import com.bupt.sse.adminManage.entity.common.ProjectMetadataType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by WenFe on 2017/5/10.
 */
@Service("meetingService")
public class MeetingService {
    @Resource
    private MeetingDao meetingDao;
    @Resource
    private ProjectMetadataService projectMetadataService;
    @Resource
    private ProjectService projectService;
    @Resource
    private UserService userService;

    public boolean create(String companyId, String projectId, String name, String time, String location, String ownerId) {
        String id = UUID.randomUUID().toString();
        MeetingEntity meetingEntity = new MeetingEntity();
        meetingEntity.setId(id);
        meetingEntity.setCompanyId(companyId);
        meetingEntity.setProjectId(projectId);
        meetingEntity.setProjectName(projectService.getProjectInfo(companyId, projectId).getName());
        meetingEntity.setName(name);
        meetingEntity.setTime(time);
        meetingEntity.setLocation(location);
        meetingEntity.setOwnerId(ownerId);
        meetingEntity.setOwnerName(userService.get(ownerId).getDisplayName());
        projectMetadataService.create(projectId, ProjectMetadataType.meeting, id, name);
        return meetingDao.create(meetingEntity);
    }

    public List<MeetingEntity> list(String companyId) {
        List<MeetingEntity> meetingEntities = meetingDao.list();
        List<MeetingEntity> result = new ArrayList<MeetingEntity>();
        for (MeetingEntity meetingEntity : meetingEntities) {
            if (meetingEntity.getCompanyId().equals(companyId)) {
                result.add(meetingEntity);
            }
        }
        return result;
    }

    public List<MeetingEntity> getListByProjectId(String projectId) {
        List<MeetingEntity> meetingEntities = meetingDao.list();
        List<MeetingEntity> result = new ArrayList<MeetingEntity>();
        for (MeetingEntity meetingEntity : meetingEntities) {
            if (meetingEntity.getProjectId().equals(projectId)) {
                result.add(meetingEntity);
            }
        }
        return result;
    }

    public List<MeetingEntity> getMeetingsByUserId(String userId) {
        List<MeetingEntity> meetingEntities = meetingDao.list();
        List<MeetingEntity> result = new ArrayList<MeetingEntity>();
        for (MeetingEntity meetingEntity : meetingEntities) {
            if (meetingEntity.getOwnerId().equals(userId)) {
                result.add(meetingEntity);
            } else {
                ProjectInfo projectInfo = projectService.getProjectInfo(meetingEntity.getCompanyId(), meetingEntity.getProjectId());
                if (projectInfo.getOwner().getName().equals(userId)) {
                    result.add(meetingEntity);
                } else {
                    for (UserEntity userEntity : projectInfo.getPersons()) {
                        if (userEntity.getName().equals(userId)) {
                            result.add(meetingEntity);
                        }
                    }
                }
            }
        }
        return result;
    }

    public MeetingEntity get(String id) {
        return meetingDao.getById(id);
    }

    public void update(String id, String log) {
        MeetingEntity meetingEntity = meetingDao.getById(id);
        meetingEntity.setLog(log);
        meetingDao.update(meetingEntity);
    }
}
