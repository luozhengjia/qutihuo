package com.ejunhai.qutihuo.system.dao;

import java.util.List;

import com.ejunhai.qutihuo.system.dto.SystemActionDto;
import com.ejunhai.qutihuo.system.model.SystemAction;

/**
 * SystemActionMapper 功能表
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:22:36
 * 
 */
public interface SystemActionMapper {

    /**
     * 根据Id获取SystemAction
     * 
     * @param id
     * @return
     */
    public SystemAction read(Integer id);

    /**
     * 新增SystemAction
     * 
     * @param systemAction
     */
    public void insert(SystemAction systemAction);

    /**
     * 更新SystemAction
     * 
     * @param systemAction
     */
    public void update(SystemAction systemAction);

    /**
     * 删除SystemAction
     * 
     * @param id
     */
    public void delete(Integer id);

    /**
     * 查询SystemAction数量
     * 
     * @param systemAction
     * @return
     */
    public Integer querySystemActionCount(SystemActionDto systemActionDto);

    /**
     * 查询SystemAction列表
     * 
     * @param systemAction
     * @return
     */
    public List<SystemAction> querySystemActionList(SystemActionDto systemActionDto);
    
	/**
	 * 根据ID获取多个action
	 * 
	 * @param actionIdList
	 * @return
	 */
	public List<SystemAction> getSystemActionListByIds(List<Integer> actionIdList);

}
