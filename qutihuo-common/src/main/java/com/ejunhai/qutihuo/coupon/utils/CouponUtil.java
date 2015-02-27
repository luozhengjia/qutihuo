package com.ejunhai.qutihuo.coupon.utils;

import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;

public final class CouponUtil {

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
