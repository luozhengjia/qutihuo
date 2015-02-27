package com.ejunhai.qutihuo.coupon.enums;

/**
 * 礼品卡初始状态
 * 
 * @author parcel
 */
public enum InitActivated {

	no(1, "未激活"), yes(2, "已激活");

	private InitActivated(Integer flag, String title) {
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

	public static InitActivated get(Integer flag) {
		for (InitActivated temp : InitActivated.values()) {
			if (temp.flag.equals(flag)) {
				return temp;
			}
		}
		return null;
	}
}
