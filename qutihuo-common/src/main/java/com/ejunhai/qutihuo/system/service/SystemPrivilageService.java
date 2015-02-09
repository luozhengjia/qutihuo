package com.ejunhai.qutihuo.system.service;

import java.util.List;
import java.util.Set;

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
	 * 根据角色ID获取角色列表
	 * 
	 * @param roleIds
	 * @return
	 */
	public List<SystemPrivilage> getSystemPrivilageListByRoleIds(String roleIds);

	/**
	 * 批量保存权限
	 * 
	 * @param roleId
	 * @param actionIdSet
	 */
	public void saveSystemPrivilage(Integer roleId, Set<Integer> actionIdSet);

}
