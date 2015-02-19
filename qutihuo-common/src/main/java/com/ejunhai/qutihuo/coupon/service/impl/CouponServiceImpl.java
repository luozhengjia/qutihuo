package com.ejunhai.qutihuo.coupon.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.coupon.dao.CouponMapper;
import com.ejunhai.qutihuo.coupon.dto.CouponDto;
import com.ejunhai.qutihuo.coupon.model.Coupon;
import com.ejunhai.qutihuo.coupon.service.CouponService;

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

	@Override
	public Coupon read(Integer id) {
		return couponMapper.read(id);
	}

	@Override
	public void insert(Coupon coupon) {
		couponMapper.insert(coupon);
	}

	@Override
	public void update(Coupon coupon) {
		couponMapper.update(coupon);
	}

	@Override
	public void delete(Integer id) {
		couponMapper.delete(id);
	}

	@Override
	public Integer queryCouponCount(CouponDto couponDto) {
		return couponMapper.queryCouponCount(couponDto);
	}

	@Override
	public List<Coupon> queryCouponList(CouponDto couponDto) {
		return couponMapper.queryCouponList(couponDto);
	}
}
