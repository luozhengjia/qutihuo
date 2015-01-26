package com.ejunhai.qutihuo.coupon.dao;

import java.util.List;

import com.ejunhai.qutihuo.coupon.model.Coupon;

/**
 * CouponMapper
 * 优惠券
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
    public Integer queryCouponCount(Coupon coupon);
    
    /**
     * 查询Coupon列表
     * 
     * @param coupon
     * @return
     */
    public List<Coupon> queryCouponList(Coupon coupon);

}
