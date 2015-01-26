package com.ejunhai.qutihuo.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.order.dao.OrderReplMapper;
import com.ejunhai.qutihuo.order.model.OrderRepl;
import com.ejunhai.qutihuo.order.service.OrderReplService;

/**
 * OrderRepl Service 实现类
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:36:31
 * 
 */
@Service("orderReplService")
public class OrderReplServiceImpl implements OrderReplService {

    @Resource
    private OrderReplMapper orderReplMapper;

    @Override
    public OrderRepl read(Integer id) {
        return orderReplMapper.read(id);
    }

    @Override
    public void insert(OrderRepl orderRepl) {
    	orderReplMapper.insert(orderRepl);

    }

    @Override
    public void update(OrderRepl orderRepl) {
    	orderReplMapper.update(orderRepl);
    }

    @Override
    public void delete(Integer id) {
    	orderReplMapper.delete(id);
    }

    @Override
    public Integer queryOrderReplCount(OrderRepl orderRepl) {
        return orderReplMapper.queryOrderReplCount(orderRepl);
    }

    @Override
    public List<OrderRepl> queryOrderReplList(OrderRepl orderRepl) {
        return orderReplMapper.queryOrderReplList(orderRepl);
    }

}
