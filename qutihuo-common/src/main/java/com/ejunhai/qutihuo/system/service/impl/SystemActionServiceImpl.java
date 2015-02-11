package com.ejunhai.qutihuo.system.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.system.dao.SystemActionMapper;
import com.ejunhai.qutihuo.system.dto.SystemActionDto;
import com.ejunhai.qutihuo.system.model.SystemAction;
import com.ejunhai.qutihuo.system.service.SystemActionService;

/**
 * SystemAction Service 实现类
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:22:36
 * 
 */
@Service("systemActionService")
public class SystemActionServiceImpl implements SystemActionService {

	@Resource
	private SystemActionMapper systemActionMapper;

	@Override
	public Integer querySystemActionCount(SystemActionDto systemActionDto) {
		return systemActionMapper.querySystemActionCount(systemActionDto);
	}

	@Override
	public List<SystemAction> querySystemActionList(SystemActionDto systemActionDto) {
		return systemActionMapper.querySystemActionList(systemActionDto);
	}

	@Override
	public SystemAction read(Integer id) {
		return systemActionMapper.read(id);
	}

	@Override
	public void insert(SystemAction systemAction) {
		systemAction.setCreateTime(new Timestamp(System.currentTimeMillis()));
		systemActionMapper.insert(systemAction);
	}

	@Override
	public void update(SystemAction systemAction) {
		SystemAction oldSystemAction = this.read(systemAction.getId());
		oldSystemAction.setActionName(systemAction.getActionName());
		oldSystemAction.setActionType(systemAction.getActionType());
		oldSystemAction.setParentId(systemAction.getParentId());
		oldSystemAction.setUrl(systemAction.getUrl());
		oldSystemAction.setIconCss(systemAction.getIconCss());
		oldSystemAction.setWeight(systemAction.getWeight());
		oldSystemAction.setRemark(systemAction.getRemark());
		systemActionMapper.update(oldSystemAction);
	}

	@Override
	public void delete(Integer id) {
		systemActionMapper.delete(id);
	}

	@Override
	public List<SystemAction> getSystemActionListByIds(List<Integer> actionIdList) {
		List<SystemAction> systemActionList = new ArrayList<SystemAction>();
		if (CollectionUtils.isNotEmpty(actionIdList)) {
			systemActionList = systemActionMapper.getSystemActionListByIds(actionIdList);
		}
		return systemActionList;
	}

	@Override
	public List<SystemAction> getAllSystemActionList() {
		SystemActionDto systemActionDto = new SystemActionDto();
		systemActionDto.setOffset(0);
		systemActionDto.setPageSize(Integer.MAX_VALUE);
		return systemActionMapper.querySystemActionList(systemActionDto);
	}
}
