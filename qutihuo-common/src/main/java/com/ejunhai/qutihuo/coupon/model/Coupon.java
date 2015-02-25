package com.ejunhai.qutihuo.coupon.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 优惠券
 * 
 * @author parcel
 * @date 2014-12-10 21:40:05
 */
public class Coupon implements Serializable {

	private static final long serialVersionUID = 8661795275004718350L;

	/**
     * 
     */
	private Integer id;

	/**
	 * 优惠券码
	 */
	private String couponNumber;

	/**
	 * 优惠券密码
	 */
	private String couponPassword;

	/**
	 * 优惠券模板ID
	 */
	private Integer couponSchemaId;

	/**
	 * 商户ID
	 */
	private Integer merchantId;

	/**
	 * 状态
	 */
	private Integer state;

	/**
	 * 开始日期
	 */
	private Timestamp useStartdate;

	/**
	 * 结束日期
	 */
	private Timestamp useEnddate;

	/**
	 * 使用订单号
	 */
	private String orderNumber;

	/**
	 * 使用时间
	 */
	private Timestamp useTime;

	/**
	 * 生成时间
	 */
	private Timestamp createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCouponNumber() {
		return couponNumber;
	}

	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
	}

	public String getCouponPassword() {
		return couponPassword;
	}

	public void setCouponPassword(String couponPassword) {
		this.couponPassword = couponPassword;
	}

	public Integer getCouponSchemaId() {
		return couponSchemaId;
	}

	public void setCouponSchemaId(Integer couponSchemaId) {
		this.couponSchemaId = couponSchemaId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Timestamp getUseStartdate() {
		return useStartdate;
	}

	public void setUseStartdate(Timestamp useStartdate) {
		this.useStartdate = useStartdate;
	}

	public Timestamp getUseEnddate() {
		return useEnddate;
	}

	public void setUseEnddate(Timestamp useEnddate) {
		this.useEnddate = useEnddate;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Timestamp getUseTime() {
		return useTime;
	}

	public void setUseTime(Timestamp useTime) {
		this.useTime = useTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

}
