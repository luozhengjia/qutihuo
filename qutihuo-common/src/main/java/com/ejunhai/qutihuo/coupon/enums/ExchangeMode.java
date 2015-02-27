package com.ejunhai.qutihuo.coupon.enums;

/**
 * 兑换模式
 * 
 * @author parcel
 */
public enum ExchangeMode {

	single(1, "简单"), multi(2, "多选一");

	private ExchangeMode(Integer flag, String title) {
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

	public static ExchangeMode get(Integer flag) {
		for (ExchangeMode temp : ExchangeMode.values()) {
			if (temp.flag.equals(flag)) {
				return temp;
			}
		}
		return null;
	}
}
