package com.bupt.sse.adminManage.dao.impl;

import com.bupt.sse.adminManage.dao.UserDAO;
import com.bupt.sse.adminManage.dao.common.BaseDaoImpl;
import com.bupt.sse.adminManage.entity.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mark on 4/24/15.
 */

@Repository
public class UserDAOImpl extends BaseDaoImpl<User> implements UserDAO {

}
