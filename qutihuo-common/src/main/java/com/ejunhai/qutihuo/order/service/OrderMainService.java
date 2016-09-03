package com.ejunhai.qutihuo.order.service;

import java.util.List;

import com.ejunhai.qutihuo.coupon.model.Coupon;
import com.ejunhai.qutihuo.order.model.OrderMain;

/**
 * OrderMain Service 接口
 * 
 * @author parcel
 * @date 2014-12-10 21:36:31
 */
public interface OrderMainService {

	/**
	 * 根据Id获取OrderMain
	 * 
	 * @param id
	 * @return
	 */
	public OrderMain read(Integer id);

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

	/**
	 * 根据订单号获取订单
	 * 
	 * @param orderMainNo
	 * @return
	 */
	public OrderMain getOrderMainByOrderMainNo(String orderMainNo);

	/**
	 * 创建订单
	 * 
	 * @param coupon
	 * @param orderMain
	 * @return
	 * @throws Exception
	 */
	public OrderMain createOrderMain(Coupon coupon, OrderMain orderMain) throws Exception;

	/**
	 * 更新收货地址
	 * 
	 * @param orderMain
	 */
	public void changeConsigneeInfo(OrderMain orderMain);

	/**
	 * 发货
	 * 
	 * @param orderMain
	 */
	public void deliverOrderMain(OrderMain orderMain);

}
