package com.ejunhai.qutihuo.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.system.dao.SystemPrivilageMapper;
import com.ejunhai.qutihuo.system.model.SystemPrivilage;
import com.ejunhai.qutihuo.system.service.SystemPrivilageService;

/**
 * SystemPrivilage Service 实现类
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:22:36
 * 
 */
@Service("systemPrivilageService")
public class SystemPrivilageServiceImpl implements SystemPrivilageService {

    @Resource
    private SystemPrivilageMapper systemPrivilageMapper;

    @Override
    public SystemPrivilage read(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(SystemPrivilage systemPrivilage) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(SystemPrivilage systemPrivilage) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Integer querySystemPrivilageCount(SystemPrivilage systemPrivilage) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SystemPrivilage> querySystemPrivilageList(SystemPrivilage systemPrivilage) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SystemPrivilage> getSystemPrivilageListByRoleIds(String roleIds) {
        List<SystemPrivilage> systemPrivilageList = new ArrayList<SystemPrivilage>();
        if (StringUtils.isBlank(roleIds)) {
            return systemPrivilageList;
        }

        // TODO Auto-generated method stub
        return systemPrivilageList;
    }

}
