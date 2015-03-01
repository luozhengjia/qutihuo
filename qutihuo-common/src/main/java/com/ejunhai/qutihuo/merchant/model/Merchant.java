package com.ejunhai.qutihuo.merchant.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 商户表
 * 
 * @author parcel
 * @date 2014-12-10 21:42:31
 */
public class Merchant implements Serializable {

	private static final long serialVersionUID = -8038668664301516934L;

	/**
	 * 商户ID
	 */
	private Integer id;

	/**
	 * 商户名称
	 */
	private String merchantName;

	/**
	 * 主营业务
	 */
	private String businessLine;

	/**
	 * 商户等级
	 */
	private Integer merchantLevel;

	/**
	 * 开通时间
	 */
	private Timestamp openTime;

	/**
	 * 到期时间
	 */
	private Timestamp expireTime;

	/**
	 * 可用短信数量
	 */
	private Integer availableSmsNum;

	/**
	 * 备案号
	 */
	private String recordNumber;

	/**
	 * 联系人
	 */
	private String contacts;

	/**
	 * 联系电话
	 */
	private String telephone;

	/**
	 * 联系地址
	 */
	private String address;

	/**
	 * 创建时间
	 */
	private Timestamp createTime;

	/**
	 * 更新时间
	 */
	private Timestamp updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getBusinessLine() {
		return businessLine;
	}

	public void setBusinessLine(String businessLine) {
		this.businessLine = businessLine;
	}

	public Integer getMerchantLevel() {
		return merchantLevel;
	}

	public void setMerchantLevel(Integer merchantLevel) {
		this.merchantLevel = merchantLevel;
	}

	public Timestamp getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Timestamp openTime) {
		this.openTime = openTime;
	}

	public Timestamp getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Timestamp expireTime) {
		this.expireTime = expireTime;
	}

	public Integer getAvailableSmsNum() {
		return availableSmsNum;
	}

	public void setAvailableSmsNum(Integer availableSmsNum) {
		this.availableSmsNum = availableSmsNum;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}
