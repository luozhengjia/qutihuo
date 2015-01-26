package com.ejunhai.qutihuo.merchant.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.merchant.dao.FinanceFlowLogMapper;
import com.ejunhai.qutihuo.merchant.model.FinanceFlowLog;
import com.ejunhai.qutihuo.merchant.service.FinanceFlowLogService;

/**
 * FinanceFlowLog Service 实现类
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:42:31
 * 
 */
@Service("financeFlowLogService")
public class FinanceFlowLogServiceImpl implements FinanceFlowLogService {

    @Resource
    private FinanceFlowLogMapper financeFlowLogMapper;

    @Override
    public FinanceFlowLog read(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(FinanceFlowLog financeFlowLog) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(FinanceFlowLog financeFlowLog) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Integer queryFinanceFlowLogCount(FinanceFlowLog financeFlowLog) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer queryFinanceFlowLogList(FinanceFlowLog financeFlowLog) {
        // TODO Auto-generated method stub
        return null;
    }

}
