package com.ejunhai.qutihuo.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 补货单表
 * 
 * @author parcel
 * @date 2015-05-31 17:38:52
 */
public class OrderRepl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2223159409051017839L;

	/**
     * 
     */
	private Integer id;

	/**
	 * 补货订单号
	 */
	private String orderReplNo;

	/**
	 * 主订单号
	 */
	private String orderMainNo;

	/**
	 * 商户ID
	 */
	private Integer merchantId;

	/**
	 * 订单状态
	 */
	private Integer state;

	/**
	 * 订单金额
	 */
	private BigDecimal payAmount;

	/**
	 * 预定日期
	 */
	private String orderDate;

	/**
	 * 收货人
	 */
	private String consignee;

	/**
	 * 联系电话
	 */
	private String mobilePhone;

	/**
	 * 省编码
	 */
	private String provinceCode;

	/**
	 * 市编码
	 */
	private String cityCode;

	/**
	 * 区编码
	 */
	private String areaCode;

	/**
	 * 省市区
	 */
	private String provinceCityArea;

	/**
	 * 详细地址
	 */
	private String detailAddress;

	/**
	 * 物流公司
	 */
	private String logisticsCompany;

	/**
	 * 快递单号
	 */
	private String expressOrderNo;

	/**
	 * 发货时间
	 */
	private Timestamp deliverTime;

	/**
	 * 是否已发短信
	 */
	private Integer isSendSms;

	/**
	 * 是否已打印快递单
	 */
	private Integer isPrintExpress;

	/**
	 * 订单来源 1 后台下单 2 pc下单 3 wap下单
	 */
	private Integer source;

	/**
	 * 订单备注
	 */
	private String remark;

	/**
	 * 下单时间
	 */
	private Timestamp createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderReplNo() {
		return orderReplNo;
	}

	public void setOrderReplNo(String orderReplNo) {
		this.orderReplNo = orderReplNo;
	}

	public String getOrderMainNo() {
		return orderMainNo;
	}

	public void setOrderMainNo(String orderMainNo) {
		this.orderMainNo = orderMainNo;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getProvinceCityArea() {
		return provinceCityArea;
	}

	public void setProvinceCityArea(String provinceCityArea) {
		this.provinceCityArea = provinceCityArea;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getLogisticsCompany() {
		return logisticsCompany;
	}

	public void setLogisticsCompany(String logisticsCompany) {
		this.logisticsCompany = logisticsCompany;
	}

	public String getExpressOrderNo() {
		return expressOrderNo;
	}

	public void setExpressOrderNo(String expressOrderNo) {
		this.expressOrderNo = expressOrderNo;
	}

	public Timestamp getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(Timestamp deliverTime) {
		this.deliverTime = deliverTime;
	}

	public Integer getIsSendSms() {
		return isSendSms;
	}

	public void setIsSendSms(Integer isSendSms) {
		this.isSendSms = isSendSms;
	}

	public Integer getIsPrintExpress() {
		return isPrintExpress;
	}

	public void setIsPrintExpress(Integer isPrintExpress) {
		this.isPrintExpress = isPrintExpress;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
