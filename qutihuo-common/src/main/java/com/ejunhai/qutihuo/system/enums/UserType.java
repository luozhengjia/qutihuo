package com.ejunhai.qutihuo.system.enums;

/**
 * 用户类型
 * 
 * @author parcel
 */
public enum UserType {

	ssa(1, "超级系统管理员"), sa(2, "系统管理员"), sma(3, "商户户主"), ma(4, "商户管理员");

	private UserType(Integer flag, String title) {
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

	public static UserType get(Integer flag) {
		for (UserType temp : UserType.values()) {
			if (temp.flag.equals(flag)) {
				return temp;
			}
		}
		return null;
	}
}
