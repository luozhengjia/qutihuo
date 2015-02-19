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
	 * 根据Id获取Coupon
	 * 
	 * @param id
	 * @return
	 */
	public Coupon read(Integer id);

	/**
	 * 新增Coupon
	 * 
	 * @param coupon
	 */
	public void insert(Coupon coupon);

	/**
	 * 更新Coupon
	 * 
	 * @param coupon
	 */
	public void update(Coupon coupon);

	/**
	 * 删除Coupon
	 * 
	 * @param id
	 */
	public void delete(Integer id);

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

}
