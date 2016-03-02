package com.ejunhai.qutihuo.coupon.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.coupon.dao.CouponSchemaMapper;
import com.ejunhai.qutihuo.coupon.dto.CouponSchemaDto;
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
        return couponSchemaMapper.read(id);
    }

    @Override
    public void insert(CouponSchema couponSchema) {
        couponSchema.setCreateTime(new Timestamp(System.currentTimeMillis()));
        couponSchemaMapper.insert(couponSchema);
    }

    @Override
    public void update(CouponSchema couponSchema) {
        couponSchemaMapper.update(couponSchema);
    }

    @Override
    public void delete(Integer id) {
        couponSchemaMapper.delete(id);
    }

    @Override
    public Integer queryCouponSchemaCount(CouponSchemaDto couponSchemaDto) {
        return couponSchemaMapper.queryCouponSchemaCount(couponSchemaDto);
    }

    @Override
    public List<CouponSchema> queryCouponSchemaList(CouponSchemaDto couponSchemaDto) {
        return couponSchemaMapper.queryCouponSchemaList(couponSchemaDto);
    }

    @Override
    public List<CouponSchema> getCouponSchemaListByIds(List<Integer> ids) {
        return couponSchemaMapper.getCouponSchemaListByIds(ids);
    }

    @Override
    public void updateCouponUseNum(Integer couponSchemaId, Integer hasUseNum) {
        couponSchemaMapper.updateCouponUseNum(couponSchemaId, hasUseNum);
    }

    @Override
    public List<CouponSchema> getCouponSchemaListByOrderNos(List<String> orderNoList) {
        if (CollectionUtils.isEmpty(orderNoList)) {
            return new ArrayList<CouponSchema>();
        }
        return couponSchemaMapper.getCouponSchemaListByOrderNos(orderNoList);
    }

}
