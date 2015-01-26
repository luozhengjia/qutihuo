package com.ejunhai.qutihuo.system.enums;

/**
 * 操作类型
 * 
 * @author parcel
 */
public enum ActionType {

	catalog(1, "目录"), menu(2, "菜单"), operation(3, "操作");

	private ActionType(Integer flag, String title) {
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

	public static ActionType get(Integer flag) {
		for (ActionType temp : ActionType.values()) {
			if (temp.flag.equals(flag)) {
				return temp;
			}
		}
		return null;
	}
}
