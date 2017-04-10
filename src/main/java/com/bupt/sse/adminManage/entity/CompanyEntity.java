package com.bupt.sse.adminManage.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by WenFeng on 2017/3/16.
 */
@Entity(name="companyInfo")
public class CompanyEntity implements Serializable{
    @Id
    private String id;
    private String name;
    private String ownerId;
    private String address;
    private String email;
    private String phone;
    private String introduce;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
