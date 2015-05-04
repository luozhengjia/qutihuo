package com.ejunhai.qutihuo.coupon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ejunhai.qutihuo.coupon.dto.CouponDto;
import com.ejunhai.qutihuo.coupon.model.Coupon;
import com.ejunhai.qutihuo.coupon.model.CouponSchema;

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
	public void disturbCoupons(Integer couponSchemaId);

	/**
	 * 更新券状态
	 * 
	 * @param id
	 * @param state
	 */
	public void updateCouponState(@Param("id") Integer id, @Param("state") Integer state);

	/**
	 * 使用礼品卡
	 * 
	 * @param coupon
	 */
	public void useCoupon(Coupon coupon);

}
