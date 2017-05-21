package com.bupt.sse.adminManage.controller;

import com.bupt.sse.adminManage.entity.DepartmentEntity;
import com.bupt.sse.adminManage.service.DepartmentService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by WenFeng on 2017/3/15.
 */
@RequestMapping("/department")
@Controller
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @RequestMapping(value = "/structure", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    @ResponseBody
    public String structure(String companyId){
        return departmentService.getDepartmentStructure(companyId).toString();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public boolean create(String companyId,
                        @RequestParam(required=true) String name,
                       @RequestParam(required=false) String description,
                       @RequestParam(required=false) String parentId,
                       @RequestParam(required=false) String ownerId){
        return departmentService.create(companyId, name, description, parentId, ownerId);
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<DepartmentEntity> list(String companyId){
        return departmentService.list(companyId);
    }

    @RequestMapping("/listByUser")
    @ResponseBody
    public List<DepartmentEntity> listByUser(String companyId, String userId){
        return departmentService.listByUser(companyId, userId);
    }


    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public DepartmentEntity get(String companyId, String id){
        return departmentService.getById(companyId, id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public boolean update(String companyId, String id,
                       @RequestParam(required=false) String name,
                       @RequestParam(required=false) String description,
                       @RequestParam(required=false) String ownerId){
        departmentService.update(companyId, id, name, description, ownerId);
        return true;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public boolean delete(String companyId, String id){
        departmentService.delete(companyId, id);
        return true;
    }

    @RequestMapping(value = "/existChild", method = RequestMethod.POST)
    @ResponseBody
    public boolean existChild(String companyId, String id){
        departmentService.existChild(companyId, id);
        return true;
    }
}
