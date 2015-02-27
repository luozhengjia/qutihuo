package com.ejunhai.qutihuo.order.dao;

import java.util.List;

import com.ejunhai.qutihuo.order.model.CodingRule;

/**
 * CodingRuleMapper
 * 商家订单号配置表
 *
 * @author parcel
 * 
 * @date 2015-02-27 11:21:48
 * 
 */
public interface CodingRuleMapper {

    /**
     * 根据Id获取CodingRule
     * 
     * @param id
     * @return
     */
    public CodingRule read(Integer id);
    
    /**
     * 新增CodingRule
     * 
     * @param codingRule
     */
    public void insert(CodingRule codingRule);

    /**
     * 更新CodingRule
     * 
     * @param codingRule
     */
    public void update(CodingRule codingRule);
    
    /**
     * 删除CodingRule
     * 
     * @param id
     */
    public void delete(Integer id);
    
    /**
     * 查询CodingRule数量
     * 
     * @param codingRule
     * @return
     */
    public Integer queryCodingRuleCount(CodingRule codingRule);
    
    /**
     * 查询CodingRule列表
     * 
     * @param codingRule
     * @return
     */
    public List<CodingRule> queryCodingRuleList(CodingRule codingRule);
    
    
    /**
     * 获取订单号
     * @return
     */
    public String getOrderNo();

}
