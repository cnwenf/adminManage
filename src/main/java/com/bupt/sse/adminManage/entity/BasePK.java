package com.bupt.sse.adminManage.entity;

import java.io.Serializable;

/**
 * Created by WenFe on 2017/3/15.
 */
public class BasePK implements Serializable {
    private String companyId;
    private String id;

    public BasePK(){}

    public BasePK(String companyId, String id) {
        setCompanyId(companyId);
        setId(id);
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getId() {
        return id;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setId(String id) {
        this.id = id;
    }
}
