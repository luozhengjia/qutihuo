package com.ejunhai.qutihuo.order.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 物流公司表
 *
 * @author parcel
 * @date 2015-01-28 09:07:46
 */
public class LogisticsCompany implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -7823037327507731797L;

	/**
     * 物流公司ID
     */
    private Integer id;
    
    /**
     * 物流公司名称
     */
    private String lcName;
    
    /**
     * 物流公司编码
     */
    private String lcCode;
    
    /**
     * 快递模板
     */
    private String expressTemplate;
    
    /**
     * 联系人
     */
    private String contact;
    
    /**
     * 联系电话
     */
    private String telephone;
    
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
    
    public String getLcName() {
        return lcName;
    }
        
    public void setLcName(String lcName) {
        this.lcName = lcName;
    }
    
    public String getLcCode() {
        return lcCode;
    }
        
    public void setLcCode(String lcCode) {
        this.lcCode = lcCode;
    }
    
    public String getExpressTemplate() {
        return expressTemplate;
    }
        
    public void setExpressTemplate(String expressTemplate) {
        this.expressTemplate = expressTemplate;
    }
    
    public String getContact() {
        return contact;
    }
        
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public String getTelephone() {
        return telephone;
    }
        
    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
