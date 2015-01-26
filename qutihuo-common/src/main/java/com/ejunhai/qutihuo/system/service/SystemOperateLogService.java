package com.ejunhai.qutihuo.system.service;

import java.util.List;

import com.ejunhai.qutihuo.system.model.SystemOperateLog;

/**
 * 
 * SystemOperateLog Service 接口
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:22:36
 * 
 */
public interface SystemOperateLogService {

    /**
     * 根据Id获取SystemOperateLog
     * 
     * @param id
     * @return
     */
    public SystemOperateLog read(Integer id);
    
    /**
     * 新增SystemOperateLog
     * 
     * @param systemOperateLog
     */
    public void insert(SystemOperateLog systemOperateLog);

    /**
     * 更新SystemOperateLog
     * 
     * @param systemOperateLog
     */
    public void update(SystemOperateLog systemOperateLog);
    
    /**
     * 删除SystemOperateLog
     * 
     * @param id
     */
    public void delete(Integer id);
    
    /**
     * 查询SystemOperateLog数量
     * 
     * @param systemOperateLog
     * @return
     */
    public Integer querySystemOperateLogCount(SystemOperateLog systemOperateLog);
    
    /**
     * 查询SystemOperateLog列表
     * 
     * @param systemOperateLog
     * @return
     */
    public List<SystemOperateLog> querySystemOperateLogList(SystemOperateLog systemOperateLog);

}
