package com.ejunhai.qutihuo.order.service;

import java.util.List;

import com.ejunhai.qutihuo.order.dto.OrderLogDto;
import com.ejunhai.qutihuo.order.model.OrderLog;

/**
 * 
 * OrderLog Service 接口
 * 
 * @author parcel
 * 
 * @date 2015-05-31 15:43:53
 * 
 */
public interface OrderLogService {
	
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
