package com.bupt.sse.adminManage.controller;

import com.bupt.sse.adminManage.entity.DepartmentEntity;
import com.bupt.sse.adminManage.entity.UserEntity;
import com.bupt.sse.adminManage.entity.common.ProjectStatus;
import com.bupt.sse.adminManage.service.ProjectInfo;
import com.bupt.sse.adminManage.service.ProjectService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by WenFe on 2017/5/8.
 */
@RequestMapping("/project")
@Controller
public class ProjectController {
    @Resource
    private ProjectService projectService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public boolean create(ProjectInfo projectInfo) {
        JSONObject jsonObject = new JSONObject(projectInfo);
        return true;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public ProjectInfo get(String companyId, String id) {
        return projectService.getProjectInfo(companyId, id);
    }
}
