package com.ejunhai.qutihuo.system.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.ejunhai.qutihuo.system.model.SystemRole;

public class SystemRoleUtil {

	public static Map<String, SystemRole> getSystemRoleMap(List<SystemRole> systemRoleList) {
		Map<String, SystemRole> systemRoleMap = new HashMap<String, SystemRole>();
		if (CollectionUtils.isEmpty(systemRoleList)) {
			return systemRoleMap;
		}

		for (SystemRole systemRole : systemRoleList) {
			systemRoleMap.put(String.valueOf(systemRole.getId()), systemRole);
		}

		return systemRoleMap;
	}
}
