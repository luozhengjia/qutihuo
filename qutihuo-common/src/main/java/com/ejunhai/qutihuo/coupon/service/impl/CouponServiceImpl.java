package com.ejunhai.qutihuo.coupon.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.coupon.dao.CouponMapper;
import com.ejunhai.qutihuo.coupon.dto.CouponDto;
import com.ejunhai.qutihuo.coupon.enums.Confusion;
import com.ejunhai.qutihuo.coupon.enums.CouponState;
import com.ejunhai.qutihuo.coupon.enums.InitActivated;
import com.ejunhai.qutihuo.coupon.model.Coupon;
import com.ejunhai.qutihuo.coupon.model.CouponSchema;
import com.ejunhai.qutihuo.coupon.service.CouponSchemaService;
import com.ejunhai.qutihuo.coupon.service.CouponService;
import com.ejunhai.qutihuo.coupon.utils.CouponUtil;
import com.ejunhai.qutihuo.errors.JunhaiAssert;

/**
 * Coupon Service 实现类
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:40:05
 * 
 */
@Service("couponService")
public class CouponServiceImpl implements CouponService {

	@Resource
	private CouponMapper couponMapper;

	@Resource
	private CouponSchemaService couponSchemaService;

	@Override
	public Integer queryCouponCount(CouponDto couponDto) {
		return couponMapper.queryCouponCount(couponDto);
	}

	@Override
	public List<Coupon> queryCouponList(CouponDto couponDto) {
		return couponMapper.queryCouponList(couponDto);
	}

	@Override
	public void batchGenerateCoupon(Integer couponSchemaId, int num) {
		CouponSchema couponSchema = couponSchemaService.read(couponSchemaId);
		JunhaiAssert.notNull(couponSchema, "礼品卡不存在");

		List<Coupon> couponList = new ArrayList<Coupon>(num);
		for (int i = 0; i < num; i++) {
			Coupon coupon = new Coupon();
			coupon.setCouponSchemaId(couponSchemaId);
			coupon.setMerchantId(couponSchema.getMerchantId());
			coupon.setCouponNumber(CouponUtil.generateCouponNumber());
			coupon.setCouponPassword(CouponUtil.generateCouponPassword());
			if (InitActivated.yes.getValue().equals(couponSchema.getInitActivate())) {
				coupon.setState(CouponState.unused.getValue());
			} else {
				coupon.setState(CouponState.notactivated.getValue());
			}

			coupon.setUseStartdate(new Timestamp(couponSchema.getUseStartdate().getTime()));
			coupon.setUseEnddate(new Timestamp(couponSchema.getUseEnddate().getTime()));
			coupon.setCreateTime(new Timestamp(System.currentTimeMillis()));
			couponList.add(coupon);
		}

		// 批量新增
		couponMapper.batchAddCoupon(couponList);

		couponSchema.setHasIssueNum(couponSchema.getHasIssueNum() + num);
		couponSchemaService.update(couponSchema);
	}

	@Override
	public void disturbCoupon(Integer couponSchemaId) {
		CouponSchema couponSchema = couponSchemaService.read(couponSchemaId);
		JunhaiAssert.notNull(couponSchema, "礼品卡不存在");

		if (Confusion.no.getValue().equals(couponSchema.getHasConfusion())) {
			couponMapper.disturbCoupon(couponSchemaId);
			couponSchema.setHasConfusion(Confusion.yes.getValue());
			couponSchemaService.update(couponSchema);
		}
	}
}
