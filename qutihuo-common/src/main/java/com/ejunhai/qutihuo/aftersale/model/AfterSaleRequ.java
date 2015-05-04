package com.ejunhai.qutihuo.aftersale.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 售后申请表
 * 
 * @author parcel
 * @date 2015-04-12 21:36:37
 */
public class AfterSaleRequ implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 551457775828792143L;

	/**
     * 
     */
	private Integer id;

	/**
	 * 商户ID
	 */
	private Integer merchantId;

	/**
	 * 主订单号
	 */
	private String orderMainNo;

	/**
	 * 图片路径
	 */
	private String urls;

	/**
	 * 申请描述
	 */
	private String description;

	/**
	 * 状态：未处理 已拒绝 已下补货单
	 */
	private Integer state;

	/**
	 * 创建时间
	 */
	private Timestamp createTime;

	/**
	 * 处理时间
	 */
	private Timestamp dealTime;

	/**
	 * 处理说明
	 */
	private String dealInfo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getOrderMainNo() {
		return orderMainNo;
	}

	public void setOrderMainNo(String orderMainNo) {
		this.orderMainNo = orderMainNo;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getDealTime() {
		return dealTime;
	}

	public void setDealTime(Timestamp dealTime) {
		this.dealTime = dealTime;
	}

	public String getDealInfo() {
		return dealInfo;
	}

	public void setDealInfo(String dealInfo) {
		this.dealInfo = dealInfo;
	}

}
