package com.bupt.sse.adminManage.dao;

import com.bupt.sse.adminManage.entity.User;

import java.util.List;

/**
 * Created by mark on 4/24/15.
 */
public interface UserDAO {
    public int save(User u);
    public List<User> findAll();
}
