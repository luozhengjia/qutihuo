package com.ejunhai.qutihuo.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.errors.JunhaiAssert;
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

		List<Integer> roleIdList = new ArrayList<Integer>();
		String[] arrRoleId = roleIds.split(",");
		for (int i = 0; i < arrRoleId.length; i++) {
			roleIdList.add(Integer.parseInt(arrRoleId[i]));
		}

		return systemPrivilageMapper.getSystemPrivilageListByRoleIds(roleIdList);
	}

	@Override
	public void saveSystemPrivilage(Integer roleId, Set<Integer> actionIdSet) {
		JunhaiAssert.notNull(roleId, "角色ID不能为空");

		// 先删除历史数据
		systemPrivilageMapper.deleteByRoleId(roleId);

		List<SystemPrivilage> systemPrivilageList = new ArrayList<SystemPrivilage>();
		for (Integer actionId : actionIdSet) {
			SystemPrivilage systemPrivilage = new SystemPrivilage();
			systemPrivilage.setActionId(actionId);
			systemPrivilage.setRoleId(roleId);
			systemPrivilageList.add(systemPrivilage);
		}

		// 新增权限数据
		if (CollectionUtils.isNotEmpty(systemPrivilageList)) {
			systemPrivilageMapper.batchAddSystemPrivilage(systemPrivilageList);
		}
	}
}
