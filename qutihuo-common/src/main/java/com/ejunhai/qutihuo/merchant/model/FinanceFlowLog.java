package com.ejunhai.qutihuo.merchant.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 财务流水日志表
 *
 * @author parcel
 * @date 2014-12-10 21:42:31
 */
public class FinanceFlowLog implements Serializable {
    
    private static final long serialVersionUID = -231191767402265616L;

    /**
     * 
     */
    private Integer id;
    
    /**
     * 用户ID
     */
    private Integer userId;
    
    /**
     * 操作类型
     */
    private Integer operType;
    
    /**
     * 商户ID
     */
    private Integer merchantId;
    
    /**
     * 操作描述
     */
    private String operation;
    
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
    
    public Integer getUserId() {
        return userId;
    }
        
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public Integer getOperType() {
        return operType;
    }
        
    public void setOperType(Integer operType) {
        this.operType = operType;
    }
    
    public Integer getMerchantId() {
        return merchantId;
    }
        
    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }
    
    public String getOperation() {
        return operation;
    }
        
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    public Timestamp getCreateTime() {
        return createTime;
    }
        
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    
}
