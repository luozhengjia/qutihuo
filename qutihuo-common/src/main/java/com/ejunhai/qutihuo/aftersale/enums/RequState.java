package com.ejunhai.qutihuo.aftersale.enums;

/**
 * 售后请求状态
 * 
 * @author parcel
 */
public enum RequState {

	wait(0, "待处理"), refuse(1, "已拒绝"), deal(2, "已补货");

	private RequState(Integer flag, String title) {
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

	public static RequState get(Integer flag) {
		for (RequState temp : RequState.values()) {
			if (temp.flag.equals(flag)) {
				return temp;
			}
		}
		return null;
	}
}
