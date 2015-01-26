package com.ejunhai.qutihuo.system.model;

import java.io.Serializable;

/**
 * 系统配置表
 * 
 * @author parcel
 * @date 2014-12-10 21:18:30
 */
public class SystemConfig implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 9096594100505693449L;

    /**
     * 
     */
    private Integer id;

    /**
     * 名称
     */
    private String configName;

    /**
     * 配置项key
     */
    private String configKey;

    /**
     * 配置项值
     */
    private String configValue;

    /**
     * 备注
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
