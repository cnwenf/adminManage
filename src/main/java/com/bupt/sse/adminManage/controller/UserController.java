package com.bupt.sse.adminManage.controller;

import com.bupt.sse.adminManage.entity.UserEntity;
import com.bupt.sse.adminManage.service.UserService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by WenFe on 2017/4/10.
 */
@RequestMapping("/user")
@Controller
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public UserEntity login(String name, String password) {
        return userService.login(name, password);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public boolean login(String IDCard, String displayName, String password) {
        return userService.create(displayName, IDCard, password, IDCard, null, null, null, null, null, null, 0, null, 0, null);
    }

    @RequestMapping(value ="/list", method = RequestMethod.POST)
    @ResponseBody
    public List<UserEntity> list(String companyId) {
        return userService.list(companyId);
    }

    @RequestMapping(value ="/detail", method = RequestMethod.POST)
    @ResponseBody
    public UserEntity get(String name) {
        return userService.get(name);
    }

    @RequestMapping(value ="/create", method = RequestMethod.POST)
    @ResponseBody
    public boolean create(String name,
                             String displayName,
                             String departmentId,
                             String workNum,
                             String companyId,
                             String phone,
                             String email,
                             String history) {
        return userService.create(displayName, name, name, name, workNum, departmentId, null, phone, email, null, 0, history, 0, companyId);
    }

}
