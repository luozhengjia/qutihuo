package com.ejunhai.qutihuo.order.dao;

import java.util.List;

import com.ejunhai.qutihuo.order.dto.OrderLogDto;
import com.ejunhai.qutihuo.order.model.OrderLog;

/**
 * OrderLogMapper 订单日志表
 * 
 * @author parcel
 * 
 * @date 2015-05-31 15:43:53
 * 
 */
public interface OrderLogMapper {

	/**
	 * 新增订单日志
	 * 
	 * @param OrderLogDto
	 * @return
	 */
	public int insert(OrderLog orderLog);

	/**
	 * 获取订单日志列表
	 * 
	 * @param orderLog
	 * @return
	 */
	public List<OrderLog> queryOrderLogList(OrderLogDto orderLogDto);

}
