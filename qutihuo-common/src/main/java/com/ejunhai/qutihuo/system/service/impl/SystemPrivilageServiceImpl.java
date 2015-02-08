package com.ejunhai.qutihuo.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
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
	public List<SystemPrivilage> getSystemPrivilageListByRoleIds(String roleIds) {
		List<SystemPrivilage> systemPrivilageList = new ArrayList<SystemPrivilage>();
		if (StringUtils.isBlank(roleIds)) {
			return systemPrivilageList;
		}

		return systemPrivilageList;
	}

	@Override
	public void batchAddSystemPrivilage(List<SystemPrivilage> systemPrivilageList) {
		if (CollectionUtils.isNotEmpty(systemPrivilageList)) {
			systemPrivilageMapper.batchAddSystemPrivilage(systemPrivilageList);
		}
	}

	@Override
	public void deleteByRoleId(Integer roleId) {
		systemPrivilageMapper.deleteByRoleId(roleId);
	}

}
