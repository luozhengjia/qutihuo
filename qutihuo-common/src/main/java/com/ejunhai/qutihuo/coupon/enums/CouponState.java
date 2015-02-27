package com.ejunhai.qutihuo.coupon.enums;

/**
 * 礼品卡状态
 * 
 * @author parcel
 */
public enum CouponState {

	notactivated(0, "未激活"), unused(1, "未使用"), used(2, "已使用"), discard(3, "已作废"), expired(4, "已过期");

	private CouponState(Integer flag, String title) {
		this.flag = flag;
		this.title = title;
	}

	private Integer flag;

	private String title;

	public Integer getValue() {
		return flag;
	}

	public String getTitle() {
		return title;
	}

	public static CouponState get(Integer flag) {
		for (CouponState temp : CouponState.values()) {
			if (temp.flag.equals(flag)) {
				return temp;
			}
		}
		return null;
	}
}
