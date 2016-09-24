package com.ejunhai.qutihuo.system.dao;

import java.util.List;

import com.ejunhai.qutihuo.system.model.SystemArea;

/**
 * SystemAreaMapper 地区信息
 * 
 * @author parcel
 * 
 * @date 2015-04-06 21:29:22
 * 
 */
public interface SystemAreaMapper {

	/**
	 * 根据编号获取区域
	 * 
	 * @param no
	 * @return
	 */
	public SystemArea getAreaByNo(String no);

	public List<SystemArea> getAll();
}
