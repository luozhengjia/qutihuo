package com.ejunhai.qutihuo.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.order.dao.OrderLogMapper;
import com.ejunhai.qutihuo.order.dto.OrderLogDto;
import com.ejunhai.qutihuo.order.model.OrderLog;
import com.ejunhai.qutihuo.order.service.OrderLogService;

/**
 * OrderLog Service 实现类
 * 
 * @author parcel
 * 
 * @date 2015-05-31 15:43:53
 * 
 */
@Service("orderLogService")
public class OrderLogServiceImpl implements OrderLogService {

	@Resource
	private OrderLogMapper orderLogMapper;

	@Override
	public int insert(OrderLog orderLog) {
		return orderLogMapper.insert(orderLog);
	}

	@Override
	public List<OrderLog> queryOrderLogList(OrderLogDto orderLogDto) {
		return orderLogMapper.queryOrderLogList(orderLogDto);
	}

}
