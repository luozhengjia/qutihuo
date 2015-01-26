package com.ejunhai.qutihuo.merchant.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejunhai.qutihuo.merchant.dao.MerchantMapper;
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(Merchant merchant) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Merchant merchant) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Integer queryMerchantCount(Merchant merchant) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer queryMerchantList(Merchant merchant) {
        // TODO Auto-generated method stub
        return null;
    }

}
