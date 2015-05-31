package com.ejunhai.qutihuo.order.service;

import java.util.List;

import com.ejunhai.qutihuo.order.dto.OrderReplDto;
import com.ejunhai.qutihuo.order.model.OrderRepl;

/**
 * 
 * OrderRepl Service 接口
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:36:31
 * 
 */
public interface OrderReplService {

    /**
     * 根据Id获取OrderRepl
     * 
     * @param id
     * @return
     */
    public OrderRepl read(Integer id);
    
    /**
     * 新增OrderRepl
     * 
     * @param orderRepl
     */
    public void insert(OrderRepl orderRepl);

    /**
     * 更新OrderRepl
     * 
     * @param orderRepl
     */
    public void update(OrderRepl orderRepl);
    
    /**
     * 删除OrderRepl
     * 
     * @param id
     */
    public void delete(Integer id);
    
    /**
     * 查询OrderRepl数量
     * 
     * @param orderRepl
     * @return
     */
    public Integer queryOrderReplCount(OrderRepl orderRepl);
    
    /**
     * 查询OrderRepl列表
     * 
     * @param orderRepl
     * @return
     */
    public List<OrderRepl> queryOrderReplList(OrderReplDto orderReplDto);

}
