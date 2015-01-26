package com.ejunhai.qutihuo.merchant.service;

import com.ejunhai.qutihuo.merchant.model.FinanceFlowLog;

/**
 * 
 * FinanceFlowLog Service 接口
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:42:31
 * 
 */
public interface FinanceFlowLogService {

    /**
     * 根据Id获取FinanceFlowLog
     * 
     * @param id
     * @return
     */
    public FinanceFlowLog read(Integer id);

    /**
     * 新增FinanceFlowLog
     * 
     * @param financeFlowLog
     */
    public void insert(FinanceFlowLog financeFlowLog);

    /**
     * 更新FinanceFlowLog
     * 
     * @param financeFlowLog
     */
    public void update(FinanceFlowLog financeFlowLog);

    /**
     * 删除FinanceFlowLog
     * 
     * @param id
     */
    public void delete(Integer id);

    /**
     * 查询FinanceFlowLog数量
     * 
     * @param financeFlowLog
     * @return
     */
    public Integer queryFinanceFlowLogCount(FinanceFlowLog financeFlowLog);

    /**
     * 查询FinanceFlowLog列表
     * 
     * @param financeFlowLog
     * @return
     */
    public Integer queryFinanceFlowLogList(FinanceFlowLog financeFlowLog);

}
