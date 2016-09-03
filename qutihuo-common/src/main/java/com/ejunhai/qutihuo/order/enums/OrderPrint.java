package com.ejunhai.qutihuo.order.enums;

/**
 * 订单打印状态
 * 
 * @author parcel
 */
public enum OrderPrint {

	NO_PRINT(0,"未打印"),PRINTED(1, "已打印");

	private OrderPrint(Integer flag, String title) {
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

	public static OrderPrint get(Integer flag) {
		for (OrderPrint temp : OrderPrint.values()) {
			if (temp.flag.equals(flag)) {
				return temp;
			}
		}
		return null;
	}
}
