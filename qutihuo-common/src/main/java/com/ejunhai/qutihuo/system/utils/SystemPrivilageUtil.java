package com.ejunhai.qutihuo.system.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<Integer> actionIdList = new ArrayList<Integer>();
		if (CollectionUtils.isEmpty(systemPrivilageList)) {
			return actionIdList;
		}

		for (SystemPrivilage systemPrivilage : systemPrivilageList) {
			actionIdList.add(systemPrivilage.getActionId());
		}

		return actionIdList;
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
