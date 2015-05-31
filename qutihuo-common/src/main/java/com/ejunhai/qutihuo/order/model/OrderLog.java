package com.ejunhai.qutihuo.order.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 订单日志表
 * 
 * @author parcel
 * @date 2015-05-31 15:43:53
 */
public class OrderLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6894988289925767903L;

	/**
     * 
     */
	private String id;

	/**
     * 
     */
	private String orderNo;

	/**
     * 
     */
	private Integer logType;

	/**
	 * 操作用户名，前台或后台登录用户名
	 */
	private String operateUser;

	/**
     * 
     */
	private String remark;

	/**
     * 
     */
	private Timestamp createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public String getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
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
