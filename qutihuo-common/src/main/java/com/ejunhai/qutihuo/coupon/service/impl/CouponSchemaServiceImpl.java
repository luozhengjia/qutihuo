package com.ejunhai.qutihuo.coupon.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.coupon.dao.CouponSchemaMapper;
import com.ejunhai.qutihuo.coupon.model.CouponSchema;
import com.ejunhai.qutihuo.coupon.service.CouponSchemaService;

/**
 * CouponSchema Service 实现类
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:40:05
 * 
 */
@Service("couponSchemaService")
public class CouponSchemaServiceImpl implements CouponSchemaService {

    @Resource
    private CouponSchemaMapper couponSchemaMapper;

    @Override
    public CouponSchema read(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(CouponSchema couponSchema) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(CouponSchema couponSchema) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Integer queryCouponSchemaCount(CouponSchema couponSchema) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer queryCouponSchemaList(CouponSchema couponSchema) {
        // TODO Auto-generated method stub
        return null;
    }

}
