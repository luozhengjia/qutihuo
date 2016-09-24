package com.ejunhai.qutihuo.order.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.coupon.model.Coupon;
import com.ejunhai.qutihuo.coupon.model.CouponSchema;
import com.ejunhai.qutihuo.coupon.service.CouponSchemaService;
import com.ejunhai.qutihuo.coupon.service.CouponService;
import com.ejunhai.qutihuo.errors.JunhaiAssert;
import com.ejunhai.qutihuo.order.dao.OrderMainMapper;
import com.ejunhai.qutihuo.order.enums.LogisticsCompany;
import com.ejunhai.qutihuo.order.enums.OrderPrint;
import com.ejunhai.qutihuo.order.enums.OrderState;
import com.ejunhai.qutihuo.order.model.OrderLog;
import com.ejunhai.qutihuo.order.model.OrderMain;
import com.ejunhai.qutihuo.order.service.OrderLogService;
import com.ejunhai.qutihuo.order.service.OrderMainService;
import com.ejunhai.qutihuo.order.utils.OrderUtil;
import com.ejunhai.qutihuo.system.service.SystemAreaService;

/**
 * OrderMain Service 实现类
 * 
 * @author parcel
 * @date 2014-12-10 21:36:31
 */
@Service("orderMainService")
public class OrderMainServiceImpl implements OrderMainService {

	@Resource
	private OrderMainMapper orderMainMapper;

	@Resource
	private CouponService couponService;

	@Resource
	private CouponSchemaService couponSchemaService;

	@Resource
	private SystemAreaService systemAreaService;

	@Resource
	private OrderLogService orderLogService;

	@Override
	public OrderMain read(Integer id) {
		return orderMainMapper.read(id);
	}

	@Override
	public void update(OrderMain orderMain) {
		orderMainMapper.update(orderMain);
	}

	@Override
	public void delete(Integer id) {
		orderMainMapper.delete(id);
	}

	@Override
	public Integer queryOrderMainCount(OrderMain orderMain) {
		return orderMainMapper.queryOrderMainCount(orderMain);
	}

	@Override
	public List<OrderMain> queryOrderMainList(OrderMain orderMain) {
		return orderMainMapper.queryOrderMainList(orderMain);
	}

	@Override
	public OrderMain getOrderMainByOrderMainNo(String orderMainNo) {
		return orderMainMapper.getOrderMainByOrderMainNo(orderMainNo);
	}

	@Override
	public OrderMain createOrderMain(Coupon coupon, OrderMain orderMain) throws Exception {

		// 创建订单数据
		String orderMainNo = OrderUtil.createOrderMainNo();
		orderMain.setOrderMainNo(orderMainNo);
		CouponSchema couponSchema = couponSchemaService.read(coupon.getCouponSchemaId());
		orderMain.setMerchantId(coupon.getMerchantId());
		orderMain.setPayAmount(couponSchema.getParValue());
		orderMain.setCouponNumber(coupon.getCouponNumber());
		orderMain.setCreateTime(new Timestamp(System.currentTimeMillis()));
		orderMain.setState(OrderState.NO_DELIVER.getValue());
		String provinceCityArea = systemAreaService.getProvinceCityArea(orderMain.getAreaCode());
		orderMain.setProvinceCityArea(provinceCityArea);
		orderMain.setIsPrintExpress(OrderPrint.NO_PRINT.getValue());
		orderMainMapper.insert(orderMain);

		// 更新优惠券状态
		couponService.useCoupon(coupon.getCouponNumber(), orderMainNo);

		// 发送短信

		// 记录订单处理日志
		OrderLog orderLog = new OrderLog();
		orderLog.setRemark("订单已预定成功，正在给您备货。");
		orderLog.setOrderNo(orderMainNo);
		orderLog.setOperateUser("system");
		orderLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
		orderLogService.insert(orderLog);

		return orderMain;
	}

	@Override
	public void changeConsigneeInfo(OrderMain orderMain) {
		OrderMain oldOrderMain = this.read(orderMain.getId());
		JunhaiAssert.notNull(oldOrderMain, "订单ID无效");
		
		oldOrderMain.setOrderDate(orderMain.getOrderDate());
		oldOrderMain.setConsignee(orderMain.getConsignee());
		oldOrderMain.setTelephone(orderMain.getTelephone());
		oldOrderMain.setProvinceCode(orderMain.getProvinceCode());
		oldOrderMain.setCityCode(orderMain.getCityCode());
		oldOrderMain.setAreaCode(orderMain.getAreaCode());
		String provinceCityArea = systemAreaService.getProvinceCityArea(orderMain.getAreaCode());
		oldOrderMain.setProvinceCityArea(provinceCityArea);
		oldOrderMain.setDetailAddress(orderMain.getDetailAddress());
		this.orderMainMapper.update(oldOrderMain);
	}

	@Override
	public void deliverOrderMain(OrderMain orderMain) {
		OrderMain oldOrderMain = this.read(orderMain.getId());
		JunhaiAssert.notNull(oldOrderMain, "订单ID无效");

		oldOrderMain.setLogisticsCompany(orderMain.getLogisticsCompany());
		oldOrderMain.setExpressOrderNo(orderMain.getExpressOrderNo());
		oldOrderMain.setState(OrderState.DELIVERD.getValue());
		oldOrderMain.setDeliverTime(new Timestamp(System.currentTimeMillis()));
		this.orderMainMapper.update(oldOrderMain);

		// 发送短信

		// 记录订单处理日志
		OrderLog orderLog = new OrderLog();
		String lcogisticsCompany = LogisticsCompany.get(Integer.parseInt(orderMain.getLogisticsCompany())).getTitle();
		String logiInfo = lcogisticsCompany + ",快递单号：" + orderMain.getExpressOrderNo();
		orderLog.setRemark("订单已出库，请您留意签收。" + logiInfo);
		orderLog.setOrderNo(oldOrderMain.getOrderMainNo());
		orderLog.setOperateUser("system");
		orderLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
		orderLogService.insert(orderLog);
	}
}
