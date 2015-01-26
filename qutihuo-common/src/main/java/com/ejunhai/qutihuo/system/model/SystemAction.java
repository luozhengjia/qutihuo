package com.ejunhai.qutihuo.system.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 功能表
 * 
 * @author parcel
 * @date 2014-12-10 21:18:30
 */
public class SystemAction implements Serializable {

	private static final long serialVersionUID = -9117726492427882293L;

	/**
     * 
     */
	private Integer id;

	/**
	 * 操作名
	 */
	private String actionName;

	/**
	 * 节点类型 1 目录 2 菜单 3 操作
	 */
	private Integer actionType;

	/**
	 * 父节点ID
	 */
	private Integer parentId;

	/**
	 * url
	 */
	private String url;

	/**
	 * iconCss
	 */
	private String iconCss;

	/**
	 * 操作描述
	 */
	private String remark;

	/**
	 * 权重
	 */
	private Integer weight;

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

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIconCss() {
		return iconCss;
	}

	public void setIconCss(String iconCss) {
		this.iconCss = iconCss;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
