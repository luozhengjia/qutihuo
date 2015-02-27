package com.ejunhai.qutihuo.coupon.dao;

import java.util.List;

import com.ejunhai.qutihuo.coupon.dto.CouponDto;
import com.ejunhai.qutihuo.coupon.model.Coupon;

/**
 * CouponMapper 优惠券
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:40:05
 * 
 */
public interface CouponMapper {

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
	 * 批量新增
	 * 
	 * @param couponList
	 */
	public void batchAddCoupon(List<Coupon> couponList);

	/**
	 * 扰乱礼品卡密码
	 * 
	 * @param couponSchemaId
	 */
	public void disturbCoupon(Integer couponSchemaId);

}
