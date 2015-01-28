package com.ejunhai.qutihuo.order.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ejunhai.qutihuo.order.model.LogisticsCompany;

/**
 * LogisticsCompanyMapper
 * 物流公司表
 *
 * @author parcel
 * 
 * @date 2015-01-28 09:07:46
 * 
 */
public interface LogisticsCompanyMapper {

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

}
