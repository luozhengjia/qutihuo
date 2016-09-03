package com.ejunhai.qutihuo.order.service;

import java.util.List;

import com.ejunhai.qutihuo.order.dto.OrderReplDto;
import com.ejunhai.qutihuo.order.model.OrderMain;
import com.ejunhai.qutihuo.order.model.OrderRepl;

/**
 * 
 * OrderRepl Service 接口
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:36:31
 * 
 */
public interface OrderReplService {

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

	/**
	 * 新增补货单
	 * 
	 * @param orderMain
	 * @return
	 */
	public OrderRepl createOrderRepl(OrderMain orderMain, OrderRepl orderRepl);

	/**
	 * 更改收货人地址
	 * 
	 * @param orderRepl
	 */
	public void changeConsigneeInfo(OrderRepl orderRepl);

	/**
	 * 补货单发货
	 * 
	 * @param orderMain
	 */
	public void deliverOrderRepl(OrderRepl orderRepl);

}
