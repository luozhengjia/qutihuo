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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(OrderRepl orderRepl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(OrderRepl orderRepl) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Integer queryOrderReplCount(OrderRepl orderRepl) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OrderRepl> queryOrderReplList(OrderRepl orderRepl) {
        // TODO Auto-generated method stub
        return null;
    }

}
