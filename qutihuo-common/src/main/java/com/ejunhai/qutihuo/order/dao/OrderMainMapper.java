package com.ejunhai.qutihuo.order.dao;

import java.util.List;

import com.ejunhai.qutihuo.order.model.OrderMain;

/**
 * OrderMainMapper 订单表
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:36:31
 * 
 */
public interface OrderMainMapper {

	/**
	 * 根据Id获取OrderMain
	 * 
	 * @param id
	 * @return
	 */
	public OrderMain read(Integer id);

	/**
	 * 根据订单号获取订单
	 * 
	 * @param orderMainNo
	 * @return
	 */
	public OrderMain getOrderMainByOrderMainNo(String orderMainNo);

	/**
	 * 新增OrderMain
	 * 
	 * @param orderMain
	 */
	public void insert(OrderMain orderMain);

	/**
	 * 更新OrderMain
	 * 
	 * @param orderMain
	 */
	public void update(OrderMain orderMain);

	/**
	 * 删除OrderMain
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	/**
	 * 查询OrderMain数量
	 * 
	 * @param orderMain
	 * @return
	 */
	public Integer queryOrderMainCount(OrderMain orderMain);

	/**
	 * 查询OrderMain列表
	 * 
	 * @param orderMain
	 * @return
	 */
	public List<OrderMain> queryOrderMainList(OrderMain orderMain);

}
