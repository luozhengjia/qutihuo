package com.ejunhai.qutihuo.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.order.dao.OrderMainMapper;
import com.ejunhai.qutihuo.order.model.OrderMain;
import com.ejunhai.qutihuo.order.service.OrderMainService;

/**
 * OrderMain Service 实现类
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:36:31
 * 
 */
@Service("orderMainService")
public class OrderMainServiceImpl implements OrderMainService {

    @Resource
    private OrderMainMapper orderMainMapper;

    @Override
    public OrderMain read(Integer id) {
        return orderMainMapper.read(id);
    }

    @Override
    public void insert(OrderMain orderMain) {
    	orderMainMapper.insert(orderMain);
    }

    @Override
    public void update(OrderMain orderMain) {
    	orderMainMapper.update(orderMain);

    }

    @Override
    public void delete(Integer id) {
    	orderMainMapper.delete(id);

    }

    @Override
    public Integer queryOrderMainCount(OrderMain orderMain) {
        return orderMainMapper.queryOrderMainCount(orderMain);
    }

    @Override
    public List<OrderMain> queryOrderMainList(OrderMain orderMain) {
        return orderMainMapper.queryOrderMainList(orderMain);
    }

}
