package com.ejunhai.qutihuo.order.enums;

/**
 * 订单来源 1 后台下单 2 pc下单 3 wap下单
 * 
 * @author parcel
 */
public enum OrderSource {

	MANUAL(1, "后台下单"), PC(2, "PC下单"), PHONE(3, "手机下单");

	private OrderSource(Integer flag, String title) {
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

	public static OrderSource get(Integer flag) {
		for (OrderSource temp : OrderSource.values()) {
			if (temp.flag.equals(flag)) {
				return temp;
			}
		}
		return null;
	}
}
