package com.bupt.sse.adminManage.controller;

import com.bupt.sse.adminManage.service.DepartmentService;
import com.bupt.sse.adminManage.service.ProjectInfo;
import com.bupt.sse.adminManage.service.ProjectService;
import com.bupt.sse.adminManage.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by WenFe on 2017/5/8.
 */
@RequestMapping("/project")
@Controller
public class ProjectController {
    @Resource
    private ProjectService projectService;
    @Resource
    private UserService userService;
    @Resource
    private DepartmentService departmentService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public boolean create(String projectInfo) {
        ProjectInfo pi = new ProjectInfo(projectInfo, userService, departmentService);
        return projectService.create(pi);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public boolean update(String projectInfo) {
        ProjectInfo pi = new ProjectInfo(projectInfo, userService, departmentService);
        return projectService.update(pi);
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public ProjectInfo get(String companyId, String id) {
        return projectService.getProjectInfo(companyId, id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public List<ProjectInfo> list(String companyId) {
        return projectService.listProjectInfo(companyId);
    }
}
