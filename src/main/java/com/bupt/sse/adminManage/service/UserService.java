package com.bupt.sse.adminManage.service;

import com.bupt.sse.adminManage.dao.iface.UserDao;
import com.bupt.sse.adminManage.entity.UserEntity;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by WenFeng on 2017/3/16.
 */
@Service("userService")
public class UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private DepartmentService departmentService;

    public boolean create(String displayName, String name, String password, String IDCard, String workNum, String departmentId, String projectId, String phone, String email, String time, int status, String history, int payment,String companyId, String role) {
        UserEntity userEntity = new UserEntity();
        userEntity.setDisplayName(displayName);
        userEntity.setName(name);
        userEntity.setPassword(password);
        userEntity.setIDCard(IDCard);
        userEntity.setWorkNum(workNum);
        userEntity.setCompanyId(companyId);
        if (null != departmentId && !"".equals(departmentId)) {
            userEntity.setDepartmentId(departmentId);
            userEntity.setDepartmentName(departmentService.getById(companyId, departmentId).getName());
        }
        userEntity.setProjectId(projectId);
        userEntity.setPhone(phone);
        userEntity.setEmail(email);
        userEntity.setTime(time);
        userEntity.setStatus(status);
        userEntity.setHistory(history);
        userEntity.setPayment(payment);
        userEntity.setRole(role);
        return userDao.create(userEntity);

    }
    public UserEntity login(String name, String password) {
        UserEntity userEntity = userDao.getById(name);
        if (null != userEntity && userEntity.getPassword().equals(password)) {
            return userEntity;
        } else {
            return null;
        }

    }

    public List<UserEntity> list(String companyId) {
        List<UserEntity> userEntities = userDao.list();
        List<UserEntity> result = new ArrayList<UserEntity>();
        for (UserEntity userEntity : userEntities) {
            if (null != userEntity.getCompanyId() && userEntity.getCompanyId().equals(companyId)) {
                result.add(userEntity);
            }
        }
        return result;
    }

    public List<UserEntity> listByDepartment(String companyId, String departmentId) {
        List<UserEntity> userEntities = userDao.list();
        List<UserEntity> result = new ArrayList<UserEntity>();
        for (UserEntity userEntity : userEntities) {
            if (null != userEntity.getCompanyId()
                    && userEntity.getCompanyId().equals(companyId)
                    && null != userEntity.getDepartmentId()
                    && userEntity.getDepartmentId().equals(departmentId)) {
                result.add(userEntity);
            }
        }
        return result;
    }

    public List<UserEntity> listByRole(String companyId, String role) {
        List<UserEntity> userEntities = userDao.list();
        List<UserEntity> result = new ArrayList<UserEntity>();
        if (role.equals("materialadmin")) {
            for (UserEntity userEntity : userEntities) {
                if (null != userEntity.getCompanyId()
                        && userEntity.getCompanyId().equals(companyId)
                        && null != userEntity.getRole()
                        && (userEntity.getRole().equals(role) || userEntity.getRole().equals("admin"))) {
                    result.add(userEntity);
                }
            }
        }
        return result;
    }

    public UserEntity get(String id) {
        return userDao.getById(id);
    }

    public boolean update(String name,
                          String displayName,
                          String departmentId,
                          String workNum,
                          String phone,
                          String email,
                          String history,
                          String role) {
        UserEntity userEntity = userDao.getById(name);
        userEntity.setDisplayName(displayName);
        userEntity.setDepartmentId(departmentId);
        userEntity.setDepartmentName(departmentService.getById(userEntity.getCompanyId(), departmentId).getName());
        userEntity.setWorkNum(workNum);
        userEntity.setPhone(phone);
        userEntity.setEmail(email);
        userEntity.setHistory(history);
        userEntity.setRole(role);
        userDao.update(userEntity);
        return true;
    }
    public boolean delete(String id) {
        userDao.deleteById(id);
        return true;
    }
}
