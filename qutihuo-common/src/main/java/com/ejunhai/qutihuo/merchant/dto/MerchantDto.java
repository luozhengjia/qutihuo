package com.ejunhai.qutihuo.merchant.dto;

import com.ejunhai.qutihuo.merchant.model.Merchant;

public class MerchantDto extends Merchant {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 密码
	 */
	private String passwd;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 用户头像
	 */
	private String pictureUrl;

	/**
	 * 用户角色IDS
	 */
	private String roleIds;

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

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

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
