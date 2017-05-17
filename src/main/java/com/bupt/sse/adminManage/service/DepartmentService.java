package com.bupt.sse.adminManage.service;

import com.bupt.sse.adminManage.dao.iface.DepartmentDao;
import com.bupt.sse.adminManage.entity.common.BasePK;
import com.bupt.sse.adminManage.entity.DepartmentEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by WenFeng on 2017/3/15.
 */
@Service("departmentService")
public class DepartmentService {
    @Resource
    private DepartmentDao departmentDao;
    @Resource
    private ProjectService projectService;

    private static String nodes = "nodes";
    private static String text = "text";
    private static String href = "href";

    public JSONArray getDepartmentStructure(String companyId) {
        List<DepartmentEntity> departmentEntities = list(companyId);
        List<JSONObject> jsonObjects = entity2JSON(departmentEntities);
        for (JSONObject childObject : jsonObjects) {
            childObject.put(text, childObject.getString("name"));
            childObject.put(href, "department-detail.html?companyId="+companyId+"&id=" + childObject.getString("id"));
            if (childObject.has("parentId")) {
                String parentId = childObject.getString("parentId");
                for (JSONObject parentObject : jsonObjects) {
                    if (parentObject.getString("id").equals(parentId)) {
                        if (!parentObject.has(nodes)) {
                            JSONArray jsonArray = new JSONArray();
                            parentObject.put(nodes, jsonArray);
                        }
                        parentObject.getJSONArray(nodes).put(childObject);
                    }
                }
            }
        }
        JSONArray result = new JSONArray();
        for (JSONObject jsonObject : jsonObjects) {
            if (!jsonObject.has("parentId") || "".equals(jsonObject.getString("parentId"))) {
                result.put(jsonObject);
            }
        }
        return result;
    }

    public boolean create(String companyId, String name, String description, String parentId, String ownerId) {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        String id = UUID.randomUUID().toString();
        departmentEntity.setCompanyId(companyId);
        departmentEntity.setId(id);
        departmentEntity.setName(name);
        departmentEntity.setDescription(description);
        departmentEntity.setParentId(parentId);
        departmentEntity.setOwnerId(ownerId);
        return departmentDao.create(departmentEntity);
    }

    public List<DepartmentEntity> list(String companyId) {
        List<DepartmentEntity> departmentEntities = departmentDao.list();
        List<DepartmentEntity> result = new ArrayList<DepartmentEntity>();
        for (DepartmentEntity entity : departmentEntities) {
            if (companyId.equals(entity.getCompanyId())) {
                result.add(entity);
            }
        }
        return result;
    }

    public DepartmentEntity getById(String companyId, String id) {
        BasePK pk = new BasePK(companyId, id);
        return departmentDao.getById(pk);
    }

    public void save(DepartmentEntity departmentEntity) {
        departmentDao.create(departmentEntity);
    }

    public void update(String companyId, String id, String name, String description, String ownerId) {
        BasePK pk = new BasePK(companyId, id);
        DepartmentEntity departmentEntity = departmentDao.getById(pk);
        departmentEntity.setName(name);
        departmentEntity.setDescription(description);
        departmentEntity.setOwnerId(ownerId);
        departmentDao.update(departmentEntity);
    }

    public void delete(String companyId, String id) {
        projectService.deleteByDepartmentId(id);
        BasePK pk = new BasePK(companyId, id);
        departmentDao.deleteById(pk);
    }

    private List<JSONObject> entity2JSON(List<DepartmentEntity> departmentEntities) {
        ArrayList<JSONObject> jsonObjects = new ArrayList<JSONObject>();
        for (DepartmentEntity departmentEntity : departmentEntities) {
            jsonObjects.add(new JSONObject(departmentEntity));
        }
        return jsonObjects;
    }
}
