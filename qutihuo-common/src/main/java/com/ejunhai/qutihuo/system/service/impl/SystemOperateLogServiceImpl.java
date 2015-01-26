package com.ejunhai.qutihuo.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.system.dao.SystemOperateLogMapper;
import com.ejunhai.qutihuo.system.model.SystemOperateLog;
import com.ejunhai.qutihuo.system.service.SystemOperateLogService;

/**
 * SystemOperateLog Service 实现类
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:22:36
 * 
 */
@Service("systemOperateLogService")
public class SystemOperateLogServiceImpl implements SystemOperateLogService {

    @Resource
    private SystemOperateLogMapper systemOperateLogMapper;

    @Override
    public SystemOperateLog read(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(SystemOperateLog systemOperateLog) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(SystemOperateLog systemOperateLog) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Integer querySystemOperateLogCount(SystemOperateLog systemOperateLog) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SystemOperateLog> querySystemOperateLogList(SystemOperateLog systemOperateLog) {
        // TODO Auto-generated method stub
        return null;
    }

}
