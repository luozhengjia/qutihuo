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

	@Override
	public String getProvinceCityArea(String areaNo) {
		// 将areaNo拆分出省市区编码
		String cityNo = areaNo.substring(0, areaNo.lastIndexOf("-"));
		String provinceNo = cityNo.substring(0, cityNo.lastIndexOf("-"));
		
		// 拼出省市区
		StringBuffer sb = new StringBuffer();
		sb.append(this.getAreaByNo(provinceNo).getName());
		sb.append(this.getAreaByNo(cityNo).getName());
		sb.append(this.getAreaByNo(areaNo).getName());
		return sb.toString();
	}
}
