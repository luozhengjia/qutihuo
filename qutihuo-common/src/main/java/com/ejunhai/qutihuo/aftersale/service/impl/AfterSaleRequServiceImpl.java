package com.ejunhai.qutihuo.aftersale.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.aftersale.dao.AfterSaleRequMapper;
import com.ejunhai.qutihuo.aftersale.dto.AfterSaleRequDto;
import com.ejunhai.qutihuo.aftersale.model.AfterSaleRequ;
import com.ejunhai.qutihuo.aftersale.service.AfterSaleRequService;

/**
 * AfterSaleRequ Service 实现类
 * 
 * @author parcel
 * 
 * @date 2015-04-09 20:22:19
 * 
 */
@Service("afterSaleRequService")
public class AfterSaleRequServiceImpl implements AfterSaleRequService {

	@Resource
	private AfterSaleRequMapper afterSaleRequMapper;

	@Override
	public AfterSaleRequ read(Integer id) {
		return afterSaleRequMapper.read(id);
	}

	@Override
	public AfterSaleRequ getAfterSaleRequByOrderMainNo(String orderMainNo) {
		return afterSaleRequMapper.getAfterSaleRequByOrderMainNo(orderMainNo);
	}

	@Override
	public void insert(AfterSaleRequ afterSaleRequ) {
		afterSaleRequMapper.insert(afterSaleRequ);
	}

	@Override
	public void update(AfterSaleRequ afterSaleRequ) {
		afterSaleRequMapper.update(afterSaleRequ);
	}

	@Override
	public Integer queryAfterSaleRequCount(AfterSaleRequDto afterSaleRequDto) {
		return afterSaleRequMapper.queryAfterSaleRequCount(afterSaleRequDto);
	}

	@Override
	public List<AfterSaleRequ> queryAfterSaleRequList(AfterSaleRequDto afterSaleRequDto) {
		return afterSaleRequMapper.queryAfterSaleRequList(afterSaleRequDto);
	}
}
