package com.bupt.sse.adminManage.controller;

import com.bupt.sse.adminManage.entity.MaterialEntity;
import com.bupt.sse.adminManage.service.MaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by WenFe on 2017/5/11.
 */
@RequestMapping("/material")
@Controller
public class MaterialController {
    @Resource
    private MaterialService materialService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public boolean create (String companyId, String name, String num, String projectId, String spUserId, String exUserId, String time, String spInfo) {
        return materialService.create(companyId, name, num, projectId, spUserId, exUserId, time, spInfo);
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public MaterialEntity create (String id) {
        return materialService.get(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public boolean delete (String id) {
        materialService.delete(id);
        return true;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public boolean update(String id, String status, String exInfo) {
        materialService.update(id, status, exInfo);
        return true;
    }



    @RequestMapping(value = "/listBySpUser", method = RequestMethod.POST)
    @ResponseBody
    public List<MaterialEntity> listBySpUser (String spUserId) {
        return materialService.listBySpUser(spUserId);
    }

    @RequestMapping(value = "/listByExUser", method = RequestMethod.POST)
    @ResponseBody
    public List<MaterialEntity> listByExUser(String exUserId) {
        return materialService.listByExUser(exUserId);
    }

    @RequestMapping(value = "/listByProject", method = RequestMethod.POST)
    @ResponseBody
    public List<MaterialEntity> listByProject(String projectId) {
        return materialService.listByProject(projectId);
    }

}
