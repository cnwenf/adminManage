package com.bupt.sse.adminManage.service;

import com.bupt.sse.adminManage.dao.iface.CompanyDao;
import com.bupt.sse.adminManage.dao.iface.UserDao;
import com.bupt.sse.adminManage.entity.CompanyEntity;
import com.bupt.sse.adminManage.entity.UserEntity;
import com.bupt.sse.adminManage.entity.common.Roles;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * Created by WenFe on 2017/3/16.
 */
@Service("companyService")
public class CompanyService {
    @Resource
    private CompanyDao companyDao;
    @Resource
    private UserDao userDao;

    public CompanyEntity create(String name, String ownerId, String address, String email, String phone, String introduce) {
        CompanyEntity companyEntity = new CompanyEntity();
        String id = UUID.randomUUID().toString();
        companyEntity.setId(id);
        companyEntity.setName(name);
        companyEntity.setOwnerId(ownerId);
        companyEntity.setAddress(address);
        companyEntity.setEmail(email);
        companyEntity.setPhone(phone);
        companyEntity.setIntroduce(introduce);
        if(companyDao.create(companyEntity)){
            UserEntity userEntity = userDao.getById(ownerId);
            userEntity.setCompanyId(companyEntity.getId());
            userEntity.setRole("admin");
            userDao.update(userEntity);
            return companyEntity;
        } else {
            return null;
        }
    }

    public List<CompanyEntity> list() {
        return companyDao.list();
    }

    public CompanyEntity getById(String id) {
        return companyDao.getById(id);
    }

    public void update(String id, String name, String ownerId, String address, String email, String phone, String introduce) {
        CompanyEntity companyEntity = getById(id);
        companyEntity.setName(name);
        companyEntity.setOwnerId(ownerId);
        companyEntity.setAddress(address);
        companyEntity.setEmail(email);
        companyEntity.setPhone(phone);
        companyEntity.setIntroduce(introduce);
        companyDao.update(companyEntity);
    }

    public void delete(String id) {
        companyDao.deleteById(id);
    }
}
