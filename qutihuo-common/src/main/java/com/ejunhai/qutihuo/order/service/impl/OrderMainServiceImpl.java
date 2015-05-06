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
import com.ejunhai.qutihuo.order.enums.OrderState;
import com.ejunhai.qutihuo.order.model.OrderMain;
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
		orderMainMapper.insert(orderMain);

		// 更新优惠券状态
		couponService.useCoupon(coupon.getCouponNumber(), orderMainNo);

		// 发送短信

		return orderMain;
	}

	@Override
	public void changeConsigneeInfo(OrderMain orderMain) {
		OrderMain oldOrderMain = this.read(orderMain.getId());
		JunhaiAssert.notNull(oldOrderMain, "订单ID无效");

		oldOrderMain.setConsignee(orderMain.getConsignee());
		oldOrderMain.setTelephone(orderMain.getTelephone());
		oldOrderMain.setProvinceCode(orderMain.getProvinceCode());
		oldOrderMain.setCityCode(orderMain.getCityCode());
		oldOrderMain.setAreaCode(orderMain.getAreaCode());
		this.orderMainMapper.update(oldOrderMain);
	}

}
