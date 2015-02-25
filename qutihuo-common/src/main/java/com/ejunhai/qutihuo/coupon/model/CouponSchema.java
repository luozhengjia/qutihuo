package com.ejunhai.qutihuo.coupon.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 优惠券方案
 * 
 * @author parcel
 * @date 2014-12-10 21:40:05
 */
public class CouponSchema implements Serializable {

	private static final long serialVersionUID = -1270482035564504283L;

	/**
     * 
     */
	private Integer id;

	/**
	 * 优惠券名称
	 */
	private String couponName;

	/**
	 * icon地址
	 */
	private String iconUrl;

	/**
	 * 商户ID
	 */
	private Integer merchantId;

	/**
	 * 兑换模式 1 无 2 多选一
	 */
	private Integer exchangeMode;

	/**
	 * 兑换选项 json格式，后台设置
	 */
	private String exchangeItem;

	/**
	 * 开始时间
	 */
	private Date useStartdate;

	/**
	 * 结束时间
	 */
	private Date useEnddate;

	/**
	 * 是否初始化激活
	 */
	private Integer initActivate;

	/**
	 * 面值
	 */
	private BigDecimal parValue;

	/**
	 * 发行总量
	 */
	private Integer issueAmount;

	/**
	 * 已发行量
	 */
	private Integer hasIssueNum;

	/**
	 * 已使用量
	 */
	private Integer hasUseNum;

	/**
	 * 是否已扰乱
	 */
	private Integer hasConfusion;

	/**
	 * 日限兑换量
	 */
	private Integer dayLimitNum;

	/**
	 * 提前预约天数
	 */
	private Integer frontDayNum;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建时间
	 */
	private Timestamp createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getExchangeMode() {
		return exchangeMode;
	}

	public void setExchangeMode(Integer exchangeMode) {
		this.exchangeMode = exchangeMode;
	}

	public String getExchangeItem() {
		return exchangeItem;
	}

	public void setExchangeItem(String exchangeItem) {
		this.exchangeItem = exchangeItem;
	}

	public Date getUseStartdate() {
		return useStartdate;
	}

	public void setUseStartdate(Date useStartdate) {
		this.useStartdate = useStartdate;
	}

	public Date getUseEnddate() {
		return useEnddate;
	}

	public void setUseEnddate(Date useEnddate) {
		this.useEnddate = useEnddate;
	}

	public Integer getInitActivate() {
		return initActivate;
	}

	public void setInitActivate(Integer initActivate) {
		this.initActivate = initActivate;
	}

	public BigDecimal getParValue() {
		return parValue;
	}

	public void setParValue(BigDecimal parValue) {
		this.parValue = parValue;
	}

	public Integer getIssueAmount() {
		return issueAmount;
	}

	public void setIssueAmount(Integer issueAmount) {
		this.issueAmount = issueAmount;
	}

	public Integer getHasIssueNum() {
		return hasIssueNum;
	}

	public void setHasIssueNum(Integer hasIssueNum) {
		this.hasIssueNum = hasIssueNum;
	}

	public Integer getHasUseNum() {
		return hasUseNum;
	}

	public void setHasUseNum(Integer hasUseNum) {
		this.hasUseNum = hasUseNum;
	}

	public Integer getHasConfusion() {
		return hasConfusion;
	}

	public void setHasConfusion(Integer hasConfusion) {
		this.hasConfusion = hasConfusion;
	}

	public Integer getDayLimitNum() {
		return dayLimitNum;
	}

	public void setDayLimitNum(Integer dayLimitNum) {
		this.dayLimitNum = dayLimitNum;
	}

	public Integer getFrontDayNum() {
		return frontDayNum;
	}

	public void setFrontDayNum(Integer frontDayNum) {
		this.frontDayNum = frontDayNum;
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
