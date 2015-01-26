package com.ejunhai.qutihuo.system.dao;

import java.util.List;

import com.ejunhai.qutihuo.system.model.SystemPrivilage;

/**
 * SystemPrivilageMapper
 * 系统权限表
 *
 * @author parcel
 * 
 * @date 2014-12-10 21:22:36
 * 
 */
public interface SystemPrivilageMapper {

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

}
