package com.ejunhai.qutihuo.merchant.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.ejunhai.qutihuo.merchant.model.Merchant;

public class MerchantUtil {
	public static Map<String, Merchant> getMerchantMap(List<Merchant> merchantList) {
		Map<String, Merchant> merchantMap = new HashMap<String, Merchant>();
		if (CollectionUtils.isEmpty(merchantList)) {
			return merchantMap;
		}

		for (Merchant merchant : merchantList) {
			merchantMap.put(String.valueOf(merchant.getId()), merchant);
		}

		return merchantMap;
	}
}
