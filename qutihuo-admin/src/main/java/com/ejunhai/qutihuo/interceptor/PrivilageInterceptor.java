package com.ejunhai.qutihuo.interceptor;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ejunhai.qutihuo.system.model.SystemAction;
import com.ejunhai.qutihuo.system.model.SystemPrivilage;
import com.ejunhai.qutihuo.system.service.SystemActionService;
import com.ejunhai.qutihuo.system.service.SystemPrivilageService;
import com.ejunhai.qutihuo.system.utils.SystemActionUtil;
import com.ejunhai.qutihuo.system.utils.SystemPrivilageUtil;
import com.ejunhai.qutihuo.utils.FrontUtil;
import com.ejunhai.qutihuo.utils.SessionManager;

public class PrivilageInterceptor implements HandlerInterceptor {

	/** Logger available to subclasses */
	protected final Logger logger = Logger.getLogger(this.getClass().getName());

	@Resource
	private SystemActionService systemActionService;

	@Resource
	private SystemPrivilageService systemPrivilageService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		if (SessionManager.get(request) == null || FrontUtil.isExcludeUrl(request.getRequestURI())) {
			return true;
		}

		// 获取当前用户所拥有的action列表
		String roleIds = SessionManager.get(request).getRoleIds();
		List<SystemPrivilage> authorizedPrivilageList = systemPrivilageService.getSystemPrivilageListByRoleIds(roleIds);
		List<Integer> authorizedActionIdList = SystemPrivilageUtil.getActionIdList(authorizedPrivilageList);
		List<SystemAction> authorizedActionList = systemActionService.getSystemActionListByIds(authorizedActionIdList);
		Map<String, SystemAction> url2ActionMap = SystemActionUtil.getUrl2ActionMap(authorizedActionList);

		// 处理异步请求-重定向到统一异常处理
		if (url2ActionMap.get(request.getRequestURI()) == null) {
			response.sendRedirect("/forbidden.jhtml");
			return false;
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

}
