package com.ejunhai.qutihuo.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejunhai.qutihuo.common.constant.CommonConstant;
import com.ejunhai.qutihuo.coupon.enums.CouponState;
import com.ejunhai.qutihuo.coupon.model.Coupon;
import com.ejunhai.qutihuo.coupon.model.CouponSchema;
import com.ejunhai.qutihuo.coupon.service.CouponSchemaService;
import com.ejunhai.qutihuo.coupon.service.CouponService;
import com.ejunhai.qutihuo.errors.JunhaiAssert;
import com.ejunhai.qutihuo.order.dao.CodingRuleMapper;
import com.ejunhai.qutihuo.order.dao.OrderMainMapper;
import com.ejunhai.qutihuo.order.enums.OrderState;
import com.ejunhai.qutihuo.order.model.OrderMain;
import com.ejunhai.qutihuo.order.service.OrderMainService;

/**
 * OrderMain Service 实现类
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:36:31
 * 
 */
@Service("orderMainService")
public class OrderMainServiceImpl implements OrderMainService {

	@Resource
	private OrderMainMapper orderMainMapper;

	@Resource
	private CodingRuleMapper codingRuleMapper;

	@Resource
	private CouponService couponService;

	@Resource
	private CouponSchemaService couponSchemaService;
	
	@Override
	public OrderMain read(Integer id) {
		return orderMainMapper.read(id);
	}

	@Override
	@Transactional
	public void insert(OrderMain orderMain) {
		//校验礼品卡是否有效
		Coupon coupon = couponService.getCouponByNo(orderMain.getCouponNumber());
		JunhaiAssert.notNull(coupon, "礼品卡不存在");
		// 检查礼品卡状态
		boolean flag = CouponState.unused.getValue().equals(coupon.getState());
		JunhaiAssert.isFalse(flag, "礼品卡状态不可使用");
		
		//根据礼品卡信息 设置订单
		orderMain.setMerchantId(coupon.getMerchantId());
		CouponSchema couponSchema = couponSchemaService.read(coupon.getCouponSchemaId());
		orderMain.setPayAmount(couponSchema.getParValue());
		
		
		//设置订单号
		String orderMainNo = CommonConstant.ORDER_NO_PREFIX + codingRuleMapper.getOrderNo();
		orderMain.setOrderMainNo(orderMainNo);
		orderMain.setState(OrderState.NO_DELIVER.getValue());
		orderMainMapper.insert(orderMain);
		
		//使用礼品卡
		couponService.useCoupon(orderMain.getCouponNumber(), orderMainNo);
		
	}

	@Override
	public void update(OrderMain orderMain) {
		orderMainMapper.update(orderMain);

	}

	@Override
	@Transactional
	public void delete(Integer id) {
		OrderMain orderMain = orderMainMapper.read(id);
		// 检查订单状态
		boolean flag = OrderState.NO_DELIVER.getValue().equals(orderMain.getState());
		JunhaiAssert.isTrue(flag, "订单状态不允许删除");
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

}
