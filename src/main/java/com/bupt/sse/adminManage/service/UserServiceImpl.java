package com.bupt.sse.adminManage.service;

import com.bupt.sse.adminManage.entity.User;
import com.bupt.sse.adminManage.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by mark on 4/24/15.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    public void saveUsers(List<User> us) {
        for (User u : us) {
            userDao.save(u);
        }
    }

    public List<User> getAllUsernames() {
        return userDao.list();
    }

    public User getById(Integer id) {
        return userDao.getById(id);
    }
}
