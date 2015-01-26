package com.ejunhai.qutihuo.system.utils;

import java.util.ArrayList;
import java.util.List;

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
}
