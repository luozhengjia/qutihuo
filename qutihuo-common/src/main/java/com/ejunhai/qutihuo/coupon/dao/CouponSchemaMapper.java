package com.ejunhai.qutihuo.coupon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ejunhai.qutihuo.coupon.dto.CouponSchemaDto;
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
	 * @param couponSchemaDto
	 * @return
	 */
	public Integer queryCouponSchemaCount(CouponSchemaDto couponSchemaDto);

	/**
	 * 查询CouponSchema列表
	 * 
	 * @param couponSchemaDto
	 * @return
	 */
	public List<CouponSchema> queryCouponSchemaList(CouponSchemaDto couponSchemaDto);

	/**
	 * 根据ID获取CouponSchema列表
	 * 
	 * @param ids
	 * @return
	 */
	public List<CouponSchema> getCouponSchemaListByIds(List<Integer> ids);

	/**
	 * 更新券使用数
	 * 
	 * @param couponSchemaId
	 * @param useNum
	 */
	public void updateCouponUseNum(@Param("couponSchemaId") Integer couponSchemaId, @Param("hasUseNum") Integer hasUseNum);

	/**
	 * 根据订单号获取优惠券模板列表
	 * 
	 * @param orderNoList
	 * @return
	 */
	public List<CouponSchema> getCouponSchemaListByOrderNos(List<String> orderNoList);
}
