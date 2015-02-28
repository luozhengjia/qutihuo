package com.ejunhai.qutihuo.coupon.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.RandomStringUtils;

import com.ejunhai.qutihuo.coupon.model.Coupon;
import com.ejunhai.qutihuo.coupon.model.CouponSchema;

public final class CouponUtil {

	/**
	 * 获取礼品卡方案ID列表
	 * 
	 * @param couponList
	 * @return
	 */
	public static List<Integer> getCouponSchemaIdList(List<Coupon> couponList) {
		Set<Integer> couponSchemaIdSet = new HashSet<Integer>();
		if (CollectionUtils.isEmpty(couponList)) {
			return new ArrayList<Integer>(couponSchemaIdSet);
		}

		for (Coupon coupon : couponList) {
			couponSchemaIdSet.add(coupon.getCouponSchemaId());
		}

		return new ArrayList<Integer>(couponSchemaIdSet);
	}

	public static Map<String, CouponSchema> getCouponSchemaMap(List<CouponSchema> couponSchemaList) {
		Map<String, CouponSchema> couponSchemaMap = new HashMap<String, CouponSchema>();
		if (CollectionUtils.isEmpty(couponSchemaList)) {
			return couponSchemaMap;
		}

		for (CouponSchema couponSchema : couponSchemaList) {
			couponSchemaMap.put(String.valueOf(couponSchema.getId()), couponSchema);
		}

		return couponSchemaMap;
	}

	/**
	 * 生成优惠券编码
	 * 
	 * @return
	 */
	public static String generateCouponNumber() {
		return RandomStringUtils.randomNumeric(13);
	}

	/**
	 * 生成优惠券密码
	 * 
	 * @return
	 */
	public static String generateCouponPassword() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().toUpperCase().replaceAll("-", "").substring(24);
	}

}
