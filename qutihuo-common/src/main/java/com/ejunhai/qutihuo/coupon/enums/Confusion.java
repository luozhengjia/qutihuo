package com.ejunhai.qutihuo.coupon.enums;

/**
 * 礼品卡密码是否扰乱
 * 
 * @author parcel
 */
public enum Confusion {

	no(1, "未扰乱"), yes(2, "已扰乱");

	private Confusion(Integer flag, String title) {
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

	public static Confusion get(Integer flag) {
		for (Confusion temp : Confusion.values()) {
			if (temp.flag.equals(flag)) {
				return temp;
			}
		}
		return null;
	}
}
