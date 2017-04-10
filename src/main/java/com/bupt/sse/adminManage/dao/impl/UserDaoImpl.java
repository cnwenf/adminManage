package com.bupt.sse.adminManage.dao.impl;

import com.bupt.sse.adminManage.dao.common.BaseDao;
import com.bupt.sse.adminManage.dao.common.BaseDaoImpl;
import com.bupt.sse.adminManage.dao.iface.UserDao;
import com.bupt.sse.adminManage.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by WenFe on 2017/3/16.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<UserEntity> implements UserDao{
}
