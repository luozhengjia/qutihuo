package com.ejunhai.qutihuo.common.enums;

/**
 * 是否上线
 * 
 * @author parcel
 */
public enum OnlineStatus {

	offline(0), online(1);

	private OnlineStatus(Integer status) {

		this.status = status;
	}

	private Integer status;

	public static OnlineStatus getStatus(Integer i) {

		if (i == null) {
			return null;
		}
		for (OnlineStatus e : OnlineStatus.values()) {
			if (e.status.equals(i)) {
				return e;
			}
		}
		return null;
	}

	public Integer getValue() {
		return status;
	}

}
