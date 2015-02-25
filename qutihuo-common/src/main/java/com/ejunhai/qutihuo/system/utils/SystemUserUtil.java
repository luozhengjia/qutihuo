package com.ejunhai.qutihuo.system.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.ejunhai.qutihuo.common.utils.CommonUtils;
import com.ejunhai.qutihuo.system.model.SystemUser;

public class SystemUserUtil {

	/**
	 * 获取role Id list
	 * 
	 * @param systemPrivilageList
	 * @return
	 */
	public static List<Integer> getRoleIdList(List<SystemUser> systemUserList) {
		Set<Integer> roleIdSet = new HashSet<Integer>();
		if (CollectionUtils.isEmpty(systemUserList)) {
			return new ArrayList<Integer>(roleIdSet);
		}

		for (SystemUser systemUser : systemUserList) {
			roleIdSet.addAll(CommonUtils.str2IntList(systemUser.getRoleIds(), ","));
		}

		return new ArrayList<Integer>(roleIdSet);
	}
}
