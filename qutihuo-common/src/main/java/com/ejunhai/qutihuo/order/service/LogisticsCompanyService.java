package com.ejunhai.qutihuo.order.service;

import java.util.List;

import com.ejunhai.qutihuo.order.model.LogisticsCompany;

/**
 * 
 * LogisticsCompany Service 接口
 * 
 * @author parcel
 * 
 * @date 2015-01-28 09:07:46
 * 
 */
public interface LogisticsCompanyService {

    /**
     * 根据Id获取LogisticsCompany
     * 
     * @param id
     * @return
     */
    public LogisticsCompany read(Integer id);
    
    /**
     * 新增LogisticsCompany
     * 
     * @param logisticsCompany
     */
    public void insert(LogisticsCompany logisticsCompany);

    /**
     * 更新LogisticsCompany
     * 
     * @param logisticsCompany
     */
    public void update(LogisticsCompany logisticsCompany);
    
    /**
     * 删除LogisticsCompany
     * 
     * @param id
     */
    public void delete(Integer id);
    
    /**
     * 查询LogisticsCompany数量
     * 
     * @param logisticsCompany
     * @return
     */
    public Integer queryLogisticsCompanyCount(LogisticsCompany logisticsCompany);
    
    /**
     * 查询LogisticsCompany列表
     * 
     * @param logisticsCompany
     * @return
     */
    public List<LogisticsCompany> queryLogisticsCompanyList(LogisticsCompany logisticsCompany);
    
    
    /**
     * 查询LogisticsCompany
     * 
     * @param logisticsCompany
     * @return
     */
    public LogisticsCompany findLogisticsCompany(LogisticsCompany logisticsCompany);

}
