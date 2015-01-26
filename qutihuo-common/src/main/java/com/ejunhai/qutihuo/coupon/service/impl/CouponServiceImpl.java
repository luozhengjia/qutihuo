package com.ejunhai.qutihuo.coupon.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.coupon.dao.CouponMapper;
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(Coupon coupon) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Coupon coupon) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Integer queryCouponCount(Coupon coupon) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer queryCouponList(Coupon coupon) {
        // TODO Auto-generated method stub
        return null;
    }

}
