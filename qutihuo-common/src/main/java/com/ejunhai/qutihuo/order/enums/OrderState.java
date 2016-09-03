package com.ejunhai.qutihuo.order.enums;

/**
 * 订单状态
 * 
 * @author parcel
 */
public enum OrderState {

	CANCEL(0,"取消"),NO_DELIVER(1, "未发货"), DELIVERD(2, "已发货");

	private OrderState(Integer flag, String title) {
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

	public static OrderState get(Integer flag) {
		for (OrderState temp : OrderState.values()) {
			if (temp.flag.equals(flag)) {
				return temp;
			}
		}
		return null;
	}
}
