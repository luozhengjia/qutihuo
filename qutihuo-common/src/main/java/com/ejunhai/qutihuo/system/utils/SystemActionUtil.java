package com.ejunhai.qutihuo.system.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ejunhai.qutihuo.common.constant.CommonConstant;
import com.ejunhai.qutihuo.system.enums.ActionType;
import com.ejunhai.qutihuo.system.model.SystemAction;

public class SystemActionUtil {


	/**
	 * 根据节点类型获取父节点列表
	 * 
	 * @param systemActionList
	 * @param nodeType
	 * @return
	 */
	public static List<SystemAction> getSystemActionListByNodeType(List<SystemAction> systemActionList, Integer nodeType) {
		List<SystemAction> parentActionList = new ArrayList<SystemAction>();
		if (nodeType == null) {
			return parentActionList;
		}

		// 构造根节点
		SystemAction rootSystemAction = new SystemAction();
		rootSystemAction.setId(CommonConstant.ROOT_MENU_ID);
		rootSystemAction.setActionName("根节点");

		// 目录只能选择根节点为父亲节点
		if (ActionType.catalog.getValue().equals(nodeType)) {
			parentActionList.add(rootSystemAction);
			return parentActionList;
		}

		// 菜单可以选择根节点和目录节点为父亲节点
		if (ActionType.menu.getValue().equals(nodeType)) {
			List<SystemAction> catalogSystemActionList = new ArrayList<SystemAction>();
			catalogSystemActionList.add(rootSystemAction);
			for (SystemAction systemAction : systemActionList) {
				if (ActionType.catalog.getValue().equals(systemAction.getActionType())) {
					catalogSystemActionList.add(systemAction);
				}
			}
			return catalogSystemActionList;
		}

		// 操作节点只可以选择菜单节点为父亲节点
		List<SystemAction> menuSystemActionList = new ArrayList<SystemAction>();
		for (SystemAction systemAction : systemActionList) {
			if (ActionType.menu.getValue().equals(systemAction.getActionType())) {
				menuSystemActionList.add(systemAction);
			}
		}
		return menuSystemActionList;
	}

	/**
	 * 将列表转成map
	 * 
	 * @param systemActionList
	 * @return
	 */
	public static Map<String, SystemAction> getSystemActionMap(List<SystemAction> systemActionList) {
		Map<String, SystemAction> systemActionMap = new HashMap<String, SystemAction>();
		for (SystemAction systemAction : systemActionList) {
			systemActionMap.put(String.valueOf(systemAction.getId()), systemAction);
		}

		return systemActionMap;
	}

	/**
	 * 
	 * @param systemActionList
	 * @return
	 */
	public static Map<String, SystemAction> getUrl2ActionMap(List<SystemAction> systemActionList) {
		Map<String, SystemAction> routeMap = new HashMap<String, SystemAction>();
		if (systemActionList == null) {
			return routeMap;
		}

		for (SystemAction systemAction : systemActionList) {
			routeMap.put(systemAction.getUrl(), systemAction);
		}
		return routeMap;
	}

	/**
	 * 根据url获取目录路由
	 * 
	 * @param systemActionList
	 * @param url
	 * @return
	 */
	public static Map<String, SystemAction> getRouteMapByUrl(List<SystemAction> systemActionList, String url) {
		Map<String, SystemAction> routeMap = new HashMap<String, SystemAction>();
		if (systemActionList == null || StringUtils.isBlank(url)) {
			return routeMap;
		}

		Map<String, SystemAction> systemActionMap = getSystemActionMap(systemActionList);
		for (SystemAction systemAction : systemActionList) {
			if (url.equals(systemAction.getUrl())) {
				routeMap.put(String.valueOf(systemAction.getId()), systemAction);
				while (!CommonConstant.ROOT_MENU_ID.equals(systemAction.getParentId())) {
					systemAction = systemActionMap.get(String.valueOf(systemAction.getParentId()));
					routeMap.put(String.valueOf(systemAction.getId()), systemAction);
				}
			}
		}
		return routeMap;
	}

	/**
	 * 获取当前action到根的action列表
	 * 
	 * @param actionId
	 * @param systemActionMap
	 * @return
	 */
	public static List<SystemAction> getCurAction2RootList(Integer actionId, Map<String, SystemAction> systemActionMap) {
		List<SystemAction> systemActionList = new ArrayList<SystemAction>();
		SystemAction systemAction = systemActionMap.get(String.valueOf(actionId));
		systemActionList.add(systemAction);
		while (!CommonConstant.ROOT_MENU_ID.equals(systemAction.getParentId())) {
			systemAction = systemActionMap.get(String.valueOf(systemAction.getParentId()));
			if (systemAction == null) {
				break;
			}
			systemActionList.add(systemAction);
		}
		return systemActionList;
	}
}
