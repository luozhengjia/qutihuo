package com.ejunhai.qutihuo.coupon.dao;

import java.util.List;

import com.ejunhai.qutihuo.coupon.model.CouponSchema;

/**
 * CouponSchemaMapper 优惠券方案
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:40:05
 * 
 */
public interface CouponSchemaMapper {

    /**
     * 根据Id获取CouponSchema
     * 
     * @param id
     * @return
     */
    public CouponSchema read(Integer id);

    /**
     * 新增CouponSchema
     * 
     * @param couponSchema
     */
    public void insert(CouponSchema couponSchema);

    /**
     * 更新CouponSchema
     * 
     * @param couponSchema
     */
    public void update(CouponSchema couponSchema);

    /**
     * 删除CouponSchema
     * 
     * @param id
     */
    public void delete(Integer id);

    /**
     * 查询CouponSchema数量
     * 
     * @param couponSchema
     * @return
     */
    public Integer queryCouponSchemaCount(CouponSchema couponSchema);

    /**
     * 查询CouponSchema列表
     * 
     * @param couponSchema
     * @return
     */
    public List<CouponSchema> queryCouponSchemaList(CouponSchema couponSchema);

}
