package com.ejunhai.qutihuo.system.dao;

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
}
