package com.ejunhai.qutihuo.system.enums;

/**
 * 角色类型
 * 
 * @author parcel
 */
public enum RoleType {

	ssa(1, "超级系统管理员"), sma(2, "商户户主"), sa(3, "商户管理员");

	private RoleType(Integer flag, String title) {
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

	public static RoleType get(Integer flag) {
		for (RoleType temp : RoleType.values()) {
			if (temp.flag.equals(flag)) {
				return temp;
			}
		}
		return null;
	}
}
