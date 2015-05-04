package com.ejunhai.qutihuo.aftersale.dto;

import com.ejunhai.qutihuo.aftersale.model.AfterSaleRequ;

/**
 * 售后申请表
 * 
 * @author parcel
 * @date 2015-04-09 20:22:19
 */
public class AfterSaleRequDto extends AfterSaleRequ {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6040873197639287376L;

	/**
	 * 页码
	 */
	private Integer pageNo;

	/**
	 * 分页开始位置
	 */
	private int offset;

	/**
	 * 分页大小
	 */
	private int pageSize;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
