package com.ejunhai.qutihuo.system.dao;

import java.util.List;

import com.ejunhai.qutihuo.system.model.SystemConfig;

/**
 * SystemConfigMapper
 * 系统配置表
 *
 * @author parcel
 * 
 * @date 2014-12-10 21:22:36
 * 
 */
public interface SystemConfigMapper {

    /**
     * 根据Id获取SystemConfig
     * 
     * @param id
     * @return
     */
    public SystemConfig read(Integer id);
    
    /**
     * 新增SystemConfig
     * 
     * @param systemConfig
     */
    public void insert(SystemConfig systemConfig);

    /**
     * 更新SystemConfig
     * 
     * @param systemConfig
     */
    public void update(SystemConfig systemConfig);
    
    /**
     * 删除SystemConfig
     * 
     * @param id
     */
    public void delete(Integer id);
    
    /**
     * 查询SystemConfig数量
     * 
     * @param systemConfig
     * @return
     */
    public Integer querySystemConfigCount(SystemConfig systemConfig);
    
    /**
     * 查询SystemConfig列表
     * 
     * @param systemConfig
     * @return
     */
    public List<SystemConfig> querySystemConfigList(SystemConfig systemConfig);

}
