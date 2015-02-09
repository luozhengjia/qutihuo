package com.ejunhai.qutihuo.system.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.ejunhai.qutihuo.system.model.SystemPrivilage;

public class SystemPrivilageUtil {

	/**
	 * 获取action Id list
	 * 
	 * @param systemPrivilageList
	 * @return
	 */
	public static List<Integer> getActionIdList(List<SystemPrivilage> systemPrivilageList) {
		Set<Integer> actionIdSet = new HashSet<Integer>();
		if (CollectionUtils.isEmpty(systemPrivilageList)) {
			return new ArrayList<Integer>(actionIdSet);
		}

		for (SystemPrivilage systemPrivilage : systemPrivilageList) {
			actionIdSet.add(systemPrivilage.getActionId());
		}

		return new ArrayList<Integer>(actionIdSet);
	}

	public static Map<Integer, SystemPrivilage> getSystemPrivilageMap(List<SystemPrivilage> systemPrivilageList) {
		Map<Integer, SystemPrivilage> systemPrivilageMap = new HashMap<Integer, SystemPrivilage>();
		if (CollectionUtils.isEmpty(systemPrivilageList)) {
			return systemPrivilageMap;
		}

		for (SystemPrivilage systemPrivilage : systemPrivilageList) {
			systemPrivilageMap.put(systemPrivilage.getActionId(), systemPrivilage);
		}
		return systemPrivilageMap;
	}
}
