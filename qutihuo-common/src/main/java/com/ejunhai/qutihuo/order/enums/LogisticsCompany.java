package com.ejunhai.qutihuo.order.enums;

/**
 * 物流公司
 * 
 * @author parcel
 */
public enum LogisticsCompany {

	shunfeng(1, "顺丰快递"), zhongtong(2, "中通快递");

	private LogisticsCompany(Integer flag, String title) {
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

	public static LogisticsCompany get(Integer flag) {
		for (LogisticsCompany temp : LogisticsCompany.values()) {
			if (temp.flag.equals(flag)) {
				return temp;
			}
		}
		return null;
	}
}
