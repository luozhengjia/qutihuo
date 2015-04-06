package com.ejunhai.qutihuo.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.system.dao.SystemAreaMapper;
import com.ejunhai.qutihuo.system.model.SystemArea;
import com.ejunhai.qutihuo.system.service.SystemAreaService;

/**
 * SystemArea Service 实现类
 * 
 * @author parcel
 * 
 * @date 2015-04-06 21:29:22
 * 
 */
@Service("systemAreaService")
public class SystemAreaServiceImpl implements SystemAreaService {

	@Resource
	private SystemAreaMapper systemAreaMapper;

	@Override
	public SystemArea getAreaByNo(String no) {
		return systemAreaMapper.getAreaByNo(no);
	}
}
