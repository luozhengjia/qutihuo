package com.ejunhai.qutihuo.system.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 系统用户表
 * 
 * @author parcel
 * @date 2014-12-10 21:18:30
 */
public class SystemUser implements Serializable {

    private static final long serialVersionUID = 8603084533895399522L;

    /**
     * 用户ID
     */
    private Integer id;

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
    private String telephone;

    /**
     * 用户类型 1 超级系统管理员 2 系统管理员 3 商户户主 4 商户管理员
     */
    private Integer userType;

    /**
     * 商户ID
     */
    private Integer merchantId;

    /**
     * 用户头像
     */
    private String pictureUrl;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 用户角色IDS
     */
    private String roleIds;

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
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
