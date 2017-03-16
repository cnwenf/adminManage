package com.bupt.sse.adminManage.controller;

import com.bupt.sse.adminManage.entity.CompanyEntity;
import com.bupt.sse.adminManage.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by WenFe on 2017/3/16.
 */
@RequestMapping("/company")
@Controller
public class CompanyController {
    @Resource
    private CompanyService companyService;

    @RequestMapping("/list")
    @ResponseBody
    public List<CompanyEntity> list(){
        return companyService.list();
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public CompanyEntity get(String id) {
        return companyService.getById(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public void create(String name, String ownerId,
                       @RequestParam(required=false) String address,
                       @RequestParam(required=false) String email,
                       @RequestParam(required=false) String phone,
                       @RequestParam(required=false) String introduce) {
        companyService.create(name, ownerId, address, email, phone, introduce);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public void delete(String id) {
        companyService.delete(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void update(String id,
                       @RequestParam(required=false) String name,
                       @RequestParam(required=false) String ownerId,
                       @RequestParam(required=false) String address,
                       @RequestParam(required=false) String email,
                       @RequestParam(required=false) String phone,
                       @RequestParam(required=false) String introduce) {
        companyService.update(id, name, ownerId, address, email, phone, introduce);
    }

}
