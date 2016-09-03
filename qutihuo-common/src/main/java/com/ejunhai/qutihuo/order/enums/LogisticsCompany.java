package com.ejunhai.qutihuo.order.enums;

/**
 * 物流公司
 * 
 * @author parcel
 */
public enum LogisticsCompany {

	shunfeng(1, "顺丰快递", "shunfeng"), zhongtong(2, "中通快递", "zhongtong");

	private LogisticsCompany(Integer flag, String title, String qcode) {
		this.flag = flag;
		this.title = title;
		this.qcode = qcode;
	}

	private Integer flag;

	private String title;

	private String qcode;

	public Integer getValue() {
		return flag;
	}

	public String getTitle() {
		return title;
	}

	public String getQcode() {
		return qcode;
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
