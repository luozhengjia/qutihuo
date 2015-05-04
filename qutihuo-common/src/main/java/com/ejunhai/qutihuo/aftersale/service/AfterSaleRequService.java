package com.ejunhai.qutihuo.aftersale.service;

import java.util.List;

import com.ejunhai.qutihuo.aftersale.dto.AfterSaleRequDto;
import com.ejunhai.qutihuo.aftersale.model.AfterSaleRequ;

/**
 * 
 * AfterSaleRequ Service 接口
 * 
 * @author parcel
 * 
 * @date 2015-04-09 20:22:19
 * 
 */
public interface AfterSaleRequService {

	/**
	 * 根据Id获取AfterSaleRequ
	 * 
	 * @param id
	 * @return
	 */
	public AfterSaleRequ read(Integer id);

	/**
	 * 根据订单号读取售后申请
	 * 
	 * @param orderMainNo
	 * @return
	 */
	public AfterSaleRequ getAfterSaleRequByOrderMainNo(String orderMainNo);

	/**
	 * 新增AfterSaleRequ
	 * 
	 * @param afterSaleRequ
	 */
	public void insert(AfterSaleRequ afterSaleRequ);

	/**
	 * 更新AfterSaleRequ
	 * 
	 * @param afterSaleRequ
	 */
	public void update(AfterSaleRequ afterSaleRequ);

	/**
	 * 查询AfterSaleRequ数量
	 * 
	 * @param AfterSaleRequDto
	 * @return
	 */
	public Integer queryAfterSaleRequCount(AfterSaleRequDto afterSaleRequDto);

	/**
	 * 查询AfterSaleRequ列表
	 * 
	 * @param afterSaleRequ
	 * @return
	 */
	public List<AfterSaleRequ> queryAfterSaleRequList(AfterSaleRequDto afterSaleRequDto);

}
