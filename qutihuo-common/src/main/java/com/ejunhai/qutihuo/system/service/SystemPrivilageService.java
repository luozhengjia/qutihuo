package com.ejunhai.qutihuo.system.service;

import java.util.List;

import com.ejunhai.qutihuo.system.model.SystemPrivilage;

/**
 * 
 * SystemPrivilage Service 接口
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:22:36
 * 
 */
public interface SystemPrivilageService {

    /**
     * 根据Id获取SystemPrivilage
     * 
     * @param id
     * @return
     */
    public SystemPrivilage read(Integer id);

    /**
     * 新增SystemPrivilage
     * 
     * @param systemPrivilage
     */
    public void insert(SystemPrivilage systemPrivilage);

    /**
     * 更新SystemPrivilage
     * 
     * @param systemPrivilage
     */
    public void update(SystemPrivilage systemPrivilage);

    /**
     * 删除SystemPrivilage
     * 
     * @param id
     */
    public void delete(Integer id);

    /**
     * 查询SystemPrivilage数量
     * 
     * @param systemPrivilage
     * @return
     */
    public Integer querySystemPrivilageCount(SystemPrivilage systemPrivilage);

    /**
     * 查询SystemPrivilage列表
     * 
     * @param systemPrivilage
     * @return
     */
    public List<SystemPrivilage> querySystemPrivilageList(SystemPrivilage systemPrivilage);

    /**
     * 根据角色ID获取角色列表
     * 
     * @param roleIds
     * @return
     */
    public List<SystemPrivilage> getSystemPrivilageListByRoleIds(String roleIds);
}
