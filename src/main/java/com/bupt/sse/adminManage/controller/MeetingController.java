package com.bupt.sse.adminManage.controller;

import com.bupt.sse.adminManage.entity.MeetingEntity;
import com.bupt.sse.adminManage.service.MeetingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by WenFe on 2017/5/10.
 */
@RequestMapping("/meeting")
@Controller
public class MeetingController {
    @Resource
    private MeetingService meetingService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public boolean create(String companyId, String projectId, String name, String time, String location, String ownerId) {
        return meetingService.create(companyId, projectId, name, time, location, ownerId);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public List<MeetingEntity> list(String companyId) {
        return meetingService.list(companyId);
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public MeetingEntity get(String id) {
        return meetingService.get(id);
    }

    @RequestMapping(value = "/listByProject", method = RequestMethod.POST)
    @ResponseBody
    public List<MeetingEntity> listByProject(String projectId) {
        return meetingService.getListByProjectId(projectId);
    }

    @RequestMapping(value = "/listByUser", method = RequestMethod.POST)
    @ResponseBody
    public List<MeetingEntity> listByUser(String userId) {
        return meetingService.getMeetingsByUserId(userId);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public boolean update(String id, String log) {
        meetingService.update(id , log);
        return true;
    }
}
