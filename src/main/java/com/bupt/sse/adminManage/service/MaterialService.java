package com.bupt.sse.adminManage.service;

import com.bupt.sse.adminManage.dao.iface.MaterialDao;
import com.bupt.sse.adminManage.dao.iface.ProjectDao;
import com.bupt.sse.adminManage.entity.MaterialEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by WenFe on 2017/5/11.
 */
@Service("MaterialService")
public class MaterialService {
    @Resource
    private MaterialDao materialDao;
    @Resource
    private ProjectDao projectDao;
    @Resource
    private UserService userService;
    public boolean create (String companyId, String name, String num, String projectId, String spUserId, String exUserId, String time, String spInfo) {
        MaterialEntity materialEntity = new MaterialEntity();
        String id = UUID.randomUUID().toString();
        materialEntity.setId(id);
        materialEntity.setCompanyId(companyId);
        materialEntity.setName(name);
        materialEntity.setNum(num);
        materialEntity.setTime(time);
        materialEntity.setSpInfo(spInfo);
        materialEntity.setStatus("run");

        materialEntity.setProjectId(projectId);
        materialEntity.setProjectName(projectDao.getById(projectId).getName());

        materialEntity.setSpUserId(spUserId);
        materialEntity.setSpUserName(userService.get(spUserId).getDisplayName());

        materialEntity.setExUserId(exUserId);
        materialEntity.setExUserName(userService.get(exUserId).getDisplayName());

        return materialDao.create(materialEntity);
    }

    public void delete(String id) {
        materialDao.deleteById(id);
    }

    public void update(String id, String status, String exInfo) {
        MaterialEntity materialEntity = materialDao.getById(id);
        materialEntity.setStatus(status);
        materialEntity.setExInfo(exInfo);
        materialDao.update(materialEntity);
    }

    public MaterialEntity get(String id) {
        return materialDao.getById(id);
    }

    public List<MaterialEntity> listBySpUser(String userId) {
        List<MaterialEntity> materialEntities = materialDao.list();
        List<MaterialEntity> result = new ArrayList<MaterialEntity>();
        for (MaterialEntity materialEntity : materialEntities) {
            if(materialEntity.getSpUserId().equals(userId)) {
                result.add(materialEntity);
            }
        }
        return result;
    }

    public List<MaterialEntity> listByExUser(String userId) {
        List<MaterialEntity> materialEntities = materialDao.list();
        List<MaterialEntity> result = new ArrayList<MaterialEntity>();
        for (MaterialEntity materialEntity : materialEntities) {
            if(materialEntity.getExUserId().equals(userId) && materialEntity.getStatus().equals("run")) {
                result.add(materialEntity);
            }
        }
        return result;
    }

    public List<MaterialEntity> listByProject(String projectId) {
        List<MaterialEntity> materialEntities = materialDao.list();
        List<MaterialEntity> result = new ArrayList<MaterialEntity>();
        for (MaterialEntity materialEntity : materialEntities) {
            if(materialEntity.getProjectId().equals(projectId)) {
                result.add(materialEntity);
            }
        }
        return result;
    }
}
