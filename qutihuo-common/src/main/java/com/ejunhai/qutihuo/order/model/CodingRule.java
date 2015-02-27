package com.ejunhai.qutihuo.order.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 商家订单号配置表
 *
 * @author parcel
 * @date 2015-02-27 11:21:48
 */
public class CodingRule implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6143104773530168399L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 商家id，该id为业务系统预先知道的
	 */
	private Integer merchantId;

	/**
	 * 商家名称
	 */
	private String merchantName;

	/**
	 * 前缀
	 */
	private String prefix;

	/**
	 * 当前序号 
	 */
	private Integer currentSerialNo;

	/**
	 * 序号长度
	 */
	private Integer serialNoLength;

	/**
	 * 重置模式(0:永不重置 1:按天重置 2:按月重置 3:按年重置)
	 */
	private Integer resetMode;

	/**
	 * 是否允许断号，1表示允许，0表示不允许，允许时会在应用程序中缓存10个

	序号，不允许时，每次都从数据库请求，允许断号

	性能更好，但如果服务器重启，可能缓存中没用过的序号丢失
	 */
	private Integer allowBreakNo;

	/**
	 * 重置时间，在判断是否需要重置时有用
	 */
	private Timestamp resetTime;

	/**
	 * 备注
	 */
	private String remarks;

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

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Integer getCurrentSerialNo() {
		return currentSerialNo;
	}

	public void setCurrentSerialNo(Integer currentSerialNo) {
		this.currentSerialNo = currentSerialNo;
	}

	public Integer getSerialNoLength() {
		return serialNoLength;
	}

	public void setSerialNoLength(Integer serialNoLength) {
		this.serialNoLength = serialNoLength;
	}

	public Integer getResetMode() {
		return resetMode;
	}

	public void setResetMode(Integer resetMode) {
		this.resetMode = resetMode;
	}

	public Integer getAllowBreakNo() {
		return allowBreakNo;
	}

	public void setAllowBreakNo(Integer allowBreakNo) {
		this.allowBreakNo = allowBreakNo;
	}

	public Timestamp getResetTime() {
		return resetTime;
	}

	public void setResetTime(Timestamp resetTime) {
		this.resetTime = resetTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
