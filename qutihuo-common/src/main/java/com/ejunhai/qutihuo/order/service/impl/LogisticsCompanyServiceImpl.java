package com.ejunhai.qutihuo.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.order.dao.LogisticsCompanyMapper;
import com.ejunhai.qutihuo.order.model.LogisticsCompany;
import com.ejunhai.qutihuo.order.service.LogisticsCompanyService;

/**
 * LogisticsCompany Service 实现类
 * 
 * @author parcel
 * 
 * @date 2015-01-28 09:07:46
 * 
 */
@Service("logisticsCompanyService")
public class LogisticsCompanyServiceImpl implements LogisticsCompanyService {

	@Resource
	private LogisticsCompanyMapper logisticsCompanyMapper;

	@Override
	public LogisticsCompany read(Integer id) {
		return logisticsCompanyMapper.read(id);
	}

	@Override
	public void insert(LogisticsCompany logisticsCompany) {
		logisticsCompanyMapper.insert(logisticsCompany);
	}

	@Override
	public void update(LogisticsCompany logisticsCompany) {
		logisticsCompanyMapper.update(logisticsCompany);
	}

	@Override
	public void delete(Integer id) {
		logisticsCompanyMapper.delete(id);
	}

	@Override
	public Integer queryLogisticsCompanyCount(LogisticsCompany logisticsCompany) {
		return logisticsCompanyMapper.queryLogisticsCompanyCount(logisticsCompany);
	}

	@Override
	public List<LogisticsCompany> queryLogisticsCompanyList(LogisticsCompany logisticsCompany) {
		return logisticsCompanyMapper.queryLogisticsCompanyList(logisticsCompany);
	}

	@Override
	public LogisticsCompany findLogisticsCompany(LogisticsCompany logisticsCompany) {
		return logisticsCompanyMapper.findLogisticsCompany(logisticsCompany);
	}
}
