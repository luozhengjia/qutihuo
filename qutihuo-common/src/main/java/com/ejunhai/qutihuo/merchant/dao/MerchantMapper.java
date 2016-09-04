package com.ejunhai.qutihuo.merchant.dao;

import java.util.List;

import com.ejunhai.qutihuo.merchant.dto.MerchantDto;
import com.ejunhai.qutihuo.merchant.model.Merchant;

/**
 * MerchantMapper 商户表
 * 
 * @author parcel
 * 
 * @date 2014-12-10 21:42:31
 * 
 */
public interface MerchantMapper {

	/**
	 * 根据Id获取Merchant
	 * 
	 * @param id
	 * @return
	 */
	public Merchant read(Integer id);

	/**
	 * 新增Merchant
	 * 
	 * @param merchant
	 */
	public void insert(Merchant merchant);

	/**
	 * 更新Merchant
	 * 
	 * @param merchant
	 */
	public void update(Merchant merchant);

	/**
	 * 删除Merchant
	 * 
	 * @param id
	 */
	public void delete(Integer id);

	/**
	 * 查询Merchant数量
	 * 
	 * @param merchant
	 * @return
	 */
	public Integer queryMerchantCount(MerchantDto merchantDto);

	/**
	 * 查询Merchant列表
	 * 
	 * @param merchant
	 * @return
	 */
	public List<Merchant> queryMerchantList(MerchantDto merchantDto);

	/**
	 * 根据ID获取商户列表
	 * 
	 * @param merchantIds
	 * @return
	 */
	public List<Merchant> getMerchantListByIds(List<Integer> merchantIds);

}
