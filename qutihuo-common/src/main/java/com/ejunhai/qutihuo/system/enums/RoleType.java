package com.ejunhai.qutihuo.system.enums;

/**
 * 角色类型
 * 
 * @author parcel
 */
public enum RoleType {
	
	system(1, "系统默认"), custom(2, "自定义");

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
