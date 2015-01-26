package com.ejunhai.qutihuo.common.enums;

/**
 * 是否已删除
 * 
 * @author parcel
 */
public enum DeleteStatus {

	deleted(0), not_deleted(1);

	private DeleteStatus(Integer status) {

		this.status = status;
	}

	private Integer status;

	public static DeleteStatus getStatus(Integer i) {

		if (i == null) {
			return null;
		}
		for (DeleteStatus e : DeleteStatus.values()) {
			if (e.status.equals(i)) {
				return e;
			}
		}
		return null;
	}

	public Integer getValue() {
		return status;
	}

}
