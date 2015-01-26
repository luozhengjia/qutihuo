package com.ejunhai.qutihuo.system.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 系统角色表
 * 
 * @author parcel
 * @date 2014-12-10 21:18:30
 */
public class SystemRole implements Serializable {

    private static final long serialVersionUID = 7145624372459845737L;

    /**
     * 
     */
    private Integer id;

    /**
     * 角色类型 1 系统默认 2 自定义
     */
    private Integer roleType;

    /**
     * 商户ID
     */
    private Integer merchantId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

}
