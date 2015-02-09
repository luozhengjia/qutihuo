package com.ejunhai.qutihuo.system.enums;

/**
 * 用户状态
 * 
 * @author parcel
 */
public enum UserState {

	normal(1, "正常"), lock(2, "锁定");

	private UserState(Integer flag, String title) {
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

	public static UserState get(Integer flag) {
		for (UserState temp : UserState.values()) {
			if (temp.flag.equals(flag)) {
				return temp;
			}
		}
		return null;
	}
}
