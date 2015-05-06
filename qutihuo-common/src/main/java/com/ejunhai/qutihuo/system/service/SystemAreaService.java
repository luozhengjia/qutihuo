package com.ejunhai.qutihuo.system.service;

import com.ejunhai.qutihuo.system.model.SystemArea;

/**
 * 
 * SystemArea Service 接口
 * 
 * @author parcel
 * 
 * @date 2015-04-06 21:29:22
 * 
 */
public interface SystemAreaService {

	/**
	 * 根据编号获取区域
	 * 
	 * @param no
	 * @return
	 */
	public SystemArea getAreaByNo(String no);

}
