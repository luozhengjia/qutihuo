package com.ejunhai.qutihuo.system.model;

import java.io.Serializable;

/**
 * 系统权限表
 *
 * @author parcel
 * @date 2014-12-10 21:18:30
 */
public class SystemPrivilage implements Serializable {
    
    private static final long serialVersionUID = 2349237408009549841L;

    /**
     * 系统权限ID
     */
    private Integer id;
    
    /**
     * 角色ID
     */
    private Integer roleId;
    
    /**
     * 操作ID
     */
    private Integer actionId;
    
    public Integer getId() {
        return id;
    }
        
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getRoleId() {
        return roleId;
    }
        
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    
    public Integer getActionId() {
        return actionId;
    }
        
    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }
    
}
