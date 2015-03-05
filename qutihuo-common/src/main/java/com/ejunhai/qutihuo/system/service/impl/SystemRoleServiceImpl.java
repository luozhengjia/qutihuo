package com.ejunhai.qutihuo.system.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.system.dao.SystemRoleMapper;
import com.ejunhai.qutihuo.system.dto.SystemRoleDto;
import com.ejunhai.qutihuo.system.model.SystemRole;
import com.ejunhai.qutihuo.system.service.SystemRoleService;

/**
 * SystemRole Service 实现类
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:22:36
 * 
 */
@Service("systemRoleService")
public class SystemRoleServiceImpl implements SystemRoleService {

	@Resource
	private SystemRoleMapper systemRoleMapper;

	@Override
	public SystemRole read(Integer id) {
		return systemRoleMapper.read(id);
	}

	@Override
	public void insert(SystemRole systemRole) {
		systemRole.setCreateTime(new Timestamp(System.currentTimeMillis()));
		systemRoleMapper.insert(systemRole);
	}

	@Override
	public void update(SystemRole systemRole) {
		SystemRole newSystemRole = systemRoleMapper.read(systemRole.getId());
		newSystemRole.setRoleName(systemRole.getRoleName());
		systemRoleMapper.update(newSystemRole);
	}

	@Override
	public void delete(Integer id) {
		systemRoleMapper.delete(id);
	}

	@Override
	public Integer querySystemRoleCount(SystemRoleDto systemRoleDto) {
		return systemRoleMapper.querySystemRoleCount(systemRoleDto);
	}

	@Override
	public List<SystemRole> querySystemRoleList(SystemRoleDto systemRoleDto) {
		return systemRoleMapper.querySystemRoleList(systemRoleDto);
	}

	@Override
	public List<SystemRole> getSystemRoleListByIds(List<Integer> idList) {
		return systemRoleMapper.getSystemRoleListByIds(idList);
	}

}
