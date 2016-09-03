package com.ejunhai.qutihuo.order.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejunhai.qutihuo.aftersale.enums.RequState;
import com.ejunhai.qutihuo.aftersale.model.AfterSaleRequ;
import com.ejunhai.qutihuo.aftersale.service.AfterSaleRequService;
import com.ejunhai.qutihuo.errors.JunhaiAssert;
import com.ejunhai.qutihuo.order.dao.OrderReplMapper;
import com.ejunhai.qutihuo.order.dto.OrderReplDto;
import com.ejunhai.qutihuo.order.enums.LogisticsCompany;
import com.ejunhai.qutihuo.order.enums.OrderState;
import com.ejunhai.qutihuo.order.model.OrderLog;
import com.ejunhai.qutihuo.order.model.OrderMain;
import com.ejunhai.qutihuo.order.model.OrderRepl;
import com.ejunhai.qutihuo.order.service.OrderLogService;
import com.ejunhai.qutihuo.order.service.OrderReplService;
import com.ejunhai.qutihuo.order.utils.OrderUtil;
import com.ejunhai.qutihuo.system.service.SystemAreaService;

/**
 * OrderRepl Service 实现类
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:36:31
 * 
 */
@Service("orderReplService")
public class OrderReplServiceImpl implements OrderReplService {

	@Resource
	private OrderReplMapper orderReplMapper;

	@Resource
	private SystemAreaService systemAreaService;

	@Resource
	private OrderLogService orderLogService;

	@Resource
	private AfterSaleRequService afterSaleRequService;

	@Override
	public OrderRepl read(Integer id) {
		return orderReplMapper.read(id);
	}

	@Override
	public OrderRepl getOrderReplByOrderReplNo(String orderReplNo) {
		return orderReplMapper.getOrderReplByOrderReplNo(orderReplNo);
	}

	@Override
	public OrderRepl getOrderReplByOrderMainNo(String orderMainNo) {
		return this.orderReplMapper.getOrderReplByOrderMainNo(orderMainNo);
	}

	@Override
	public void update(OrderRepl orderRepl) {
		orderReplMapper.update(orderRepl);
	}

	@Override
	public void delete(Integer id) {
		orderReplMapper.delete(id);
	}

	@Override
	public Integer queryOrderReplCount(OrderReplDto orderReplDto) {
		return orderReplMapper.queryOrderReplCount(orderReplDto);
	}

	@Override
	public List<OrderRepl> queryOrderReplList(OrderReplDto orderReplDto) {
		return orderReplMapper.queryOrderReplList(orderReplDto);
	}

	@Override
	public OrderRepl createOrderRepl(OrderMain orderMain, OrderRepl orderRepl) {
		orderRepl.setMerchantId(orderMain.getMerchantId());
		orderRepl.setOrderReplNo(OrderUtil.createOrderReplNo());
		orderRepl.setOrderMainNo(orderMain.getOrderMainNo());
		orderRepl.setCreateTime(new Timestamp(System.currentTimeMillis()));
		orderRepl.setOrderDate(orderMain.getOrderDate());
		orderRepl.setPayAmount(orderMain.getPayAmount());
		orderRepl.setLogisticsCompany(orderMain.getLogisticsCompany());

		// 封装省市区数据
		String provinceCityArea = systemAreaService.getProvinceCityArea(orderRepl.getAreaCode());
		orderRepl.setProvinceCityArea(provinceCityArea);
		orderRepl.setState(OrderState.NO_DELIVER.getValue());
		orderReplMapper.insert(orderRepl);

		// 更新售后请求状态
		AfterSaleRequ afterSaleRequ = afterSaleRequService.getAfterSaleRequByOrderMainNo(orderMain.getOrderMainNo());
		if (afterSaleRequ != null) {
			afterSaleRequ.setDealTime(new Timestamp(System.currentTimeMillis()));
			afterSaleRequ.setDealInfo(orderRepl.getRemark());
			afterSaleRequ.setState(RequState.deal.getValue());
			afterSaleRequService.update(afterSaleRequ);
		}

		// 记录订单处理日志
		OrderLog orderLog = new OrderLog();
		orderLog.setRemark("补货单已生成，正在给您备货。");
		orderLog.setOrderNo(orderRepl.getOrderReplNo());
		orderLog.setOperateUser("system");
		orderLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
		orderLogService.insert(orderLog);
		return orderRepl;
	}

	@Override
	public void changeConsigneeInfo(OrderRepl orderRepl) {
		String provinceCityArea = systemAreaService.getProvinceCityArea(orderRepl.getAreaCode());
		orderRepl.setProvinceCityArea(provinceCityArea);
		this.orderReplMapper.update(orderRepl);
	}

	@Override
	@Transactional
	public void deliverOrderRepl(OrderRepl orderRepl) {
		OrderRepl oldOrderRepl = this.read(orderRepl.getId());
		JunhaiAssert.notNull(oldOrderRepl, "补货单ID无效");

		oldOrderRepl.setLogisticsCompany(orderRepl.getLogisticsCompany());
		oldOrderRepl.setExpressOrderNo(orderRepl.getExpressOrderNo());
		oldOrderRepl.setState(OrderState.DELIVERD.getValue());
		oldOrderRepl.setDeliverTime(new Timestamp(System.currentTimeMillis()));
		this.orderReplMapper.update(oldOrderRepl);

		// 发送短信

		// 记录订单处理日志
		OrderLog orderLog = new OrderLog();
		String lcogisticsCompany = LogisticsCompany.get(Integer.parseInt(orderRepl.getLogisticsCompany())).getTitle();
		String logiInfo = lcogisticsCompany + ",快递单号：" + orderRepl.getExpressOrderNo();
		orderLog.setRemark("补货单已出库，请您留意签收。" + logiInfo);
		orderLog.setOrderNo(oldOrderRepl.getOrderReplNo());
		orderLog.setOperateUser("system");
		orderLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
		orderLogService.insert(orderLog);
	}
}
