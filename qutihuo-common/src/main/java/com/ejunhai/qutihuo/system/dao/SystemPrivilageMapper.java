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
	 * 根据角色ID获取角色列表
	 * 
	 * @param roleIds
	 * @return
	 */
	public List<SystemPrivilage> getSystemPrivilageListByRoleIds(List<Integer> roleIds);

	/**
	 * 批量新增
	 * 
	 * @param systemPrivilageList
	 */
	public void batchAddSystemPrivilage(List<SystemPrivilage> systemPrivilageList);

	/**
	 * 根据角色ID删除权限
	 * 
	 * 
	 * @param id
	 */
	public void deleteByRoleId(Integer roleId);

}
