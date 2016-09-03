package com.ejunhai.qutihuo.order.dao;

import java.util.List;

import com.ejunhai.qutihuo.order.dto.OrderReplDto;
import com.ejunhai.qutihuo.order.model.OrderRepl;

/**
 * OrderReplMapper 补货单表
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:36:31
 * 
 */
public interface OrderReplMapper {

	/**
	 * 根据Id获取OrderRepl
	 * 
	 * @param id
	 * @return
	 */
	public OrderRepl read(Integer id);

	/**
	 * 根据补货单号获取补货单
	 * 
	 * @param orderReplNo
	 * @return
	 */
	public OrderRepl getOrderReplByOrderReplNo(String orderReplNo);

	/**
	 * 根据订单号获取补货单
	 * 
	 * @param orderMainNo
	 * @return
	 */
	public OrderRepl getOrderReplByOrderMainNo(String orderMainNo);

	/**
	 * 新增OrderRepl
	 * 
	 * @param orderRepl
	 */
	public void insert(OrderRepl orderRepl);

	/**
	 * 更新OrderRepl
	 * 
	 * @param orderRepl
	 */
	public void update(OrderRepl orderRepl);

	/**
	 * 删除OrderRepl
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	/**
	 * 查询OrderRepl数量
	 * 
	 * @param orderRepl
	 * @return
	 */
	public Integer queryOrderReplCount(OrderReplDto orderReplDto);

	/**
	 * 查询OrderRepl列表
	 * 
	 * @param orderRepl
	 * @return
	 */
	public List<OrderRepl> queryOrderReplList(OrderReplDto orderReplDto);
}
