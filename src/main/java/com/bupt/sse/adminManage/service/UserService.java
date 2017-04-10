package com.bupt.sse.adminManage.service;

import com.bupt.sse.adminManage.dao.iface.UserDao;
import com.bupt.sse.adminManage.entity.UserEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * Created by WenFeng on 2017/3/16.
 */
@Service("userService")
public class UserService {
    @Resource
    private UserDao userDao;

    public void create(String displayName, String name, String password, String IDCard, String workNum, String departmentId, String projectId, String phone, String email, String time, int status, String history, int payment) {
        UserEntity userEntity = new UserEntity();
        userEntity.setDisplayName(displayName);
        userEntity.setName(name);
        userEntity.setPassword(password);
        userEntity.setIDCard(IDCard);
        userEntity.setWorkNum(workNum);
        userEntity.setDepartmentId(departmentId);
        userEntity.setProjectId(projectId);
        userEntity.setPhone(phone);
        userEntity.setEmail(email);
        userEntity.setTime(time);
        userEntity.setStatus(status);
        userEntity.setHistory(history);
        userEntity.setPayment(payment);
        userDao.create(userEntity);
    }

    public boolean login(String name, String password) {
        UserEntity userEntity = userDao.getById(name);
        if(null != userEntity && userEntity.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public List<UserEntity> list() {
        return userDao.list();
    }

//    public void delete()
}
