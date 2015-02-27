package com.ejunhai.qutihuo.coupon.service;

import java.util.List;

import com.ejunhai.qutihuo.coupon.dto.CouponDto;
import com.ejunhai.qutihuo.coupon.model.Coupon;

/**
 * 
 * Coupon Service 接口
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:40:05
 * 
 */
public interface CouponService {

	/**
	 * 查询Coupon数量
	 * 
	 * @param coupon
	 * @return
	 */
	public Integer queryCouponCount(CouponDto couponDto);

	/**
	 * 查询Coupon列表
	 * 
	 * @param coupon
	 * @return
	 */
	public List<Coupon> queryCouponList(CouponDto couponDto);

	/**
	 * 批量生成礼品卡
	 * 
	 * @param couponSchemaId
	 * @param num
	 */
	public void batchGenerateCoupon(Integer couponSchemaId, int num);

	/**
	 * 券码密码扰乱
	 * 
	 * @param couponSchemaId
	 */
	public void disturbCoupon(Integer couponSchemaId);

}
