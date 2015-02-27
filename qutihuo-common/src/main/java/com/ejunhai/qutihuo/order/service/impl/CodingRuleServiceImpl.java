package com.ejunhai.qutihuo.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.order.dao.CodingRuleMapper;
import com.ejunhai.qutihuo.order.model.CodingRule;
import com.ejunhai.qutihuo.order.service.CodingRuleService;

/**
 * CodingRule Service 实现类
 * 
 * @author parcel
 * 
 * @date 2015-02-27 11:21:48
 * 
 */
@Service("codingRuleService")
public class CodingRuleServiceImpl implements CodingRuleService {

	@Resource
	private CodingRuleMapper codingRuleMapper;

	@Override
	public CodingRule read(Integer id) {
		return codingRuleMapper.read(id);
	}

	@Override
	public void insert(CodingRule codingRule) {
		codingRuleMapper.insert(codingRule);
	}

	@Override
	public void update(CodingRule codingRule) {
		codingRuleMapper.update(codingRule);
	}

	@Override
	public void delete(Integer id) {
		codingRuleMapper.delete(id);
	}

	@Override
	public Integer queryCodingRuleCount(CodingRule codingRule) {
		return codingRuleMapper.queryCodingRuleCount(codingRule);
	}

	@Override
	public List<CodingRule> queryCodingRuleList(CodingRule codingRule) {
		return codingRuleMapper.queryCodingRuleList(codingRule);
	}

	@Override
	public String getOrderNo() {
		return codingRuleMapper.getOrderNo();
	}
}
