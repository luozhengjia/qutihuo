package com.ejunhai.qutihuo.coupon.service;

import java.util.List;

import com.ejunhai.qutihuo.coupon.dto.CouponDto;
import com.ejunhai.qutihuo.coupon.model.Coupon;
import com.ejunhai.qutihuo.coupon.model.CouponSchema;

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
	 * 根据Id获取Coupon
	 * 
	 * @param id
	 * @return
	 */
	public Coupon read(Integer id);

	/**
	 * 根据券码获取礼品卡
	 * 
	 * @param couponNumber
	 * @return
	 */
	public Coupon getCouponByNo(String couponNumber);

	/**
	 * 根据订单号获取礼品卡
	 * 
	 * @param orderNumber
	 * @return
	 */
	public Coupon getCouponByOrderNo(String orderNumber);

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
	public void disturbCoupons(Integer couponSchemaId);

	/**
	 * 激活礼品卡
	 * 
	 * @param couponId
	 */
	public void activateCoupon(Integer couponId);

	/**
	 * 作废礼品卡
	 * 
	 * @param couponId
	 */
	public void discardCoupon(Integer couponId);

	/**
	 * 使用礼品卡
	 * 
	 * @param couponNumber
	 * @param orderNumber
	 */
	public void useCoupon(String couponNumber, String orderNumber);

}
