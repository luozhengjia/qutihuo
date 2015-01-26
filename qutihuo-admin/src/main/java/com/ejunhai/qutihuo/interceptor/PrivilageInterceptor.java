package com.ejunhai.qutihuo.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ejunhai.qutihuo.common.constant.CommonConstant;
import com.ejunhai.qutihuo.system.enums.ActionType;
import com.ejunhai.qutihuo.system.enums.RoleType;
import com.ejunhai.qutihuo.system.model.SystemAction;
import com.ejunhai.qutihuo.system.service.SystemActionService;
import com.ejunhai.qutihuo.system.utils.SystemActionUtil;

import freemarker.ext.beans.BeansWrapper;

public class PrivilageInterceptor implements HandlerInterceptor {

	@Resource
	private SystemActionService systemActionService;

	private static Class[] defaultStaticClasses = { ActionType.class, RoleType.class };

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		// 异步请求，直接返回
		if (StringUtils.isNotBlank(request.getHeader("X-Requested-With")) || arg3 == null) {
			return;
		}

		BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
		for (Class clz : defaultStaticClasses) {
			arg3.getModelMap().addAttribute(clz.getSimpleName(), wrapper.getStaticModels().get(clz.getName()));
		}

		// 过滤非菜单节点-根据用户角色获取用户权限列表
		List<SystemAction> allSystemActionList = systemActionService.getAllSystemActionList();
		List<SystemAction> menuSystemActionList = SystemActionUtil.getMenuSystemActionList(allSystemActionList);
		List<SystemAction> rootMenuSystemActionList = new ArrayList<SystemAction>();
		for (SystemAction systemAction : menuSystemActionList) {
			if (CommonConstant.ROOT_MENU_ID.equals(systemAction.getParentId())) {
				rootMenuSystemActionList.add(systemAction);
			}
		}

		// 按根节点分组
		Map<String, List<SystemAction>> systemActionMap = new HashMap<String, List<SystemAction>>();
		for (SystemAction rootSystemAction : rootMenuSystemActionList) {
			List<SystemAction> systemActionGroupList = new ArrayList<SystemAction>();
			for (SystemAction systemAction : menuSystemActionList) {
				if (systemAction.getParentId().equals(rootSystemAction.getId())) {
					systemActionGroupList.add(systemAction);
				}
			}
			systemActionMap.put(String.valueOf(rootSystemAction.getId()), systemActionGroupList);
		}

		arg3.addObject("systemActionMap", systemActionMap);
		arg3.addObject("rootMenuSystemActionList", rootMenuSystemActionList);
		arg3.addObject("routeMap", SystemActionUtil.getRouteMapByUrl(allSystemActionList, request.getRequestURI()));
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		return true;
	}

}
