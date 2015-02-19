package com.ejunhai.qutihuo.coupon.service.impl;

import java.util.List;

import javax.annotation.Resource;

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
}
