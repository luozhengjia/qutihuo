package com.ejunhai.qutihuo.merchant.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.merchant.dao.MerchantMapper;
import com.ejunhai.qutihuo.merchant.dto.MerchantDto;
import com.ejunhai.qutihuo.merchant.model.Merchant;
import com.ejunhai.qutihuo.merchant.service.MerchantService;

/**
 * Merchant Service 实现类
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:42:31
 * 
 */
@Service("merchantService")
public class MerchantServiceImpl implements MerchantService {

	@Resource
	private MerchantMapper merchantMapper;

	@Override
	public Merchant read(Integer id) {
		return merchantMapper.read(id);
	}

	@Override
	public void insert(Merchant merchant) {
		merchant.setCreateTime(new Timestamp(System.currentTimeMillis()));
		merchant.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		merchantMapper.insert(merchant);
	}

	@Override
	public void update(Merchant merchant) {
		merchant.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		merchantMapper.update(merchant);
	}

	@Override
	public void delete(Integer id) {
		merchantMapper.delete(id);
	}

	@Override
	public Integer queryMerchantCount(MerchantDto merchantDto) {
		return merchantMapper.queryMerchantCount(merchantDto);
	}

	@Override
	public List<Merchant> queryMerchantList(MerchantDto merchantDto) {
		return merchantMapper.queryMerchantList(merchantDto);
	}

	@Override
	public List<Merchant> getMerchantListByIds(List<Integer> merchantIds) {
		return merchantMapper.getMerchantListByIds(merchantIds);
	}

}
