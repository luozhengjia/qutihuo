package com.ejunhai.qutihuo.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.system.dao.SystemConfigMapper;
import com.ejunhai.qutihuo.system.model.SystemConfig;
import com.ejunhai.qutihuo.system.service.SystemConfigService;

/**
 * SystemConfig Service 实现类
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:22:36
 * 
 */
@Service("systemConfigService")
public class SystemConfigServiceImpl implements SystemConfigService {

    @Resource
    private SystemConfigMapper systemConfigMapper;

    @Override
    public SystemConfig read(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(SystemConfig systemConfig) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(SystemConfig systemConfig) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Integer querySystemConfigCount(SystemConfig systemConfig) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SystemConfig> querySystemConfigList(SystemConfig systemConfig) {
        // TODO Auto-generated method stub
        return null;
    }

}
